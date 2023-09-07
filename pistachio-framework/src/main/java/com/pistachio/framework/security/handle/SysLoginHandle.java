package com.pistachio.framework.security.handle;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.pistachio.common.constant.CacheConstants;
import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.common.enums.LoginDeviceEnum;
import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.system.dto.req.AdminLoginRequest;
import com.pistachio.system.dto.req.UserChangePasswordRequest;
import com.pistachio.system.dto.vo.LoginSuccessVo;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: Pengsy
 * @date: 2023/08/02 16:45
 * @description: 实现登录操作
 */
@Component
public class SysLoginHandle {

    @Value("${sa-token.private-key}")
    private String privateKey;

    @Value("${sa-token.public-key}")
    private String publicKey;

    @Value("${sa-token.token-prefix}")
    private String tokenPrefix;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 登录验证
     *
     * @param adminLoginRequest 登录参数传输对象
     * @return LoginSuccessVo
     */
    public LoginSuccessVo doAdminLogin(AdminLoginRequest adminLoginRequest) {
        validateCaptcha(adminLoginRequest.getCode(), adminLoginRequest.getUuid());

        SysUserEntity sysUser = sysUserRepository.findFirstByUsername(adminLoginRequest.getUsername()).orElseThrow(() -> new ServiceException(("没有该账号信息")));

        String password = adminLoginRequest.getPassword();
        if (!SaSecureUtil.rsaDecryptByPrivate(privateKey, sysUser.getPassword()).equals(password)) {
            throw new ServiceException("密码信息不正确");
        }

        if (adminLoginRequest.isRemember()) {
            StpUtil.login(sysUser.getId(), new SaLoginModel()
                    //此次登录的客户端标识,用于[同端互斥登录]时指定此次登录的设备名称
                    .setDevice(LoginDeviceEnum.COMPUTER_TERMINAL.getDevice())
                    //是否持久Cookie(临时cookie在浏览器关闭时会自动删除,持久cookie在重新打开后依然存在)
                    .setIsLastingCookie(true));
        } else {
            StpUtil.login(sysUser.getId(), false);
        }

        StpUtil.getSession().set("user", sysUser);

        //获取到token给到标准返回
        return new LoginSuccessVo(StpUtil.getTokenInfo().getTokenName(), StpUtil.getTokenInfo().getTokenValue(), tokenPrefix);

    }

    /**
     * 退出系统
     */
    public void doAdminLogout() {
        StpUtil.getSession().delete("user");
        StpUtil.logout();
    }

    /**
     * 密码加密
     *
     * @param text 原文
     * @return String 密文
     */
    public String rsaEncryptByPublic(String text) {
        return SaSecureUtil.rsaEncryptByPublic(publicKey, text);
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     */
    private void validateCaptcha(String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtil.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey).toString();
        // 一次性使用
        redisCache.deleteObject(verifyKey);

        if (captcha == null) {
            throw new ServiceException("验证码不能为空", 500);
        }

        // fix 由于redis取值的时候，得到的结果是Double，所以转成String的时候会携带小数部分，需要手动处理下
        captcha = captcha.substring(0, captcha.indexOf("."));

        if (!code.equals(captcha)) {
            throw new ServiceException("验证码校验失败", 500);
        }
    }

    public void changePassword(UserChangePasswordRequest request) {

        if (request.getNewPassword().length() < 8 || request.getNewPassword().length() > 18) {
            throw new ServiceException("新密码长度只能是 8~18位");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new ServiceException("两次输入的密码不相同");
        }

        SysUserEntity userEntity = (SysUserEntity) StpUtil.getSession().get("user");

        SysUserEntity sysUser = sysUserRepository.findFirstById(userEntity.getId()).orElseThrow(() -> new ServiceException("没有找到账户信息"));

        if (!SaSecureUtil.rsaDecryptByPrivate(privateKey, sysUser.getPassword()).equals(request.getOldPassword())) {
            throw new ServiceException("原密码不正确");
        }

        sysUser.setPassword(rsaEncryptByPublic(request.getNewPassword()));

        sysUserRepository.save(sysUser);
    }

}

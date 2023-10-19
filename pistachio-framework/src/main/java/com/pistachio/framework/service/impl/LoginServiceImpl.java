package com.pistachio.framework.service.impl;

import com.pistachio.common.constant.CacheConstants;
import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.JwtUtil;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.framework.dto.LoginUserDto;
import com.pistachio.framework.service.LoginService;
import com.pistachio.system.dto.req.AdminLoginRequest;
import com.pistachio.system.dto.vo.LoginSuccessVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Value("${spring.security.token-name}")
    private String tokenName;

    @Value("${spring.security.token-prefix}")
    private String tokenPrefix;

    @Override
    public LoginSuccessVo adminLogin(AdminLoginRequest loginRequest) {
        // 处理验证码问题
        validateCaptcha(loginRequest.getCode(), loginRequest.getUuid());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new ServiceException("用户名或密码错误111");
        }
        //使用userid生成token
        LoginUserDto loginUser = (LoginUserDto) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //authenticate存入redis
        redisCache.setCacheObject("login:" + userId, GsonUtil.toJSONString(loginUser));

        return new LoginSuccessVo(tokenName, jwt, tokenPrefix);
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

    @Override
    public void adminLogout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDto loginUser = (LoginUserDto) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userid);
    }
}

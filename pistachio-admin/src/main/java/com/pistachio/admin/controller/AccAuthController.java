package com.pistachio.admin.controller;

import com.google.code.kaptcha.Producer;
import com.pistachio.common.constant.CacheConstants;
import com.pistachio.common.constant.Constants;
import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.common.utils.R;
import com.pistachio.common.utils.uuid.IdUtil;
import com.pistachio.framework.security.handle.SysLoginHandle;
import com.pistachio.system.dto.req.AdminLoginRequest;
import com.pistachio.system.dto.vo.CaptchaImageVo;
import com.pistachio.system.dto.vo.LoginSuccessVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证模块
 *
 * @author Pengsy
 * @date 2023/08/02 14:45
 */
@Slf4j
@RestController
@RequestMapping("/acc")
public class AccAuthController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysLoginHandle sysLoginHandle;

    /**
     * 获取验证码
     *
     * @apiNote 后台登录获取验证码信息
     */
    @GetMapping("/getCaptcha")
    public R<CaptchaImageVo> caption() {
        String text = producer.createText();

        //个位数字相加
        String s1 = text.substring(0, 1);
        String s2 = text.substring(1, 2);
        int code = Integer.parseInt(s1) + Integer.parseInt(s2);

        //生成图片验证码
        BufferedImage image = producer.createImage(s1 + "+" + s2 + "=?");
        try {
            String uuid = IdUtil.simpleUUID();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String str = "data:image/jpeg;base64,";
            String base64Img = str + encoder.encode(outputStream.toByteArray());

            // 存储到redis中
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
            redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

            log.info("验证码 -- {} - {}", verifyKey, code);

            return R.success(new CaptchaImageVo(uuid, base64Img));
        } catch (IOException e) {

            return R.error("验证码获取失败");
        }
    }

    /**
     * 管理员登录
     *
     * @param loginRequest 登录信息传输对象
     */
    @PostMapping("/admin/doLogin")
    public R<LoginSuccessVo> doLogin(@Validated @RequestBody AdminLoginRequest loginRequest) {
        return R.success(sysLoginHandle.doAdminLogin(loginRequest));
    }

    /**
     * 管理员退出
     *
     * @apiNote 需要登录后才能操作
     */
    @GetMapping("/admin/logout")
    public R<Object> doLogout() {
        sysLoginHandle.doAdminLogout();
        return R.success();
    }
}

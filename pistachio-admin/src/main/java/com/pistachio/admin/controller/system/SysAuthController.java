package com.pistachio.admin.controller.system;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * @order 1
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "系统验证模块")
@Slf4j
@RestController
@RequestMapping("/sys-auth")
public class SysAuthController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysLoginHandle sysLoginHandle;

    @Operation(summary = "系统登录获取验证码信息", description = "后台登录界面获取验证码")
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

    @Operation(summary = "系统登录", description = "后台管理员登录系统")
    @PostMapping("/admin/doLogin")
    public R<LoginSuccessVo> doLogin(@Validated @RequestBody AdminLoginRequest loginRequest) {
        return R.success(sysLoginHandle.doAdminLogin(loginRequest));
    }

    @Operation(summary = "管理员退出", description = "管理员退出")
    @GetMapping("/admin/logout")
    public R<Object> doLogout() {
        sysLoginHandle.doAdminLogout();
        return R.success();
    }
}

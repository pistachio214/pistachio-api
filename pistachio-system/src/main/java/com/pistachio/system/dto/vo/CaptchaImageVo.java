package com.pistachio.system.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Pengsy
 * @date: 2023/08/02 16:30
 * @description: 验证码vo
 */
@AllArgsConstructor
@Data
public class CaptchaImageVo {

    private String token;

    private String base64Img;
}

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

    /**
     * token值，即登录时传入的uuid
     *
     * @mock e506f869-bff8-4e2b-81b5-abc8c23b5c71
     */
    private String token;

    /**
     * base64编码的图片信息
     */
    private String base64Img;
}

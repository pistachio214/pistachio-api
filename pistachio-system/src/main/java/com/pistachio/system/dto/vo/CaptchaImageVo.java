package com.pistachio.system.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Pengsy
 * @date: 2023/08/02 16:30
 * @description: 验证码vo
 */
@Schema(name = "验证码vo", description = "数据传输对象", defaultValue = "{}")
@AllArgsConstructor
@Data
public class CaptchaImageVo {

    @Schema(description = "token值，即登录时传入的uuid", defaultValue = "e506f869-bff8-4e2b-81b5-abc8c23b5c71")
    private String token;

    /**
     * base64编码的图片信息
     */
    @Schema(description = "base64编码的图片信息", defaultValue = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRof")
    private String base64Img;
}

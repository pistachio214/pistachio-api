package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: Pengsy
 * @date: 2023/08/02 14:58
 * @description: 登录对象
 */
@Schema(description = "请求传输参数")
@Data
public class AdminLoginRequest {

    @Schema(description = "账号", defaultValue = "admin", required = true)
    @NotBlank(message = "登录账号不能为空")
    private String username;

    @Schema(description = "密码", defaultValue = "123456", required = true)
    @NotBlank(message = "登录密码不能为空")
    private String password;

    @Schema(description = "验证码", defaultValue = "36", required = true)
    @NotBlank(message = "验证码code不能为空")
    private String code;

    @Schema(description = "验证码对应的uuid", defaultValue = "e506f869-bff8-4e2b-81b5-abc8c23b5c71", required = true)
    @NotBlank(message = "验证码uuid不能为空")
    private String uuid;

    @Schema(description = "是否记住登录 true:记住；false: 不记住", defaultValue = "true")
    private boolean isRemember;
}

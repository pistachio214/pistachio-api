package com.pistachio.system.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: Pengsy
 * @date: 2023/08/02 14:58
 * @description: 登录对象
 */
@Data
public class AdminLoginRequest {

    /**
     * 账号
     *
     * @mock admin
     */
    @NotBlank(message = "登录账号不能为空")
    private String username;

    /**
     * 密码
     *
     * @mock 123456
     * @since 12321312
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;

    /**
     * 验证码
     *
     * @mock 12
     * @since 1
     */
    @NotBlank(message = "验证码code不能为空")
    private String code;

    /**
     * 验证码对应的uuid
     */
    @NotBlank(message = "验证码uuid不能为空")
    private String uuid;

    /**
     * 是否记住登录 true:记住；false: 不记住
     * @since true
     */
    private boolean isRemember;
}

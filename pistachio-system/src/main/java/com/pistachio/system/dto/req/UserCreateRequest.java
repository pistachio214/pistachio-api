package com.pistachio.system.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: user create
 * @date: 2023/08/10 15:29
 * @author: Pengsy
 */
@Data
public class UserCreateRequest {

    /**
     * 用户名
     *
     * @mock admin
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 昵称
     *
     * @mock 萧十一郎
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 邮箱
     *
     * @mock xiaoshiyilang@outlook.com
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 状态
     *
     * @mock 1
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
}

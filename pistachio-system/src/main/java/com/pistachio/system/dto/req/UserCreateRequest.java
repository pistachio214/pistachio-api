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

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotNull(message = "状态不能为空")
    private Integer status;
}

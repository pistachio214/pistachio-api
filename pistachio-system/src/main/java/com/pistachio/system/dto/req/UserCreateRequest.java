package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: user create
 * @date: 2023/08/10 15:29
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class UserCreateRequest {

    @Schema(description = "用户名", defaultValue = "admin", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "昵称", defaultValue = "萧十一郎", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "邮箱", defaultValue = "xiaoshiyilang@outlook.com", required = true)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Schema(description = "状态", defaultValue = "1", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;
}

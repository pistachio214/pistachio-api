package com.pistachio.system.dto.req;

import com.pistachio.common.constant.UserConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @date: 2023/09/07 14:26
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class UserChangePasswordRequest {

    @Schema(description = "原密码", defaultValue = "12345678", required = true)
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @Schema(description = "新密码", defaultValue = "12345678", required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @Schema(description = "重复新密码", defaultValue = "12345678", required = true)
    @NotBlank(message = "重复新密码不能为空")
    private String confirmPassword;
}

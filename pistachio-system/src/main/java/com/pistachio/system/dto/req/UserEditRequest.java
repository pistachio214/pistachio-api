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
public class UserEditRequest {

    @Schema(description = "ID", defaultValue = "1", required = true, hidden = true)
    private Long id;

    @Schema(description = "昵称", defaultValue = "萧十一郎", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "邮箱", defaultValue = "xiaoshiyilang@outlook.com")
    private String email;

    @Schema(description = "头像", defaultValue = UserConstants.DEFAULT_AVATAR)
    private String avatar;
}

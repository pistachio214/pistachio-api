package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/18 15:20
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class UserRepassRequest {

    @Schema(description = "管理员id", defaultValue = "1", required = true)
    @NotNull(message = "管理员id不能为空")
    private Long userId;

}

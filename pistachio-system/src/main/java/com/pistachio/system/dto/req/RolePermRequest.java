package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/18 11:43
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class RolePermRequest {

    @Schema(description = "list菜单id", defaultValue = "[1,2,3]", required = true)
    @NotNull(message = "菜单id不能为空")
    private Long[] menuIds;
}

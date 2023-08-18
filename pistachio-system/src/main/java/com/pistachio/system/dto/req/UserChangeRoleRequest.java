package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/18 15:32
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class UserChangeRoleRequest {

    @Schema(description = "角色id列表", defaultValue = "[3,4,5,6,9,8,7]")
    @NotNull(message = "角色id列表")
    private Long[] roleIds;

}

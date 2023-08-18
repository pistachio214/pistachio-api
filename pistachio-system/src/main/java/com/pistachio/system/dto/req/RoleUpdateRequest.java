package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @date: 2023/08/10 16:57
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleUpdateRequest extends RoleCreateRequest {

    @Schema(description = "角色id", defaultValue = "123", required = true)
    @NotNull(message = "角色id")
    private Long id;

    @Schema(description = "菜单id列表", defaultValue = "[22, 33, 445]")
    private List<Long> menuIds;
}

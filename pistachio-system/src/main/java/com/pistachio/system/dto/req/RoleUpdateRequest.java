package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description:
 * @date: 2023/08/10 16:57
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleUpdateRequest extends RoleCreateRequest {
    /**
     * 角色id
     *
     * @mock 123
     */
    @NotNull(message = "角色id")
    private Long id;

    /**
     * 菜单id列表
     *
     * @mock [22, 33, 445]
     */
    private List<Long> menuIds;
}

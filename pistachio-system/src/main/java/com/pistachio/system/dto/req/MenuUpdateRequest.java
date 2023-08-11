package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/11 10:32
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuUpdateRequest extends MenuCreateRequest {

    /**
     * 菜单id
     *
     * @mock 11
     */
    @NotNull(message = "菜单id不能为空")
    private Long id;
}

package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @date: 2023/08/10 16:57
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleUpdateRequest extends RoleCreateRequest{
    private Long id;

    private List<Long> menuIds;
}
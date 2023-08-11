package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @date: 2023/08/11 10:32
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuUpdateRequest extends MenuCreateRequest{
    private Long id;
}

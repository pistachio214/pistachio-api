package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: dict item edit dto
 * @date: 2023/08/09 14:03
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictItemEditRequest extends DictItemCreateRequest {

    private Long id;
}

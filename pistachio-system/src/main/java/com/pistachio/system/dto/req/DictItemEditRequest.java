package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description: dict item edit dto
 * @date: 2023/08/09 14:03
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictItemEditRequest extends DictItemCreateRequest {

    /**
     * 字典项ID
     *
     * @mock 100
     */
    @NotNull(message = "字典项ID不能为空")
    private Long id;
}

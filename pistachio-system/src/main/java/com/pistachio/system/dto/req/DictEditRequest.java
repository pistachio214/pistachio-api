package com.pistachio.system.dto.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description: edit dict dto
 * @date: 2023/08/09 11:45
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictEditRequest extends DictCreateRequest {

    /**
     * 字典ID
     * @mock 19
     */
    @NotNull(message = "字典ID不能为空")
    private Long id;
}

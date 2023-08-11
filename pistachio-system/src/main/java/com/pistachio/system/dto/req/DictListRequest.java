package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Pengsy
 * @date: 2023/08/04 17:02
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictListRequest extends ReqPageDto {

    /**
     * 字典类型
     * @since 字典key dicts_type
     */
    private Integer system;

    /**
     * 类型名称
     */
    private String type;
}

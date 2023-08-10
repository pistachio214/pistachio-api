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
    private Integer system;

    private String type;
}

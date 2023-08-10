package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @date: 2023/08/09 16:41
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleListRequest extends ReqPageDto {

    private String name;

    private String code;

}

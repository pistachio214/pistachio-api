package com.pistachio.system.dto.vo;

import com.pistachio.system.entity.SysMenuEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @date: 2023/08/18 11:23
 * @author: Pengsy
 */
@Data
@AllArgsConstructor
public class SysMenuListTreeVo {

    @Schema(description = "为兼容knife4j-openapi3,把list在包一层object")
    private List<SysMenuEntity> list;

}

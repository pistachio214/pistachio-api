package com.pistachio.system.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/07 15:10
 * @description: SysDictAndItemVo
 */
@Data
public class SysDictAndItemVo {

    @Schema(description = "字典ID", defaultValue = "19")
    private Long id;

    @Schema(description = "类型标识", defaultValue = "dict_type")
    private String type;

    @Schema(description = "字典名称", defaultValue = "性别")
    private String name;

    @Schema(description = "字典项列表")
    List<SysDictItemVo> items;
}

package com.pistachio.system.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: Pengsy
 * @date: 2023/08/07 15:09
 * @description: SysDictItemVo
 */
@Data
public class SysDictItemVo {

    @Schema(description = "字典值", defaultValue = "1")
    private String value;

    @Schema(description = "字典标签", defaultValue = "女")
    private String label;
}

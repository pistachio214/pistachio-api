package com.pistachio.system.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: dict item create dto
 * @date: 2023/08/09 13:59
 * @author: Pengsy
 */
@Data
public class DictItemCreateRequest {

    @NotBlank(message = "类型不能为空")
    private String type;

    @NotNull(message = "字典id不能为空")
    private Long dictId;

    @NotBlank(message = "数据值不能为空")
    private String value;

    @NotBlank(message = "标签名不能为空")
    private String label;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotNull(message = "排序不能为空")
    private Integer sort;

    private String remarks;
}

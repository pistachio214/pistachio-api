package com.pistachio.system.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 创建字典数据分类
 * @date: 2023/08/09 11:31
 * @author: Pengsy
 */
@Data
public class DictCreateRequest {
    @NotBlank(message = "类型名称不能为空")
    private String type;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotNull(message = "字典类型不能为空")
    private Integer system;

    private String remarks;
}

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

    /**
     * 类型标识
     * @mock dict_type
     */
    @NotBlank(message = "类型标识不能为空")
    private String type;

    /**
     * 描述
     * @mock 该字典的简单描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 字典类型
     * @mock 12
     * @since 该字典类型的ID
     */
    @NotNull(message = "字典类型不能为空")
    private Integer system;

    /**
     * 备注
     */
    private String remarks;
}

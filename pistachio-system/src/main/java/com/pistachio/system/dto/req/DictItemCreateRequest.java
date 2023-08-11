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

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空")
    private String type;

    /**
     * 字典id
     *
     * @mock 10
     */
    @NotNull(message = "字典id不能为空")
    private Long dictId;

    /**
     * 字典项数据值
     *
     * @mock 1
     */
    @NotBlank(message = "数据值不能为空")
    private String value;

    /**
     * 字典项数据标签
     *
     * @mock 女
     */
    @NotBlank(message = "标签名不能为空")
    private String label;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /**
     * 备注
     */
    private String remarks;
}

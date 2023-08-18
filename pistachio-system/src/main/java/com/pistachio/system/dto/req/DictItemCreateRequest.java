package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: dict item create dto
 * @date: 2023/08/09 13:59
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class DictItemCreateRequest {

    @Schema(description = "类型", defaultValue = "123", required = true)
    @NotBlank(message = "类型不能为空")
    private String type;

    @Schema(description = "字典id", defaultValue = "10", required = true)
    @NotNull(message = "字典id不能为空")
    private Long dictId;

    @Schema(description = "字典项数据值", defaultValue = "1", required = true)
    @NotBlank(message = "数据值不能为空")
    private String value;

    @Schema(description = "字典项数据标签", defaultValue = "女", required = true)
    @NotBlank(message = "标签名不能为空")
    private String label;

    @Schema(description = "描述", defaultValue = "描述描述描述描述", required = true)
    @NotBlank(message = "描述不能为空")
    private String description;

    @Schema(description = "排序", defaultValue = "1", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "备注", defaultValue = "备注备注备注备注备注")
    private String remarks;
}

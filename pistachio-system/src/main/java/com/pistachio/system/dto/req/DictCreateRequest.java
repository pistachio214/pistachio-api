package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 创建字典数据分类
 * @date: 2023/08/09 11:31
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class DictCreateRequest {

    @Schema(description = "类型标识", defaultValue = "dict_type", required = true)
    @NotBlank(message = "类型标识不能为空")
    private String type;

    @Schema(description = "描述", defaultValue = "该字典的简单描述", required = true)
    @NotBlank(message = "描述不能为空")
    private String description;

    @Schema(description = "字典类型", defaultValue = "12", required = true)
    @NotNull(message = "字典类型不能为空")
    private Integer system;

    @Schema(description = "备注")
    private String remarks;
}

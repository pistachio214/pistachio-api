package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @date: 2023/08/10 16:49
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class RoleCreateRequest {

    @Schema(description = "角色名称", defaultValue = "商品管理员", required = true)
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(description = "角色编码,不可重复", defaultValue = "goods_manager", required = true)
    @NotBlank(message = "角色编码不能为空")
    private String code;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态", defaultValue = "1")
    private Integer status;
}

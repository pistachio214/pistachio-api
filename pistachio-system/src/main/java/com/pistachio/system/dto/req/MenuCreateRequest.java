package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/11 10:34
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
public class MenuCreateRequest {

    @Schema(description = "父级菜单ID,顶级菜单，则值为0", defaultValue = "1111", required = true)
    @NotNull(message = "父级菜单不能为空")
    private Long parentId;

    @Schema(description = "菜单名称", defaultValue = "menu name", required = true)
    @NotNull(message = "菜单名称不能为空")
    private String name;

    @Schema(description = "权限编码", defaultValue = "xxx", required = true)
    @NotNull(message = "权限编码不能为空")
    private String perms;

    @Schema(description = "icon")
    private String icon;

    @Schema(description = "path")
    private String path;

    @Schema(description = "component")
    private String component;

    @Schema(description = "菜单类型", defaultValue = "1", required = true)
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    @Schema(description = "菜单状态", defaultValue = "1", required = true)
    @NotNull(message = "菜单状态不能为空")
    private Integer status;

    @Schema(description = "序号", defaultValue = "1", required = true)
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
}

package com.pistachio.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:20
 * @description: 系统内部菜单数据传输对象
 */
@Data
public class SysMenuDto implements Serializable {

    @Schema(description = "菜单ID", defaultValue = "2")
    private Long id;

    @Schema(description = "菜单名称", defaultValue = "系统菜单名称")
    private String name;

    @Schema(description = "菜单标题", defaultValue = "系统菜单标题")
    private String title;

    @Schema(description = "ICON", defaultValue = "ICON")
    private String icon;

    @Schema(description = "指向地址", defaultValue = "/welcome")
    private String path;

    @Schema(description = "组件地址", defaultValue = "/welcome/index")
    private String component;

    @Schema(description = "子级菜单")
    private List<SysMenuDto> children = new ArrayList<>();

}

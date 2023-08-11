package com.pistachio.system.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/11 10:34
 * @author: Pengsy
 */
@Data
public class MenuCreateRequest {
    @NotNull(message = "父级菜单不能为空")
    private Long parentId;

    @NotNull(message = "菜单名称不能为空")
    private String name;

    @NotNull(message = "权限编码不能为空")
    private String perms;

    private String icon;

    private String path;

    private String component;

    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    @NotNull(message = "菜单状态不能为空")
    private Integer status;

    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
}

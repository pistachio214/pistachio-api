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

    /**
     * 父级菜单ID
     *
     * @mock 1111
     * @since 顶级菜单，则值为0
     */
    @NotNull(message = "父级菜单不能为空")
    private Long parentId;

    /**
     * 菜单名称
     * @mock menu name
     */
    @NotNull(message = "菜单名称不能为空")
    private String name;

    /**
     * 权限编码
     */
    @NotNull(message = "权限编码不能为空")
    private String perms;

    /**
     * icon
     */
    private String icon;

    /**
     * path
     */
    private String path;

    /**
     * component
     */
    private String component;

    /**
     * 菜单类型
     */
    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    /**
     * 菜单状态
     *
     * @mock 1
     */
    @NotNull(message = "菜单状态不能为空")
    private Integer status;

    /**
     * 序号
     */
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
}

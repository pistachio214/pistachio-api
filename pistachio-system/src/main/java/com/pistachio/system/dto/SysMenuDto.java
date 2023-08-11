package com.pistachio.system.dto;

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

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * name
     */
    private String name;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * ICON
     */
    private String icon;

    /**
     * 地址
     */
    private String path;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 子级菜单
     */
    private List<SysMenuDto> children = new ArrayList<>();

}

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

    private Long id;

    private String name;

    private String title;

    private String icon;

    private String path;

    private String component;

    private List<SysMenuDto> children = new ArrayList<>();

}

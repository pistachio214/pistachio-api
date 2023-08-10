package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/03 15:29
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_menu")
@Where(clause = "`is_delete` = 1")
public class SysMenuEntity extends BaseEntity implements Serializable {
    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name")
    private String name;

    /**
     * 菜单URL
     */
    @Column(name = "path")
    private String path;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @Column(name = "perms")
    private String perms;

    @Column(name = "component")
    private String component;

    /**
     * 类型 0：目录 1：菜单 2：按钮
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "status")
    private Integer status;

    @Transient
    @Column(updatable = false, insertable = false)
    private List<SysMenuEntity> children = new ArrayList<>();
}

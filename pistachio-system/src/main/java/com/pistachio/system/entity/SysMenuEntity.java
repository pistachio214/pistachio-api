package com.pistachio.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "父菜单ID，一级菜单为0", defaultValue = "0")
    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "parent_id")
    private Long parentId;

    @Schema(description = "菜单名称", defaultValue = "小李飞刀")
    @Column(name = "name")
    private String name;

    @Schema(description = "菜单URL", defaultValue = "/welcome")
    @Column(name = "path")
    private String path;

    @Schema(description = "授权(多个用逗号分隔，如：user:list,user:create)", defaultValue = "user:list")
    @Column(name = "perms")
    private String perms;

    @Schema(description = "组件地址", defaultValue = "/welcome/index")
    @Column(name = "component")
    private String component;

    @Schema(description = "类型 0：目录 1：菜单 2：按钮", defaultValue = "0")
    @Column(name = "type")
    private Integer type;

    @Schema(description = "菜单图标", defaultValue = "icon")
    @Column(name = "icon")
    private String icon;

    @Schema(description = "排序", defaultValue = "1")
    @Column(name = "order_num")
    private Integer orderNum;

    @Schema(description = "状态", defaultValue = "1")
    @Column(name = "status")
    private Integer status;

    @Schema(description = "子菜单列表")
    @Transient
    @Column(updatable = false, insertable = false)
    private List<SysMenuEntity> children = new ArrayList<>();
}

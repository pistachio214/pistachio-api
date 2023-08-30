package com.pistachio.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: Pengsy
 * @date: 2023/08/03 15:36
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_role_menu")
@Where(clause = "`is_delete` = 1")
public class SysRoleMenuEntity extends BaseEntity implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "role_id")
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "status")
    private Integer status;
}

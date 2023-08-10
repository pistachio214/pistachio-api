package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "status")
    private Integer status;
}

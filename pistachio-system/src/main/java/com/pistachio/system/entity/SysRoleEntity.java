package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: Pengsy
 * @date: 2023/08/03 11:38
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_role")
@Where(clause = "`is_delete` = 1")
public class SysRoleEntity extends BaseEntity implements Serializable {

    /**
     * 角色名称
     *
     * @mock 超级管理员
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色标识
     *
     * @mock super_manager
     */
    @Column(name = "code")
    private String code;

    /**
     * 状态
     *
     * @mock 1
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 备注
     *
     * @mock 超级管理员的备注
     */
    @Column(name = "remark")
    private String remark;

}

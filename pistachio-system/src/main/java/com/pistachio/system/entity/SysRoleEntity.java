package com.pistachio.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "角色名称", defaultValue = "超级管理员")
    @Column(name = "name")
    private String name;

    @Schema(description = "角色标识", defaultValue = "super_manager")
    @Column(name = "code")
    private String code;

    @Schema(description = "状态", defaultValue = "1")
    @Column(name = "status")
    private Integer status;

    @Schema(description = "备注", defaultValue = "超级管理员的备注")
    @Column(name = "remark")
    private String remark;

}

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

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remark")
    private String remark;
}

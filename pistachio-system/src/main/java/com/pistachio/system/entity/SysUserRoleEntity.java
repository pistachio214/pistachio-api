package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: Pengsy
 * @date: 2023/08/03 11:25
 * @description: 管理员角色实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_user_role")
@Where(clause = "`is_delete` = 1")
public class SysUserRoleEntity extends BaseEntity implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "status")
    private Integer status;
}

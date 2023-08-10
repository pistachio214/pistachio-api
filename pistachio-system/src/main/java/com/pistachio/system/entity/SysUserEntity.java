package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/03 10:41
 * @description: 系统管理员实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_user")
@Where(clause = "`is_delete` = 1")
public class SysUserEntity extends BaseEntity implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private Integer type;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "status")
    private Integer status;

    @Transient
    @Column(updatable = false, insertable = false)
    private List<SysRoleEntity> sysRoles = new ArrayList<>();

}

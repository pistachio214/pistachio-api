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

    /**
     * 账号
     *
     * @mock admin
     */
    @Column(name = "username")
    private String username;

    /**
     * 昵称
     *
     * @mock 萧十一郎
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 类型
     * @mock 1
     * @since 1管理员 2普通用户
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 头像
     * @mock https://www.sss.com/xx.png
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 邮箱
     * @mock 111111@163.com
     */
    @Column(name = "email")
    private String email;

    /**
     * city
     */
    @Column(name = "city")
    private String city;

    /**
     * 最后登录时间
     * @mock 2023-08-11 16:06
     */
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    /**
     * 状态
     * @mock 1
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 角色列表
     */
    @Transient
    @Column(updatable = false, insertable = false)
    private List<SysRoleEntity> sysRoles = new ArrayList<>();

}

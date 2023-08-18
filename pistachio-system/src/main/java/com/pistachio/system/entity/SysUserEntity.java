package com.pistachio.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "账号", defaultValue = "admin")
    @Column(name = "username")
    private String username;

    @Schema(description = "昵称", defaultValue = "萧十一郎")
    @Column(name = "nickname")
    private String nickname;

    @Schema(description = "密码")
    @Column(name = "password")
    private String password;

    @Schema(description = "类型: 1管理员 2普通用户", defaultValue = "1")
    @Column(name = "type")
    private Integer type;

    @Schema(description = "头像", defaultValue = "https://www.sss.com/xx.png")
    @Column(name = "avatar")
    private String avatar;

    @Schema(description = "邮箱", defaultValue = "111111@163.com")
    @Column(name = "email")
    private String email;

    @Schema(description = "city", defaultValue = "city")
    @Column(name = "city")
    private String city;

    @Schema(description = "最后登录时间", defaultValue = "2023-08-11 16:06")
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Schema(description = "状态", defaultValue = "1")
    @Column(name = "status")
    private Integer status;

    @Schema(description = "角色列表")
    @Transient
    @Column(updatable = false, insertable = false)
    private List<SysRoleEntity> sysRoles = new ArrayList<>();

}

package com.pistachio.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @Column(name = "account")
    private String account;

    @Schema(description = "用户名", defaultValue = "萧十一郎")
    @Column(name = "user_name")
    private String username;

    @Schema(description = "密码")
    @Column(name = "password")
    private String password;

    @Schema(description = "类型 1 管理员 2普通用户")
    @Column(name = "type")
    private Integer type;

    @Schema(description = "头像")
    @Column(name = "avatar")
    private String avatar;

    @Schema(description = "最后登录时间")
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Schema(description = "账号是否可用 默认为1(可用)")
    @Column(name = "enabled")
    private Integer enabled;

    @Schema(description = "是否过期 默认为1(没有过期)")
    @Column(name = "account_non_expired")
    private Integer accountNonExpired;

    @Schema(description = "账号是否锁定 默认为1(没有锁定)")
    @Column(name = "account_non_locked")
    private Integer accountNonLocked;

    @Schema(description = "证书(密码)是否过期 默认为1(没有过期)")
    @Column(name = "credentials_non_expired")
    private Integer CredentialsNonExpired;

}

package com.pistachio.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @description: 异常日志实体
 * @date: 2023/08/09 14:27
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_exception_log")
@Where(clause = "`is_delete` = 1")
public class SysExceptionLogEntity extends BaseEntity implements Serializable {

    @Schema(description = "请求方式", defaultValue = "GET")
    @Column(name = "oper_requ_method")
    private String operRequMethod;

    @Schema(description = "请求参数", defaultValue = "{}")
    @Column(name = "exc_requ_param")
    private String excRequParam;

    @Schema(description = "异常名称", defaultValue = "org.springframework.data.mapping.PropertyReferenceException")
    @Column(name = "exc_name")
    private String excName;

    @Schema(description = "异常信息", defaultValue = "org.springframework.data.mapping.PropertyReferenceException: 巴拉巴拉巴拉巴拉吧")
    @Column(name = "exc_message")
    private String excMessage;

    @Schema(description = "操作者id", defaultValue = "1")
    @Column(name = "oper_user_id")
    private Long operUserId;

    @Schema(description = "操作者名称", defaultValue = "萧十一郎")
    @Column(name = "oper_user_name")
    private String operUserName;

    @Schema(description = "操作方法", defaultValue = "com.pistachio.admin.controller.system.SysDictController.save")
    @Column(name = "oper_method")
    private String operMethod;

    @Schema(description = "请求URL", defaultValue = "/sys-dict/save")
    @Column(name = "oper_uri")
    private String operUri;

    @Schema(description = "请求IP", defaultValue = "127.0.0.1")
    @Column(name = "oper_ip")
    private String operIp;

    @Schema(description = "操作版号", defaultValue = "0.0.1")
    @Column(name = "oper_ver")
    private String operVer;
}

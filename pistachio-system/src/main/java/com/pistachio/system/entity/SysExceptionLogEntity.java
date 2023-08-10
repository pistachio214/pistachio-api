package com.pistachio.system.entity;

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

    @Column(name = "oper_requ_method")
    private String operRequMethod;

    @Column(name = "exc_requ_param")
    private String excRequParam;

    @Column(name = "exc_name")
    private String excName;

    @Column(name = "exc_message")
    private String excMessage;

    @Column(name = "oper_user_id")
    private Long operUserId;

    @Column(name = "oper_user_name")
    private String operUserName;

    @Column(name = "oper_method")
    private String operMethod;

    @Column(name = "oper_uri")
    private String operUri;

    @Column(name = "oper_ip")
    private String operIp;

    @Column(name = "oper_ver")
    private String operVer;
}

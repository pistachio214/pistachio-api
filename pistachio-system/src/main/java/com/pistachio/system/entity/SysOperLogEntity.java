package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: Pengsy
 * @date: 2023/08/02 16:53
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_oper_log")
@Where(clause = "`is_delete` = 1")
public class SysOperLogEntity extends BaseEntity implements Serializable {

    @Column(name = "oper_modul")
    private String operModul;

    @Column(name = "oper_type")
    private String operType;

    @Column(name = "oper_desc")
    private String operDesc;

    @Column(name = "oper_requ_method")
    private String operRequMethod;

    @Column(name = "oper_requ_param")
    private String operRequParam;

    @Column(name = "oper_resp_param")
    private String operRespParam;

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

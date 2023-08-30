package com.pistachio.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "功能模块", defaultValue = "字典模块 - 添加字典")
    @Column(name = "oper_modul")
    private String operModul;

    @Schema(description = "操作类型", defaultValue = "新增")
    @Column(name = "oper_type")
    private String operType;

    @Schema(description = "操作描述", defaultValue = "添加字典")
    @Column(name = "oper_desc")
    private String operDesc;

    @Schema(description = "请求方式", defaultValue = "POST")
    @Column(name = "oper_requ_method")
    private String operRequMethod;

    @Schema(description = "请求参数", defaultValue = "{\"type\":\"5555\",\"description\":\"5555\",\"system\":\"1\",\"remarks\":\"55555\"}")
    @Column(name = "oper_requ_param")
    private String operRequParam;

    @Schema(description = "返回参数", defaultValue = "{\"type\":\"5555\",\"description\":\"5555\",\"remarks\":\"55555\",\"system\":\"1\",\"id\":1689183353808355330,\"isDelete\":1}}")
    @Column(name = "oper_resp_param")
    private String operRespParam;

    @Schema(description = "操作者id", defaultValue = "1")
    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "oper_user_id")
    private Long operUserId;

    @Schema(description = "操作员名称", defaultValue = "萧十一郎")
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

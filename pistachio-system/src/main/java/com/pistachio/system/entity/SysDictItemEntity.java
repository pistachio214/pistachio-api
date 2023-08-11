package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: Pengsy
 * @date: 2023/08/07 15:21
 * @description: SysDictItemEntity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_dict_item")
@Where(clause = "`is_delete` = 1")
public class SysDictItemEntity extends BaseEntity implements Serializable {

    /**
     * 字典id
     */
    @Column(name = "dict_id")
    private Long dictId;

    /**
     * 数据值
     * @mock 1
     */
    @Column(name = "value")
    private String value;

    /**
     * 标签名
     * @mock 标签名称
     */
    @Column(name = "label")
    private String label;

    @Column(name = "type")
    private String type;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 备注
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

}

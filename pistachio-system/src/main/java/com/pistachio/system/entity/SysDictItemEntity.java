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

    @Schema(description = "字典id", defaultValue = "1688794906526482433")
    @Column(name = "dict_id")
    private Long dictId;

    @Schema(description = "数据值", defaultValue = "1")
    @Column(name = "value")
    private String value;

    @Schema(description = "标签名", defaultValue = "标签名称")
    @Column(name = "label")
    private String label;

    @Column(name = "type")
    private String type;

    @Schema(description = "描述")
    @Column(name = "description")
    private String description;

    @Schema(description = "备注")
    @Column(name = "remarks")
    private String remarks;

    @Schema(description = "排序", defaultValue = "1")
    @Column(name = "sort")
    private Integer sort;

}

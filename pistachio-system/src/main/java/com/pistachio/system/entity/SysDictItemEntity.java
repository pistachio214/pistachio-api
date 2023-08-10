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

    @Column(name = "dict_id")
    private Long dictId;

    @Column(name = "value")
    private String value;

    @Column(name = "label")
    private String label;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "sort")
    private Integer sort;

}

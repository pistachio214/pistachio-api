package com.pistachio.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/04 17:05
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_dict")
@Where(clause = "`is_delete` = 1")
public class SysDictEntity extends BaseEntity implements Serializable {

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "system")
    private String system;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dict_id")
    List<SysDictItemEntity> items;

}

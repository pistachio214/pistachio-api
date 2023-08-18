package com.pistachio.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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

    /**
     * 类型标识
     */
    @Schema(description = "类型标识", defaultValue = "dicts_type")
    @Column(name = "type")
    private String type;

    /**
     * 描述
     */
    @Schema(description = "描述", defaultValue = "字典类型名称")
    @Column(name = "description")
    private String description;

    /**
     * 备注
     */
    @Schema(description = "备注", defaultValue = "字典备注")
    @Column(name = "remarks")
    private String remarks;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型", defaultValue = "1")
    @Column(name = "system")
    private String system;

    /**
     * 字典项集合
     */
    @Schema(description = "字典项集合")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dict_id")
    List<SysDictItemEntity> items;

}

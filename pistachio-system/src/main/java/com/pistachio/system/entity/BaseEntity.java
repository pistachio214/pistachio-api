package com.pistachio.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: Pengsy
 * @date: 2023/08/03 11:49
 * @description: 公共实体
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     * ID
     */
    @Schema(description = "数据id", defaultValue = "1688794906526482433")
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @GenericGenerator(name = "snowFlakeIdGenerator", strategy = "com.pistachio.system.generator.SnowFlakeIdGenerator")
    @GeneratedValue(generator = "snowFlakeIdGenerator")
    private Long id;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", defaultValue = "2023-08-18 10:22:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", defaultValue = "2023-08-18 10:22:59")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "删除标识", defaultValue = "1")
    @JsonIgnore
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 预处理，删除数据时修改的数据
     */
    @PreRemove
    public void preRemove() {
        isDelete = 0;
    }

    /**
     * 预处理新增数据时，默认赋值
     */
    @PrePersist
    public void prePersist() {
        if (isDelete == null) {
            isDelete = 1;
        }
    }

}

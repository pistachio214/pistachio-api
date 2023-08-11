package com.pistachio.system.dto.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/07 15:10
 * @description: SysDictAndItemVo
 */
@Data
public class SysDictAndItemVo {

    /**
     * 字典ID
     */
    private Long id;

    /**
     * 类型标识
     * @mock dict_type
     */
    private String type;

    /**
     * 字典名称
     * @mock 性别
     * @since 例如: 性别
     */
    private String name;

    /**
     * 字典项列表
     */
    List<SysDictItemVo> items;
}

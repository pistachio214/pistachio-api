package com.pistachio.system.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/07 15:10
 * @description: SysDictAndItemVo
 */
@Data
public class SysDictAndItemVo {

    private Long id;

    private String type;

    private String name;

    List<SysDictItemVo> items;
}

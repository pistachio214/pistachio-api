package com.pistachio.system.dto.vo;

import com.pistachio.system.entity.SysRoleEntity;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @date: 2023/08/10 16:07
 * @author: Pengsy
 */
@Data
public class SysRoleAndMenuIdsVo extends SysRoleEntity {

    private List<Long> menuIds;

}

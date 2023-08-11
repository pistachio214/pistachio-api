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

    /**
     * 角色的菜单id列表
     *
     * @mock [1, 2, 3, 4, 5, 6, 7, 8, 9]
     */
    private List<Long> menuIds;

}

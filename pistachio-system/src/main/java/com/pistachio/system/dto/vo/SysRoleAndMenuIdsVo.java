package com.pistachio.system.dto.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pistachio.system.entity.SysRoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @date: 2023/08/10 16:07
 * @author: Pengsy
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleAndMenuIdsVo extends SysRoleEntity {

    @Schema(description = "角色的菜单id列表", defaultValue = "[1, 2, 3, 4, 5, 6, 7, 8, 9]")
    private List<String> menuIds;

}

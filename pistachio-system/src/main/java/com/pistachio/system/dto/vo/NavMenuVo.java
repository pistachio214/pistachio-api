package com.pistachio.system.dto.vo;

import com.pistachio.system.dto.SysMenuDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:38
 * @description: 格式化输出菜单
 */
@AllArgsConstructor
@Data
public class NavMenuVo {

    @Schema(description = "权限标识符", defaultValue = "[\"sys:user:delete\",\"sys:user:repass\"]")
    private String[] authoritys;

    @Schema(description = "菜单数据列表")
    private List<SysMenuDto> navs;

    @Schema(description = "用户信息")
    private NavUserVo user;
}

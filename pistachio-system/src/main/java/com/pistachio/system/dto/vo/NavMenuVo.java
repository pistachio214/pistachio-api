package com.pistachio.system.dto.vo;

import com.pistachio.system.dto.SysMenuDto;
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

    /**
     * 权限标识符
     *
     * @mock ["sys:user:delete","sys:user:repass"]
     * @since String[] 数组形式的权限标识符
     */
    private String[] authoritys;

    /**
     * 菜单数据列表
     */
    private List<SysMenuDto> navs;

    /**
     * 用户信息
     */
    private NavUserVo user;
}

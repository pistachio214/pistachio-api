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

    private String[] authoritys;

    private List<SysMenuDto> navs;

    private Map<String, String> user;
}

package com.pistachio.system.service;

import com.pistachio.system.dto.SysMenuDto;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:18
 * @description: ISysMenuService
 */
public interface ISysMenuService {

    List<SysMenuDto> getCurrentUserNav(Long id);
}

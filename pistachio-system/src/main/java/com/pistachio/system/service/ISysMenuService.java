package com.pistachio.system.service;

import com.pistachio.system.dto.SysMenuDto;
import com.pistachio.system.dto.req.MenuCreateRequest;
import com.pistachio.system.dto.req.MenuUpdateRequest;
import com.pistachio.system.dto.vo.SysMenuListTreeVo;
import com.pistachio.system.entity.SysMenuEntity;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:18
 * @description: ISysMenuService
 */
public interface ISysMenuService {

    List<SysMenuDto> getCurrentUserNav(Long id);

    SysMenuListTreeVo tree();

    SysMenuEntity create(MenuCreateRequest request);

    void delete(Long id);

    SysMenuEntity edit(MenuUpdateRequest request);

     SysMenuEntity findById(Long id);
}

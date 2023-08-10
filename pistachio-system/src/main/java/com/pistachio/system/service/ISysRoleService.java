package com.pistachio.system.service;

import com.pistachio.system.dto.req.RoleCreateRequest;
import com.pistachio.system.dto.req.RoleListRequest;
import com.pistachio.system.dto.req.RoleUpdateRequest;
import com.pistachio.system.dto.vo.SysRoleAndMenuIdsVo;
import com.pistachio.system.entity.SysRoleEntity;
import org.springframework.data.domain.Page;

/**
 * @description: 角色服务
 * @date: 2023/08/09 16:43
 * @author: Pengsy
 */
public interface ISysRoleService {

    Page<SysRoleEntity> selectRolePage(RoleListRequest request);

    SysRoleAndMenuIdsVo detail(Long id);

    void permRoleMenu(Long roleId, Long[] menuIds);

    SysRoleEntity create(RoleCreateRequest request);

    SysRoleEntity update(RoleUpdateRequest request);

    void delete(Long id);

}

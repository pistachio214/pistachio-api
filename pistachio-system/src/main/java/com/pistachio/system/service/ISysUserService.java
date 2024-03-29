package com.pistachio.system.service;

import com.pistachio.system.dto.req.UserChangePasswordRequest;
import com.pistachio.system.dto.req.UserCreateRequest;
import com.pistachio.system.dto.req.UserEditRequest;
import com.pistachio.system.dto.req.UserListRequest;
import com.pistachio.system.entity.SysUserEntity;
import org.springframework.data.domain.Page;

/**
 * @author: Pengsy
 * @date: 2023/08/01 17:24
 * @description:
 */
public interface ISysUserService {

    String getUserAuthorityInfo(Long userId);

    Page<SysUserEntity> selectUserPage(UserListRequest request);

    /**
     * 删除管理员原本的角色关系
     *
     * @param userId user id
     */
    void removeRoleRelation(Long userId);

    SysUserEntity findById(Long id);

    void clearUserAuthorityInfoByRoleId(Long roleId);

    void clearUserAuthorityInfoByMenuId(Long menuId);

    void rolePerm(Long userId, Long[] roleIds);

    void clearUserAuthorityInfo(Long id);

    void clearUserRoleInfo(Long id);

    void restPassword(Long id, String password);

    void deleteSysUser(Long id);

    SysUserEntity save(UserCreateRequest request, String password);

    SysUserEntity editSysUser(UserEditRequest request);

}

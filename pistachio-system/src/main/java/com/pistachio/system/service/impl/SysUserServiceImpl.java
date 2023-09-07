package com.pistachio.system.service.impl;

import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.core.ConvertCore;
import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.R;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.system.dto.req.UserChangePasswordRequest;
import com.pistachio.system.dto.req.UserCreateRequest;
import com.pistachio.system.dto.req.UserEditRequest;
import com.pistachio.system.dto.req.UserListRequest;
import com.pistachio.system.entity.SysMenuEntity;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.entity.SysUserRoleEntity;
import com.pistachio.system.repository.SysMenuRepository;
import com.pistachio.system.repository.SysRoleRepository;
import com.pistachio.system.repository.SysUserRepository;
import com.pistachio.system.repository.SysUserRoleRepository;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: Pengsy
 * @date: 2023/08/01 17:24
 * @description:
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String getUserAuthorityInfo(Long userId) {
        String authority = "";

        SysUserEntity sysUser = sysUserRepository.findFirstById(userId).orElse(null);
        if (sysUser != null) {
            String key = "GrantedAuthority:" + sysUser.getId();

            List<SysRoleEntity> roles = sysRoleRepository.getUserAuthority(userId);
            if (roles.size() > 0) {
                String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
            }

            List<Long> menuIds = sysUserRepository.getNavMenuIds(userId);
            if (menuIds.size() > 0) {
                List<SysMenuEntity> menus = sysMenuRepository.listByIds(menuIds);
                String menuPerms = menus.stream().map(SysMenuEntity::getPerms).collect(Collectors.joining(","));
                authority = authority.concat(menuPerms);
            }

            redisCache.setCacheObject(key, authority, 60, TimeUnit.MINUTES);
        }


        return authority;
    }

    @Override
    public Page<SysUserEntity> selectUserPage(UserListRequest request) {
        Page<SysUserEntity> data = sysUserRepository.findAll((Specification<SysUserEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtil.isEmpty(request.getUsername())) {
                predicateList.add(criteriaBuilder.like(root.get("username"), "%" + request.getUsername() + "%"));
            }

            return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
        }, PageRequest.of(request.getCurrent(), request.getSize(), Sort.by("createdAt").descending()));

        data.getContent().forEach(u -> {
            u.setSysRoles(sysRoleRepository.listRolesByUserId(u.getId()));
        });

        return data;
    }

    @Transactional
    @Override
    public void removeRoleRelation(Long userId) {
        sysUserRoleRepository.deleteByUserId(userId);
    }

    @Override
    public SysUserEntity findById(Long id) {
        return sysUserRepository.findFirstById(id).orElseThrow(() -> new ServiceException("管理员数据不存在"));
    }

    @Transactional
    @Override
    public void clearUserAuthorityInfoByRoleId(Long roleId) {
        List<SysUserEntity> sysUsers = sysUserRepository.selectBySysUserRoleRoleId(roleId);
        sysUsers.forEach(u -> {
            clearUserAuthorityInfoByMenuId(u.getId());
        });

    }

    @Override
    public void clearUserAuthorityInfoByMenuId(Long menuId) {
        List<SysUserEntity> sysUsers = sysUserRepository.listByMenuId(menuId);
        sysUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getId());
        });
    }

    @Transactional
    @Override
    public void rolePerm(Long userId, Long[] roleIds) {
        List<SysUserRoleEntity> userRoles = new ArrayList<>();
        Arrays.stream(roleIds).forEach(r -> {
            SysUserRoleEntity sysUserRole = new SysUserRoleEntity();
            sysUserRole.setRoleId(r);
            sysUserRole.setUserId(userId);
            sysUserRole.setStatus(UserConstants.STATUS_ON);

            userRoles.add(sysUserRole);
        });

        // 先删除原来的记录，再保存新的信息
        removeRoleRelation(userId);
        sysUserRoleRepository.saveAll(userRoles);

        // 删除缓存
        SysUserEntity sysUser = findById(userId);
        clearUserAuthorityInfo(sysUser.getId());

        clearUserRoleInfo(userId);
    }

    @Override
    public void clearUserAuthorityInfo(Long id) {
        String key = "GrantedAuthority:" + id;
        if (redisCache.hasKey(key)) {
            redisCache.deleteObject(key);
        }
    }

    @Override
    public void clearUserRoleInfo(Long id) {
        String roleCacheKey = "GrantedRole:" + id;
        if (redisCache.hasKey(roleCacheKey)) {
            redisCache.deleteObject(roleCacheKey);
        }
    }

    @Transactional
    @Override
    public void restPassword(Long id, String password) {
        SysUserEntity sysUser = findById(id);
        sysUser.setPassword(password);

        save(sysUser);
    }

    @Transactional
    @Override
    public void deleteSysUser(Long id) {
        sysUserRepository.softDeleteSysUser(id);
        sysUserRoleRepository.deleteByUserId(id);
    }

    @Override
    public SysUserEntity save(UserCreateRequest request, String password) {
        SysUserEntity entity = ConvertCore.map(request, SysUserEntity.class);
        entity.setPassword(password);
        entity.setAvatar(UserConstants.DEFAULT_AVATAR);

        return save(entity);
    }

    private SysUserEntity save(SysUserEntity entity) {
        return sysUserRepository.save(entity);
    }

    @Override
    public SysUserEntity editSysUser(UserEditRequest request) {
        SysUserEntity userEntity = findById(request.getId());

        userEntity.setAvatar(request.getAvatar());
        userEntity.setEmail(request.getEmail());
        userEntity.setNickname(request.getNickname());

        return save(userEntity);
    }

    @Override
    public void changePassword(UserChangePasswordRequest request) {


    }
}

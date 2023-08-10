package com.pistachio.framework.security.handle;

import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.entity.SysUserRoleEntity;
import com.pistachio.system.repository.SysRoleRepository;
import com.pistachio.system.repository.SysUserRoleRepository;
import com.pistachio.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/03 14:31
 * @description: 实现权限相关字段获取
 */
@Component
public class SysUserAuthorityHandle {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private ISysUserService iSysUserService;

    public String getUserAuthorityInfo(Long userId) {
        return iSysUserService.getUserAuthorityInfo(userId);
    }

    public List<String> getUserRoleList(Long userId) {
        String roleCacheKey = "GrantedRole:" + userId;
        List<SysUserRoleEntity> list = sysUserRoleRepository.getAllByUserId(userId);

        List<String> listRole = new ArrayList<>();

        list.forEach(sysUserRole -> {
            SysRoleEntity sysRole = sysRoleRepository.getById(sysUserRole.getRoleId());
            listRole.add(sysRole.getCode());
        });

        if (listRole.size() > 0) {
            redisCache.setCacheObject(roleCacheKey, StringUtils.join(listRole.toArray(), ","));
        }

        return listRole;
    }
}

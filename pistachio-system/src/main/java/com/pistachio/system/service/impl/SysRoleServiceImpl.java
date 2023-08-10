package com.pistachio.system.service.impl;

import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.system.dto.req.RoleListRequest;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.entity.SysUserRoleEntity;
import com.pistachio.system.repository.SysRoleRepository;
import com.pistachio.system.repository.SysUserRoleRepository;
import com.pistachio.system.service.ISysRoleService;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 角色服务的实现
 * @date: 2023/08/09 16:43
 * @author: Pengsy
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Page<SysRoleEntity> selectRolePage(RoleListRequest request) {
        return sysRoleRepository.findAll((Specification<SysRoleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (!StringUtil.isEmpty(request.getName())) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            if (!StringUtil.isEmpty(request.getCode())) {
                predicateList.add(criteriaBuilder.like(root.get("code"), "%" + request.getCode() + "%"));
            }

            return query.where(predicateList.toArray(new Predicate[0])).getRestriction();
        }, PageRequest.of(request.getCurrent(), request.getSize(), Sort.by("createdAt").descending()));
    }

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
        iSysUserService.removeRoleRelation(userId);
        sysUserRoleRepository.saveAll(userRoles);

        // 删除缓存
        SysUserEntity sysUser = iSysUserService.findById(userId);
        iSysUserService.clearUserAuthorityInfo(sysUser.getId());

        iSysUserService.clearUserRoleInfo(userId);
    }

}

package com.pistachio.system.service.impl;

import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.core.ConvertCore;
import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.system.dto.req.RoleCreateRequest;
import com.pistachio.system.dto.req.RoleListRequest;
import com.pistachio.system.dto.req.RoleUpdateRequest;
import com.pistachio.system.dto.vo.SysRoleAndMenuIdsVo;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.entity.SysRoleMenuEntity;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.entity.SysUserRoleEntity;
import com.pistachio.system.repository.SysRoleMenuRepository;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private ISysUserService iSysUserService;

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
    public SysRoleAndMenuIdsVo detail(Long id) {
        SysRoleEntity roleEntity = sysRoleRepository.findFirstById(id).orElseThrow(() -> new ServiceException("角色信息不存在"));

        List<SysRoleMenuEntity> roleMenus = sysRoleMenuRepository.findAllByRoleId(id);
        List<Long> menuIds = roleMenus.stream().map(SysRoleMenuEntity::getMenuId).collect(Collectors.toList());

        SysRoleAndMenuIdsVo vo = ConvertCore.map(roleEntity, SysRoleAndMenuIdsVo.class);
        vo.setMenuIds(menuIds);

        return vo;
    }

    @Transactional
    @Override
    public void permRoleMenu(Long roleId, Long[] menuIds) {
        List<SysRoleMenuEntity> sysRoleMenus = new ArrayList<>();

        Arrays.stream(menuIds).forEach(menuId -> {
            SysRoleMenuEntity roleMenu = new SysRoleMenuEntity();

            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            roleMenu.setStatus(1);

            sysRoleMenus.add(roleMenu);
        });

        // 删除原本的记录,再保存新的信息
        sysRoleMenuRepository.softDeleteByRoleId(roleId);
        sysRoleMenuRepository.saveAll(sysRoleMenus);

        // 删除缓存
        iSysUserService.clearUserAuthorityInfoByRoleId(roleId);
    }

    @Override
    public SysRoleEntity create(RoleCreateRequest request) {
        SysRoleEntity entity = ConvertCore.map(request, SysRoleEntity.class);

        if (sysRoleRepository.findFirstByCode(entity.getCode()).isPresent()) {
            throw new ServiceException("唯一编码已存在");
        }

        return save(entity);
    }

    @Override
    public SysRoleEntity update(RoleUpdateRequest request) {
        SysRoleEntity entity = sysRoleRepository.findFirstById(request.getId()).orElseThrow(() -> new ServiceException("角色不存在"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setStatus(request.getStatus());
        entity.setRemark(request.getRemark());

        SysRoleEntity sysRoleEntity = save(entity);

        // 更新缓存
        iSysUserService.clearUserAuthorityInfoByRoleId(entity.getId());

        return sysRoleEntity;
    }

    private SysRoleEntity save(SysRoleEntity entity) {
        return sysRoleRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        sysRoleRepository.softDeleteById(id);

        //删除中间表数据
        sysUserRoleRepository.softDeleteByRoleId(id);
        sysRoleMenuRepository.softDeleteByRoleId(id);

        // 更新缓存
        iSysUserService.clearUserAuthorityInfoByRoleId(id);
    }
}

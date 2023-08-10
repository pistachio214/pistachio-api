package com.pistachio.system.service.impl;

import com.pistachio.system.dto.SysMenuDto;
import com.pistachio.system.entity.SysMenuEntity;
import com.pistachio.system.repository.SysMenuRepository;
import com.pistachio.system.repository.SysUserRepository;
import com.pistachio.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:19
 * @description: ISysMenuServiceImpl
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Override
    public List<SysMenuDto> getCurrentUserNav(Long id) {
        List<Long> menuIds = sysUserRepository.getNavMenu(id);
        List<SysMenuEntity> menus = generatorMenuList(menuIds);

        // 转树状结构
        List<SysMenuEntity> menuTree = buildTreeMenu(menus);

        // 实体转DTO
        return convert(menuTree);
    }

    /**
     * 递归处理当前权限的上级
     *
     * @param ids 已知权限的id
     * @return List<SysMenu>
     */
    private List<SysMenuEntity> generatorMenuList(List<Long> ids) {
        List<SysMenuEntity> menus = new ArrayList<>();

        ids.forEach(id -> {
            menus.addAll(generatorMenu(id));
        });

        // 根据id去重
        return menus.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(SysMenuEntity::getId))), ArrayList::new));
    }

    private List<SysMenuEntity> generatorMenu(Long id) {
        List<SysMenuEntity> menuArray = new ArrayList<>();

        SysMenuEntity sysMenu = sysMenuRepository.findFirstById(id).orElse(null);
        if (sysMenu != null) {
            if (sysMenu.getParentId() != 0) {
                menuArray.addAll(generatorMenu(sysMenu.getParentId()));
            }

            Integer[] indexArray = new Integer[]{0, 1};
            if (Arrays.asList(indexArray).contains(sysMenu.getType())) {
                menuArray.add(sysMenu);
            }
        }

        return menuArray;
    }

    private List<SysMenuDto> convert(List<SysMenuEntity> menuTree) {
        List<SysMenuDto> menuDtos = new ArrayList<>();

        menuTree.forEach(m -> {
            SysMenuDto dto = new SysMenuDto();

            dto.setId(m.getId());
            dto.setName(m.getPerms());
            dto.setTitle(m.getName());
            dto.setComponent(m.getComponent());
            dto.setPath(m.getPath());
            dto.setIcon(m.getIcon());

            if (m.getChildren().size() > 0) {
                // 子节点调用当前方法进行再次转换
                dto.setChildren(convert(m.getChildren()));
            }

            menuDtos.add(dto);
        });

        return menuDtos;
    }

    private List<SysMenuEntity> buildTreeMenu(List<SysMenuEntity> menus) {
        List<SysMenuEntity> finalMenus = new ArrayList<>();
        // 先各自寻找到各自的孩子
        for (SysMenuEntity menu : menus) {
            for (SysMenuEntity e : menus) {
                if (menu.getId().equals(e.getParentId())) {
                    menu.getChildren().add(e);
                }
            }
            // 提取出父节点
            if (menu.getParentId() == 0L) {
                finalMenus.add(menu);
            }
        }

        return finalMenus;
    }
}

package com.pistachio.admin;

import com.pistachio.system.entity.SysMenuEntity;
import com.pistachio.system.entity.SysRoleMenuEntity;
import com.pistachio.system.repository.SysMenuRepository;
import com.pistachio.system.repository.SysRoleMenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 关于权限部分的测试案例
 * @date: 2023/08/25 14:11
 * @author: Pengsy
 */
@SpringBootTest
public class PermissTests {

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Test
    void restAdminPermiss() {
        List<SysMenuEntity> menuList = sysMenuRepository.findAll();

        List<SysRoleMenuEntity> roleMenuEntityList = new ArrayList<>();
        menuList.forEach(menu -> {

            System.out.println(menu.toString());

            SysRoleMenuEntity entity = new SysRoleMenuEntity();
            entity.setRoleId(1L);
            entity.setMenuId(menu.getId());
            entity.setStatus(1);

            roleMenuEntityList.add(entity);
        });

        if (roleMenuEntityList.size() > 0) {
            sysRoleMenuRepository.saveAll(roleMenuEntityList);
        }
    }

}

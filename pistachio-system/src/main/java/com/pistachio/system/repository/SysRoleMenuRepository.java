package com.pistachio.system.repository;

import com.pistachio.system.entity.SysRoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Pengsy
 * @date: 2023/08/03 15:37
 * @description: SysRoleMenuRepository
 */
@Repository
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuEntity, Long> {
}

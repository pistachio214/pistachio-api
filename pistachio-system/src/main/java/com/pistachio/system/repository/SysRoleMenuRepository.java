package com.pistachio.system.repository;

import com.pistachio.system.entity.SysRoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/03 15:37
 * @description: SysRoleMenuRepository
 */
@Repository
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuEntity, Long> {

    List<SysRoleMenuEntity> findAllByRoleId(Long roleId);

    @Modifying
    @Query("update SysRoleMenuEntity as s set s.isDelete = 0 where s.roleId = ?1")
    void softDeleteByRoleId(Long roleId);

    @Modifying
    @Query("update SysRoleMenuEntity as s set s.isDelete = 0 where s.menuId = ?1")
    void softDeleteByMenuId(Long menuId);

    @Modifying
    @Query("delete from SysRoleMenuEntity  as s where s.roleId = ?1")
    void deleteAllByRoleId(Long roleId);
}

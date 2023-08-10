package com.pistachio.system.repository;

import com.pistachio.system.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Pengsy
 * @date: 2023/08/03 10:48
 * @description: SysUserRepository
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUserEntity, Long>, JpaSpecificationExecutor<SysUserEntity> {

    Optional<SysUserEntity> findFirstByUsername(String username);

    Optional<SysUserEntity> findFirstById(Long id);

    @Query("SELECT DISTINCT rm.menuId from SysUserRoleEntity AS ur LEFT JOIN SysRoleMenuEntity as rm on ur.roleId = rm.roleId where ur.userId = ?1 and ur.isDelete = 1 and rm.isDelete = 1")
    List<Long> getNavMenuIds(Long userId);

    @Query("select distinct rm.menuId from SysUserRoleEntity as ur left join SysRoleMenuEntity as rm on ur.roleId = rm.roleId left join SysMenuEntity as sm on sm.id = rm.menuId where ur.userId = ?1 and ur.isDelete = 1 and rm.isDelete = 1 and sm.isDelete = 1")
    List<Long> getNavMenu(Long id);

    List<SysUserEntity> findAll();

}

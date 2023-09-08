package com.pistachio.system.repository;

import com.pistachio.system.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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

    Optional<SysUserEntity> findFirstByUsernameAndType(String username, Integer type);

    Optional<SysUserEntity> findFirstById(Long id);

    @Query("SELECT DISTINCT rm.menuId from SysUserRoleEntity AS ur LEFT JOIN SysRoleMenuEntity as rm on ur.roleId = rm.roleId where ur.userId = ?1 and ur.isDelete = 1 and rm.isDelete = 1")
    List<Long> getNavMenuIds(Long userId);

    @Query("select distinct rm.menuId from SysUserRoleEntity as ur left join SysRoleMenuEntity as rm on ur.roleId = rm.roleId left join SysMenuEntity as sm on sm.id = rm.menuId where ur.userId = ?1 and ur.isDelete = 1 and rm.isDelete = 1 and sm.isDelete = 1")
    List<Long> getNavMenu(Long id);

    List<SysUserEntity> findAll();

    @Modifying
    @Query("update SysUserEntity as s set s.isDelete = 0 where s.id = ?1")
    void softDeleteSysUser(Long id);

    @Query("select s from SysUserEntity as s where s.isDelete = 1 and s.id in (select r.userId from SysUserRoleEntity as r where r.isDelete = 1 and r.roleId = ?1)")
    List<SysUserEntity> selectBySysUserRoleRoleId(Long roleId);

    @Query(nativeQuery = true, value = "select distinct `su`.* from `sys_user_role` as `ur` left join `sys_role_menu` as `rm` on `ur`.`role_id` = `rm`.`role_id` right join `sys_user` as `su` on `ur`.`user_id` = `su`.`id` where `rm`.`menu_id` = ?1 and `ur`.`is_delete` = 1 and `rm`.`is_delete` = 1 and `su`.`is_delete` = 1")
    List<SysUserEntity> listByMenuId(Long menuId);
}

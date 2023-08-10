package com.pistachio.system.repository;

import com.pistachio.system.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Pengsy
 * @date: 2023/08/03 11:40
 * @description: SysRoleRepository
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Long>, JpaSpecificationExecutor<SysRoleEntity> {

    @Query("select a from SysRoleEntity as a where a.isDelete = 1 and a.id in (select b.roleId from SysUserRoleEntity as b where b.userId = ?1 and b.isDelete = 1)")
    List<SysRoleEntity> getUserAuthority(Long userId);

    @Query("select r from SysRoleEntity as r where r.id in (select ur.roleId from SysUserRoleEntity as ur where ur.userId = ?1 and ur.isDelete = 1) and r.isDelete = 1")
    List<SysRoleEntity> listRolesByUserId(Long userId);

    Optional<SysRoleEntity> findFirstById(Long id);

    Optional<SysRoleEntity> findFirstByCode(String code);

    @Modifying
    @Query("update SysRoleEntity as s set s.isDelete = 0 where s.id = ?1")
    void softDeleteById(Long id);
}

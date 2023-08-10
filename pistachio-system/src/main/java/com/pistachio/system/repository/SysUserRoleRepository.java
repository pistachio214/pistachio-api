package com.pistachio.system.repository;

import com.pistachio.system.entity.SysUserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/03 11:27
 * @description: SysUserRoleRepository
 */
@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRoleEntity, Long> {

    List<SysUserRoleEntity> getAllByUserId(Long userId);

    @Modifying
    @Query("update SysUserRoleEntity as s set s.isDelete = 0 where s.userId = ?1")
    void deleteByUserId(Long userId);


}

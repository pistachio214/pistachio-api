package com.pistachio.system.repository;

import com.pistachio.system.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author: Pengsy
 * @date: 2023/08/03 15:34
 * @description: SysMenuRepository
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenuEntity, Long> {

    @Query("select e from SysMenuEntity as e where e.id in ?1 and e.isDelete = 1")
    List<SysMenuEntity> listByIds(List<Long> ids);

    Optional<SysMenuEntity> findFirstById(Long id);

    Integer countAllByParentId(Long parentId);

    @Modifying
    @Query("update SysMenuEntity as s set s.isDelete = 0 where s.id = ?1")
    void softDeleteById(Long id);
}

package com.pistachio.system.repository;

import com.pistachio.system.entity.SysDictItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/07 15:22
 * @description: SysDictItemRepository
 */
@Repository
public interface SysDictItemRepository extends JpaRepository<SysDictItemEntity, Long> {

    List<SysDictItemEntity> findByDictId(Long dictId);

    Page<SysDictItemEntity> findByDictId(Long dictId, Pageable pageable);

    @Modifying
    @Query("update SysDictItemEntity as s set s.isDelete = 0 where s.id = ?1")
    void softDelete(Long id);
}

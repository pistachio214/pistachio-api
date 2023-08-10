package com.pistachio.system.repository;

import com.pistachio.system.entity.SysDictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Pengsy
 * @date: 2023/08/04 17:11
 * @description: SysDictRepository
 */
@Repository
public interface SysDictRepository extends JpaRepository<SysDictEntity, Long>, JpaSpecificationExecutor<SysDictEntity> {

    Optional<SysDictEntity> findFirstByType(String type);

    @Modifying
    @Query("update SysDictEntity as d set d.isDelete = 0 where d.id = ?1")
    void softDelete(Long id);

}

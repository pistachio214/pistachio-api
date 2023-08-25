package com.pistachio.system.repository;

import com.pistachio.system.entity.SysOperLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: Pengsy
 * @date: 2023/08/02 17:00
 * @description: SysOperLogRepository
 */
@Repository
public interface SysOperLogRepository extends JpaRepository<SysOperLogEntity, Long>, JpaSpecificationExecutor<SysOperLogEntity> {
}

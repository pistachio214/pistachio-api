package com.pistachio.system.repository;

import com.pistachio.system.entity.SysExceptionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @date: 2023/08/09 14:30
 * @author: Pengsy
 */
@Repository
public interface SysExceptionLogRepository extends JpaRepository<SysExceptionLogEntity, Long> {
}

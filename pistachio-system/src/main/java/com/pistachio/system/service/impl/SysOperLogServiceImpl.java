package com.pistachio.system.service.impl;

import com.pistachio.system.entity.SysExceptionLogEntity;
import com.pistachio.system.entity.SysOperLogEntity;
import com.pistachio.system.repository.SysExceptionLogRepository;
import com.pistachio.system.repository.SysOperLogRepository;
import com.pistachio.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: SysOperLogServiceImpl
 * @date: 2023/08/09 14:23
 * @author: Pengsy
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Autowired
    private SysOperLogRepository sysOperLogRepository;

    @Autowired
    private SysExceptionLogRepository sysExceptionLogRepository;

    @Override
    public SysOperLogEntity save(SysOperLogEntity entity) {
        return sysOperLogRepository.save(entity);
    }

    @Override
    public SysExceptionLogEntity save(SysExceptionLogEntity entity) {
        return sysExceptionLogRepository.save(entity);
    }
}

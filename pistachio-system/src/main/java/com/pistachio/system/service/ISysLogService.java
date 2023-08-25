package com.pistachio.system.service;

import com.pistachio.system.dto.req.ExceptionLogListRequest;
import com.pistachio.system.dto.req.OperLogListRequest;
import com.pistachio.system.entity.SysExceptionLogEntity;
import com.pistachio.system.entity.SysOperLogEntity;
import org.springframework.data.domain.Page;

/**
 * @description: 日志操作服务
 * @date: 2023/08/09 14:22
 * @author: Pengsy
 */
public interface ISysLogService {

    SysOperLogEntity save(SysOperLogEntity entity);

    SysExceptionLogEntity save(SysExceptionLogEntity entity);

    Page<SysOperLogEntity> operPageLists(OperLogListRequest request);

    SysOperLogEntity getOperLogById(Long id);

    Page<SysExceptionLogEntity> exceptionPageLists(ExceptionLogListRequest request);

    SysExceptionLogEntity getExceptionLogById(Long id);

}

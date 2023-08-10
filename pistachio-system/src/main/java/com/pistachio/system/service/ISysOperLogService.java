package com.pistachio.system.service;

import com.pistachio.system.entity.SysExceptionLogEntity;
import com.pistachio.system.entity.SysOperLogEntity;
import com.pistachio.system.entity.SysUserEntity;

/**
 * @description: 日志操作服务
 * @date: 2023/08/09 14:22
 * @author: Pengsy
 */
public interface ISysOperLogService {

    SysOperLogEntity save(SysOperLogEntity entity);

    SysExceptionLogEntity save(SysExceptionLogEntity entity);

}

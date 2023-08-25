package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.utils.R;
import com.pistachio.system.dto.req.ExceptionLogListRequest;
import com.pistachio.system.entity.SysExceptionLogEntity;
import com.pistachio.system.service.ISysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 异常日志
 * @date: 2023/08/25 10:22
 * @author: Pengsy
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "异常操作日志模块")
@RestController
@RequestMapping("/developer-exception-log")
public class SysExceptionLogController {

    @Autowired
    private ISysLogService iSysLogService;

    @Operation(summary = "异常操作日志 - 列表", description = "权限 [ developer:exception:log:list ]")
    @GetMapping("/list")
    @SaCheckPermission("developer:exception:log:list")
    public R<Page<SysExceptionLogEntity>> list(ExceptionLogListRequest request) {
        return R.success(iSysLogService.exceptionPageLists(request));
    }

    @Operation(summary = "异常操作日志 - 详情", description = "权限 [ developer:exception:log:info ]")
    @GetMapping("/{id}")
    @SaCheckPermission("developer:exception:log:info")
    public R<SysExceptionLogEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysLogService.getExceptionLogById(id));
    }
}

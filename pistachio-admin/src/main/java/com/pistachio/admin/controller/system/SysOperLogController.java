package com.pistachio.admin.controller.system;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.utils.R;
import com.pistachio.system.dto.req.OperLogListRequest;
import com.pistachio.system.entity.SysOperLogEntity;
import com.pistachio.system.service.ISysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @date: 2023/08/25 9:49
 * @author: Pengsy
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "操作日志模块", description = "系统操作日志")
@RestController
@RequestMapping("developer-oper-log")
public class SysOperLogController {

    @Autowired
    private ISysLogService iSysLogService;

    @Operation(summary = "操作日志 - 列表", description = "权限 [ developer:oper:log:list ]")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('developer:oper:log:list')")
    public R<Page<SysOperLogEntity>> list(OperLogListRequest request) {
        return R.success(iSysLogService.operPageLists(request));
    }

    @Operation(summary = "操作日志 - 详情", description = "权限 [ developer:oper:log:info ]")
    @Parameter(name = "id", description = "操作id", required = true)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('developer:oper:log:info')")
    public R<SysOperLogEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysLogService.getOperLogById(id));
    }
}

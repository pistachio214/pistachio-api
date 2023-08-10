package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.RoleListRequest;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @description: role controller
 * @date: 2023/08/09 16:40
 * @author: Pengsy
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    @GetMapping("/list")
    @SaCheckPermission("sys:role:list")
    public R<Page<SysRoleEntity>> list(RoleListRequest request) {
        return R.success(iSysRoleService.selectRolePage(request));
    }

}

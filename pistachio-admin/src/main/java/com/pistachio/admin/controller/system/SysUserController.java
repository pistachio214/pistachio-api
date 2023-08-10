package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.UserListRequest;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysRoleService;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @description: user controller
 * @date: 2023/08/09 16:02
 * @author: Pengsy
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @GetMapping("/list")
    @SaCheckPermission("sys:user:list")
    public R<Page<SysUserEntity>> list(UserListRequest request) {
        return R.success(iSysUserService.selectUserPage(request));
    }

    @OperLog(operModul = "管理员模块 - 管理员设置角色", operType = OperationLogConst.EDIT, operDesc = "管理员设置角色")
    @PostMapping("/role/{userId}")
    @SaCheckPermission("sys:user:role")
    public R<Object> rolePerm(@PathVariable("userId") Long userId, @RequestBody Long[] roleIds) {
        iSysRoleService.rolePerm(userId, roleIds);
        return R.success();
    }
}

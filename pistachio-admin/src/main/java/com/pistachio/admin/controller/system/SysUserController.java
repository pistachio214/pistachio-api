package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.framework.security.handle.SysLoginHandle;
import com.pistachio.system.dto.req.UserCreateRequest;
import com.pistachio.system.dto.req.UserListRequest;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 系统管理员模块
 *
 * @author Pengsy
 * @date: 2023/08/09 16:02
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private SysLoginHandle sysLoginHandle;

    /**
     * 管理员列表
     *
     * @param request 查询数据传输对象
     * @apiNote 权限 sys:user:list
     */
    @GetMapping("/list")
    @SaCheckPermission("sys:user:list")
    public R<Page<SysUserEntity>> list(UserListRequest request) {
        return R.success(iSysUserService.selectUserPage(request));
    }

    /**
     * 重置管理员密码
     *
     * @param userId 管理员id
     * @apiNote 权限 sys:user:repass
     */
    @OperLog(operModul = "管理员模块 - 重置管理员密码", operType = OperationLogConst.EDIT, operDesc = "重置管理员密码")
    @PostMapping("/repass")
    @SaCheckPermission("sys:user:repass")
    public R<String> repass(@RequestBody @NotNull(message = "管理员id不能为空") Long userId) {
        iSysUserService.restPassword(userId, sysLoginHandle.rsaEncryptByPublic(UserConstants.DEFAULT_PASSWORD));
        return R.success(UserConstants.DEFAULT_PASSWORD);
    }

    /**
     * 删除管理员
     *
     * @param id 管理员id
     * @apiNote 权限 sys:user:delete
     */
    @OperLog(operModul = "管理员模块 - 删除管理员", operType = OperationLogConst.DELETE, operDesc = "删除管理员")
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("sys:user:delete")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysUserService.deleteSysUser(id);
        return R.success();
    }

    /**
     * 新增管理员
     *
     * @param request 数据传输对象
     * @apiNote 权限 sys:user:save
     */
    @OperLog(operModul = "管理员模块 - 新增管理员", operType = OperationLogConst.SAVE, operDesc = "新增管理员")
    @PostMapping("/save")
    @SaCheckPermission("sys:user:save")
    public R<SysUserEntity> save(@Validated @RequestBody UserCreateRequest request) {
        String password = sysLoginHandle.rsaEncryptByPublic(UserConstants.DEFAULT_PASSWORD);
        return R.success(iSysUserService.save(request, password));
    }

    /**
     * 管理员设置角色
     *
     * @param userId  管理员id
     * @param roleIds 角色id列表
     * @apiNote 权限 sys:user:role
     */
    @OperLog(operModul = "管理员模块 - 管理员设置角色", operType = OperationLogConst.EDIT, operDesc = "管理员设置角色")
    @PostMapping("/role/{userId}")
    @SaCheckPermission("sys:user:role")
    public R<Object> rolePerm(@PathVariable("userId") Long userId, @RequestBody @NotNull(message = "角色id列表") Long[] roleIds) {
        iSysUserService.rolePerm(userId, roleIds);
        return R.success();
    }
}

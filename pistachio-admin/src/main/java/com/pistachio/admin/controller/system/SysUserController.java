package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.framework.security.handle.SysLoginHandle;
import com.pistachio.system.dto.req.UserChangeRoleRequest;
import com.pistachio.system.dto.req.UserCreateRequest;
import com.pistachio.system.dto.req.UserListRequest;
import com.pistachio.system.dto.req.UserRepassRequest;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@ApiSupport(author = "Pengsy")
@Tag(name = "管理员模块")
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private SysLoginHandle sysLoginHandle;

    @Operation(summary = "管理员 - 列表", description = "权限 [ sys:user:list ]")
    @GetMapping("/list")
    @SaCheckPermission("sys:user:list")
    public R<Page<SysUserEntity>> list(UserListRequest request) {
        return R.success(iSysUserService.selectUserPage(request));
    }

    @Operation(summary = "管理员 - 重置密码", description = "权限 [ sys:user:repass ]; 重置管理员密码")
    @OperLog(operModul = "管理员模块 - 重置管理员密码", operType = OperationLogConst.EDIT, operDesc = "重置管理员密码")
    @PostMapping("/repass")
    @SaCheckPermission("sys:user:repass")
    public R<String> repass(@RequestBody UserRepassRequest request) {
        String password = sysLoginHandle.rsaEncryptByPublic(UserConstants.DEFAULT_PASSWORD);
        iSysUserService.restPassword(request.getUserId(), password);
        return R.success(UserConstants.DEFAULT_PASSWORD);
    }

    @Operation(summary = "管理员 - 删除", description = "权限 [ sys:user:delete ]; 删除管理员")
    @Parameter(name = "id", description = "管理员id", required = true)
    @OperLog(operModul = "管理员模块 - 删除管理员", operType = OperationLogConst.DELETE, operDesc = "删除管理员")
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("sys:user:delete")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysUserService.deleteSysUser(id);
        return R.success();
    }

    @Operation(summary = "管理员 - 新增", description = "权限 [ sys:user:save ]; 新增管理员")
    @OperLog(operModul = "管理员模块 - 新增管理员", operType = OperationLogConst.SAVE, operDesc = "新增管理员")
    @PostMapping("/save")
    @SaCheckPermission("sys:user:save")
    public R<SysUserEntity> save(@Validated @RequestBody UserCreateRequest request) {
        String password = sysLoginHandle.rsaEncryptByPublic(UserConstants.DEFAULT_PASSWORD);
        return R.success(iSysUserService.save(request, password));
    }

    @Operation(summary = "管理员 - 设置角色", description = "权限 [ sys:user:role ]; 管理员设置角色")
    @Parameter(name = "userId", description = "管理员id", required = true)
    @OperLog(operModul = "管理员模块 - 管理员设置角色", operType = OperationLogConst.EDIT, operDesc = "管理员设置角色")
    @PostMapping("/role/{userId}")
    @SaCheckPermission("sys:user:role")
    public R<Object> rolePerm(@PathVariable("userId") Long userId, @RequestBody UserChangeRoleRequest request) {
        iSysUserService.rolePerm(userId, request.getRoleIds());
        return R.success();
    }

    @Operation(summary = "展示管理员详情数据", description = "点击管理员个人信息的时候，弹窗展示管理员详情")
    @GetMapping("/info")
    public R<SysUserEntity> findCurrentUser() {
        SysUserEntity sysUser = (SysUserEntity) StpUtil.getSession().get("user");
        sysUser.setPassword(null);

        return R.success(sysUser);
    }
}

package com.pistachio.admin.controller.system;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.constant.UserConstants;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.framework.dto.LoginUserDto;
import com.pistachio.system.dto.req.*;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "管理员 - 列表", description = "权限 [ sys:user:list ]")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public R<Page<SysUserEntity>> list(UserListRequest request) {
        return R.success(iSysUserService.selectUserPage(request));
    }

    @Operation(summary = "管理员 - 重置密码", description = "权限 [ sys:user:repass ]; 重置管理员密码")
    @OperLog(operModul = "管理员模块 - 重置管理员密码", operType = OperationLogConst.EDIT, operDesc = "重置管理员密码")
    @PostMapping("/repass")
    @PreAuthorize("hasAnyAuthority('sys:user:repass')")
    public R<String> repass(@RequestBody UserRepassRequest request) {
        iSysUserService.restPassword(request.getUserId(), UserConstants.DEFAULT_PASSWORD);
        return R.success(UserConstants.DEFAULT_PASSWORD);
    }

    @Operation(summary = "管理员 - 删除", description = "权限 [ sys:user:delete ]; 删除管理员")
    @Parameter(name = "id", description = "管理员id", required = true)
    @OperLog(operModul = "管理员模块 - 删除管理员", operType = OperationLogConst.DELETE, operDesc = "删除管理员")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('sys:user:delete')")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysUserService.deleteSysUser(id);
        return R.success();
    }

    @Operation(summary = "管理员 - 新增", description = "权限 [ sys:user:save ]; 新增管理员")
    @OperLog(operModul = "管理员模块 - 新增管理员", operType = OperationLogConst.SAVE, operDesc = "新增管理员")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('sys:user:save')")
    public R<SysUserEntity> save(@Validated @RequestBody UserCreateRequest request) {
        return R.success(iSysUserService.save(request, UserConstants.DEFAULT_PASSWORD));
    }

    @Operation(summary = "管理员 - 设置角色", description = "权限 [ sys:user:role ]; 管理员设置角色")
    @Parameter(name = "userId", description = "管理员id", required = true)
    @OperLog(operModul = "管理员模块 - 管理员设置角色", operType = OperationLogConst.EDIT, operDesc = "管理员设置角色")
    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAnyAuthority('sys:user:role')")
    public R<Object> rolePerm(@PathVariable("userId") Long userId, @RequestBody UserChangeRoleRequest request) {
        iSysUserService.rolePerm(userId, request.getRoleIds());
        return R.success();
    }

    @Operation(summary = "展示管理员详情数据", description = "权限 [ sys:user:modal:info ]; 点击管理员个人信息的时候，弹窗展示管理员详情")
    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('sys:user:modal:info')")
    public R<SysUserEntity> findCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDto loginUser = (LoginUserDto) authentication.getPrincipal();

        SysUserEntity sysUser = loginUser.getUser();
        SysUserEntity result = iSysUserService.findById(sysUser.getId());
        result.setPassword(null);

        return R.success(result);
    }

    @Operation(summary = "编辑展示管理员详情数据", description = "权限 [ sys:user:modal:edit ]; 点击管理员个人信息的时候，编辑管理员详情")
    @PostMapping("/info/save")
    @PreAuthorize("hasAnyAuthority('sys:user:modal:edit')")
    public R<SysUserEntity> editUser(@RequestBody UserEditRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDto loginUser = (LoginUserDto) authentication.getPrincipal();

        SysUserEntity sysUser = loginUser.getUser();
        request.setId(sysUser.getId());

        return R.success(iSysUserService.editSysUser(request));
    }

    @Operation(summary = "编辑展示管理员详情数据", description = "权限 [ sys:user:modal:change:password ];点击管理员个人信息的时候，修改管理员密码")
    @PostMapping("/change/password")
    @PreAuthorize("hasAnyAuthority('sys:user:modal:change:password')")
    public R<SysUserEntity> changePassword(@RequestBody UserChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDto loginUser = (LoginUserDto) authentication.getPrincipal();

        iSysUserService.changePassword(loginUser.getUser(), request);
        return R.success();
    }

    @Operation(summary = "管理员 - 停用管理员", description = "权限 [ sys:user:disable ]; 停用管理员")
    @Parameter(name = "id", description = "管理员id", required = true)
    @OperLog(operModul = "管理员模块 - 停用管理员", operType = OperationLogConst.EDIT, operDesc = "停用管理员")
    @GetMapping("/disable/{id}")
    public R<Object> disableSysUser(@PathVariable("id") Long id) {
//        sysLoginHandle.disableSysUser(id);

        return R.success();
    }

    @Operation(summary = "管理员 - 启用管理员", description = "权限 [ sys:user:enable ]; 启用管理员")
    @Parameter(name = "id", description = "管理员id", required = true)
    @OperLog(operModul = "管理员模块 - 启用管理员", operType = OperationLogConst.EDIT, operDesc = "启用管理员")
    @GetMapping("/enable/{id}")
    public R<Object> enableSysUser(@PathVariable("id") Long id) {
//        sysLoginHandle.enableSysUser(id);

        return R.success();
    }

}

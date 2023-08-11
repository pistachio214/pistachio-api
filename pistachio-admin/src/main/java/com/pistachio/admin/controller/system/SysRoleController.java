package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.RoleCreateRequest;
import com.pistachio.system.dto.req.RoleListRequest;
import com.pistachio.system.dto.req.RoleUpdateRequest;
import com.pistachio.system.dto.vo.SysRoleAndMenuIdsVo;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 角色模块
 *
 * @description: role controller
 * @date: 2023/08/09 16:40
 * @author: Pengsy
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 角色列表
     *
     * @param request list
     * @apiNote 权限 sys:role:list
     */
    @GetMapping("/list")
    @SaCheckPermission("sys:role:list")
    public R<Page<SysRoleEntity>> list(RoleListRequest request) {
        return R.success(iSysRoleService.selectRolePage(request));
    }

    /**
     * 角色详情
     *
     * @param id 角色id
     * @apiNote 权限 sys:role:list
     */
    @GetMapping("/info/{id}")
    @SaCheckPermission("sys:role:list")
    public R<SysRoleAndMenuIdsVo> info(@PathVariable("id") Long id) {
        return R.success(iSysRoleService.detail(id));
    }

    /**
     * 角色设置菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id列表
     * @apiNote 权限 sys:role:perm
     */
    @OperLog(operModul = "角色模块 - 角色设置菜单", operType = OperationLogConst.EDIT, operDesc = "角色设置菜单")
    @PostMapping("/perm/{roleId}")
    @SaCheckPermission("sys:role:perm")
    public R<Object> perm(@PathVariable("roleId") Long roleId, @RequestBody @NotNull(message = "菜单id不能为空") Long[] menuIds) {
        iSysRoleService.permRoleMenu(roleId, menuIds);

        return R.success();
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @apiNote 权限 sys:role:delete
     */
    @OperLog(operModul = "角色模块 - 删除角色", operType = OperationLogConst.DELETE, operDesc = "删除角色")
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("sys:role:delete")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysRoleService.delete(id);
        return R.success();
    }

    /**
     * 新增角色
     *
     * @param request 角色新增数据传输对象
     * @apiNote 权限 sys:role:save
     */
    @OperLog(operModul = "角色模块 - 新增角色", operType = OperationLogConst.SAVE, operDesc = "新增角色")
    @PostMapping("/save")
    @SaCheckPermission("sys:role:save")
    public R<SysRoleEntity> save(@Validated @RequestBody RoleCreateRequest request) {
        return R.success(iSysRoleService.create(request));
    }

    /**
     * 更新角色
     *
     * @param request 角色更新数据传输对象
     * @apiNote 权限 sys:role:update
     */
    @OperLog(operModul = "角色模块 - 更新角色", operType = OperationLogConst.EDIT, operDesc = "更新角色")
    @PutMapping("/update")
    @SaCheckPermission("sys:role:update")
    public R<SysRoleEntity> update(@Validated @RequestBody RoleUpdateRequest request) {
        return R.success(iSysRoleService.update(request));
    }
}

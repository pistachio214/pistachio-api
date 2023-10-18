package com.pistachio.admin.controller.system;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.req.RoleCreateRequest;
import com.pistachio.system.dto.req.RoleListRequest;
import com.pistachio.system.dto.req.RolePermRequest;
import com.pistachio.system.dto.req.RoleUpdateRequest;
import com.pistachio.system.dto.vo.SysRoleAndMenuIdsVo;
import com.pistachio.system.entity.SysRoleEntity;
import com.pistachio.system.service.ISysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 系统角色模块
 *
 * @description: role controller
 * @date: 2023/08/09 16:40
 * @author: Pengsy
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "系统角色模块")
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    @Operation(summary = "角色 - 列表", description = "权限 [ sys:role:list ]; 按条件搜索角色列表")
    @GetMapping("/list")
    public R<Page<SysRoleEntity>> list(RoleListRequest request) {
        return R.success(iSysRoleService.selectRolePage(request));
    }

    @Operation(summary = "角色 - 详情", description = "权限 [ sys:role:list ]; 按角色id搜索角色详情")
    @Parameter(name = "id", description = "角色id", required = true)
    @GetMapping("/info/{id}")
    public R<SysRoleAndMenuIdsVo> info(@PathVariable("id") Long id) {
        return R.success(iSysRoleService.detail(id));
    }

    @Operation(summary = "角色 - 设置菜单", description = "权限 [ sys:role:perm ]; 按角色id为其设置菜单")
    @Parameter(name = "roleId", description = "角色id", required = true)
    @OperLog(operModul = "角色模块 - 角色设置菜单", operType = OperationLogConst.EDIT, operDesc = "角色设置菜单")
    @PostMapping("/perm/{roleId}")
    public R<Object> perm(@PathVariable("roleId") Long roleId, @RequestBody RolePermRequest request) {
        iSysRoleService.permRoleMenu(roleId, request.getMenuIds());

        return R.success();
    }

    @Operation(summary = "角色 - 删除", description = "权限 [ sys:role:delete ]; 按角色id删除角色")
    @Parameter(name = "id", description = "角色id", required = true)
    @OperLog(operModul = "角色模块 - 删除角色", operType = OperationLogConst.DELETE, operDesc = "删除角色")
    @DeleteMapping("/delete/{id}")
    public R<Object> delete(@PathVariable("id") Long id) {
        iSysRoleService.delete(id);
        return R.success();
    }

    @Operation(summary = "角色 - 新增", description = "权限 [ sys:role:save ]; 新增角色")
    @OperLog(operModul = "角色模块 - 新增角色", operType = OperationLogConst.SAVE, operDesc = "新增角色")
    @PostMapping("/save")
    public R<SysRoleEntity> save(@Validated @RequestBody RoleCreateRequest request) {
        return R.success(iSysRoleService.create(request));
    }

    @Operation(summary = "角色 - 更新", description = "权限 [ sys:role:update ]; 更新角色")
    @OperLog(operModul = "角色模块 - 更新角色", operType = OperationLogConst.EDIT, operDesc = "更新角色")
    @PutMapping("/update")
    public R<SysRoleEntity> update(@Validated @RequestBody RoleUpdateRequest request) {
        return R.success(iSysRoleService.update(request));
    }
}

package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.SysMenuDto;
import com.pistachio.system.dto.req.MenuCreateRequest;
import com.pistachio.system.dto.req.MenuUpdateRequest;
import com.pistachio.system.dto.vo.NavMenuVo;
import com.pistachio.system.dto.vo.NavUserVo;
import com.pistachio.system.dto.vo.SysMenuListTreeVo;
import com.pistachio.system.entity.SysMenuEntity;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysMenuService;
import com.pistachio.system.service.ISysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 菜单模块
 *
 * @author Pengsy
 * @date: 2023/08/04 15:11
 */
@ApiSupport(author = "Pengsy")
@Tag(name = "菜单模块", description = "系统菜单操作")
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Operation(summary = "登录成功后执行", description = "在登录操作之后成功后，立马执行该操作得到菜单信息和管理员个人信息")
    @GetMapping("/nav")
    public R<NavMenuVo> nav() {
        SysUserEntity sysUser = (SysUserEntity) StpUtil.getSession().get("user");
        // 获取权限信息
        String authorityInfo = iSysUserService.getUserAuthorityInfo(sysUser.getId());
        // ROLE_admin,ROLE_normal,sys:user:list,....
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        // 获取导航栏信息
        List<SysMenuDto> navs = iSysMenuService.getCurrentUserNav(sysUser.getId());

        return R.success(new NavMenuVo(authorityInfoArray, navs, new NavUserVo(sysUser.getNickname(), sysUser.getAvatar())));
    }

    @Operation(summary = "菜单 - 列表", description = "权限 [ sys:menu:list ]")
    @GetMapping("/list")
    @SaCheckPermission("sys:menu:list")
    public R<SysMenuListTreeVo> list() {
        return R.success(iSysMenuService.tree());
    }

    @Operation(summary = "菜单 - 详情", description = "权限 [ sys:menu:list ]; 根据菜单的id，获取菜单的详情")
    @Parameter(name = "id", description = "菜单id", required = true)
    @GetMapping("/info/{id}")
    @SaCheckPermission("sys:menu:list")
    public R<SysMenuEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysMenuService.findById(id));
    }

    @Operation(summary = "菜单 - 新增", description = "权限 [ sys:menu:save ]; 创建新菜单")
    @OperLog(operModul = "菜单模块 - 新增菜单", operType = OperationLogConst.SAVE, operDesc = "新增菜单")
    @SaCheckPermission("sys:menu:save")
    @PostMapping(value = "/save")
    public R<SysMenuEntity> add(@Validated @RequestBody MenuCreateRequest request) {
        return R.success(iSysMenuService.create(request));
    }

    @Operation(summary = "菜单 - 删除", description = "权限 [ sys:menu:delete ]; 删除菜单")
    @Parameter(name = "id", description = "菜单id", required = true)
    @OperLog(operModul = "菜单模块 - 删除菜单", operType = OperationLogConst.DELETE, operDesc = "删除菜单")
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("sys:menu:delete")
    public R<Objects> delete(@PathVariable("id") Long id) {
        iSysMenuService.delete(id);
        return R.success();
    }

    @Operation(summary = "菜单 - 更新", description = "权限 [ sys:menu:update ]; 更新菜单")
    @OperLog(operModul = "菜单模块 - 更新菜单", operType = OperationLogConst.EDIT, operDesc = "更新菜单")
    @PutMapping("/update")
    @SaCheckPermission("sys:menu:update")
    public R<SysMenuEntity> update(@Validated @RequestBody MenuUpdateRequest request) {
        return R.success(iSysMenuService.edit(request));
    }
}

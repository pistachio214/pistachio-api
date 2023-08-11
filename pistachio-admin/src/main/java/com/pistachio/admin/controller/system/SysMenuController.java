package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.pistachio.common.constant.OperationLogConst;
import com.pistachio.common.utils.R;
import com.pistachio.framework.annotation.OperLog;
import com.pistachio.system.dto.SysMenuDto;
import com.pistachio.system.dto.req.MenuCreateRequest;
import com.pistachio.system.dto.req.MenuUpdateRequest;
import com.pistachio.system.dto.vo.NavMenuVo;
import com.pistachio.system.dto.vo.NavUserVo;
import com.pistachio.system.entity.SysMenuEntity;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysMenuService;
import com.pistachio.system.service.ISysUserService;
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
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     * 获取侧边栏菜单
     *
     * @apiNote 需要登录权限
     */
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

    /**
     * 菜单列表
     *
     * @apiNote 权限 sys:menu:list
     */
    @GetMapping("/list")
    @SaCheckPermission("sys:menu:list")
    public R<List<SysMenuEntity>> list() {
        return R.success(iSysMenuService.tree());
    }

    /**
     * 菜单详情
     *
     * @param id 菜单id
     * @apiNote 权限 sys:menu:list; 根据菜单的id，获取菜单的详情
     */
    @GetMapping("/info/{id}")
    @SaCheckPermission("sys:menu:list")
    public R<SysMenuEntity> info(@PathVariable("id") Long id) {
        return R.success(iSysMenuService.findById(id));
    }

    /**
     * 新增菜单
     *
     * @param request 创建菜单数据传输对象
     * @apiNote 权限 sys:menu:save
     */
    @OperLog(operModul = "菜单模块 - 新增菜单", operType = OperationLogConst.SAVE, operDesc = "新增菜单")
    @SaCheckPermission("sys:menu:save")
    @PostMapping(value = "/save")
    public R<SysMenuEntity> add(@Validated @RequestBody MenuCreateRequest request) {
        return R.success(iSysMenuService.create(request));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @apiNote 权限 sys:menu:delete
     */
    @OperLog(operModul = "菜单模块 - 删除菜单", operType = OperationLogConst.DELETE, operDesc = "删除菜单")
    @DeleteMapping("/delete/{id}")
    @SaCheckPermission("sys:menu:delete")
    public R<Objects> delete(@PathVariable("id") Long id) {
        iSysMenuService.delete(id);
        return R.success();
    }

    /**
     * 更新菜单
     *
     * @param request 更新数据传输对象
     * @apiNote 权限 sys:menu:update
     */
    @OperLog(operModul = "菜单模块 - 更新菜单", operType = OperationLogConst.EDIT, operDesc = "更新菜单")
    @PutMapping("/update")
    @SaCheckPermission("sys:menu:update")
    public R<SysMenuEntity> update(@Validated @RequestBody MenuUpdateRequest request) {
        return R.success(iSysMenuService.edit(request));
    }
}

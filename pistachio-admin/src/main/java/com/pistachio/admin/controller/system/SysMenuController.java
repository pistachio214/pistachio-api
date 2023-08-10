package com.pistachio.admin.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.pistachio.common.utils.R;
import com.pistachio.system.dto.SysMenuDto;
import com.pistachio.system.dto.vo.NavMenuVo;
import com.pistachio.system.entity.SysMenuEntity;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysMenuService;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Pengsy
 * @date: 2023/08/04 15:11
 * @description: 菜单模块
 */
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @GetMapping("/nav")
    public R<Object> nav() {
        SysUserEntity sysUser = (SysUserEntity) StpUtil.getSession().get("user");
        // 获取权限信息
        String authorityInfo = iSysUserService.getUserAuthorityInfo(sysUser.getId());
        // ROLE_admin,ROLE_normal,sys:user:list,....
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        // 获取导航栏信息
        List<SysMenuDto> navs = iSysMenuService.getCurrentUserNav(sysUser.getId());

        //获取用户的昵称和头像
        Map<String, String> user = new HashMap<>();
        user.put("nickname", sysUser.getNickname());
        user.put("avatar", sysUser.getAvatar());

        return R.success(new NavMenuVo(authorityInfoArray, navs, user));
    }

    @GetMapping("/list")
    @SaCheckPermission("sys:menu:list")
    public R<List<SysMenuEntity>> list() {
        return R.success(iSysMenuService.tree());
    }
}

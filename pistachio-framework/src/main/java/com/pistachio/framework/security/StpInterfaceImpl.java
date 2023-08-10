package com.pistachio.framework.security;

import cn.dev33.satoken.stp.StpInterface;
import com.pistachio.framework.security.handle.SysUserAuthorityHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Pengsy
 * @date: 2023/08/02 15:08
 * @description: 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private SysUserAuthorityHandle sysUserAuthorityHandle;

    /**
     * 获取一个账号所拥有的权限码集合
     *
     * @param loginId   用户id
     * @param loginType 登录类型
     * @return 权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Long id = Long.valueOf(loginId.toString());
        String authority = sysUserAuthorityHandle.getUserAuthorityInfo(id);

        return Arrays.asList(authority.split(","));
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long id = Long.valueOf(loginId.toString());

        return sysUserAuthorityHandle.getUserRoleList(id);
    }

}
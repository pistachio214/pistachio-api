package com.pistachio.framework.security.service;

import com.pistachio.common.exception.ServiceException;
import com.pistachio.common.utils.StringUtil;
import com.pistachio.framework.dto.LoginUserDto;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 重写后台管理登录实现类
 * @date: 2023/10/16 14:26
 * @author: Pengsy
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new ServiceException("用户不能为空");
        }

        SysUserEntity sysUser = iSysUserService.findByAccount(username);
        List<String> authorityList = new ArrayList<>();
        //获取该用户所拥有的权限
        String authority = iSysUserService.getUserAuthorityInfo(sysUser.getId());

        if (StringUtil.isNotEmpty(authority)) {
            authorityList = Arrays.asList(authority.split(","));
        }

        return new LoginUserDto(sysUser, authorityList);
    }

}

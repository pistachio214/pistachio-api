package com.pistachio.framework.security.handle;

import com.pistachio.framework.dto.LoginUserDto;
import com.pistachio.system.entity.SysUserEntity;
import com.pistachio.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @description: 登录成功处理逻辑
 * @date: 2023/10/16 12:04
 * @author: Pengsy
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("能进这里吗?");

        //更新用户表上次登录时间、更新人、更新时间等字段
        LoginUserDto userDetails = (LoginUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUserEntity sysUser = iSysUserService.findByAccount(userDetails.getUsername());

        sysUser.setLastLoginTime(LocalDateTime.now());

        iSysUserService.saveEntity(sysUser);
    }
}

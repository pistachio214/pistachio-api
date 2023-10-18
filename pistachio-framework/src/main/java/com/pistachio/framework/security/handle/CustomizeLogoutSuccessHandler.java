package com.pistachio.framework.security.handle;

import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 退出登录执行操作
 * @date: 2023/10/16 11:59
 * @author: Pengsy
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("logout 处理");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(GsonUtil.toJSONString(R.success()));
    }
}

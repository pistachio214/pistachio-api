package com.pistachio.framework.security.handle;

import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 匿名用户访问无权限资源时的异常
 * @date: 2023/10/16 14:15
 * @author: Pengsy
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = "匿名用户无权限访问该资源 [ " + request.getServletPath() + " ]";
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(GsonUtil.toJSONString(R.error(message)));
    }
}

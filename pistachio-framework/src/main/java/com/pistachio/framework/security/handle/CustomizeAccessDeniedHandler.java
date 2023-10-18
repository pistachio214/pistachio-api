package com.pistachio.framework.security.handle;

import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 权限拒绝处理逻辑
 * @date: 2023/10/16 14:11
 * @author: Pengsy
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String message = "您没有权限访问该资源[ " + request.getServletPath() + " ]";

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(GsonUtil.toJSONString(R.error(message)));
    }
}

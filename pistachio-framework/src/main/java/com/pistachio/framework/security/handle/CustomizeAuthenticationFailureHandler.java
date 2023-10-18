package com.pistachio.framework.security.handle;

import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.R;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 登录失败处理逻辑
 * @date: 2023/10/16 14:01
 * @author: Pengsy
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String message;
        if (exception instanceof AccountExpiredException) {
            // 账号过期
            message = "账号过期";
        } else if (exception instanceof BadCredentialsException) {
            // 密码错误
            message = "密码错误";
        } else if (exception instanceof CredentialsExpiredException) {
            // 密码过期
            message = "密码过期";
        } else if (exception instanceof DisabledException) {
            // 账号不可用
            message = "账号不可用";
        } else if (exception instanceof LockedException) {
            // 账号锁定
            message = "账号锁定";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            // 用户不存在
            message = "用户信息不存在";
        } else {
            // 其他错误
            message = "其他错误";
        }

        System.out.println("是不是没到这里哟？ message = " + message);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(GsonUtil.toJSONString(R.error(message)));
    }
}

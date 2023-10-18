package com.pistachio.framework.service;

import com.pistachio.system.dto.req.AdminLoginRequest;
import com.pistachio.system.dto.vo.LoginSuccessVo;

public interface LoginService {

    /**
     * 后台管理员登录服务
     *
     * @param loginRequest request
     * @return LoginSuccess
     */
    LoginSuccessVo adminLogin(AdminLoginRequest loginRequest);

    /**
     * 后台管理员退出系统服务
     */
    void adminLogout();

}

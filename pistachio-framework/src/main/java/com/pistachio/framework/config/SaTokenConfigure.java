package com.pistachio.framework.config;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Pengsy
 * @date: 2023/08/02 15:19
 * @description: SaToken配置
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注解sa-token拦截器，打开注解式鉴权功能
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            SaRouter.match("/**")
                    // 设置排除验证的路由
                    .notMatch("/acc/admin/doLogin", "/acc/getCaptcha", "/test")
                    // 设置验证器
                    .check(r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
    }
}

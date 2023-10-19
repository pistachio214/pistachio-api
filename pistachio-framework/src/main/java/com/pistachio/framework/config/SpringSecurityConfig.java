package com.pistachio.framework.config;

import com.pistachio.framework.filter.JwtAuthenticationTokenFilter;
import com.pistachio.framework.security.handle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: spring security 配置类
 * @date: 2023/10/16 10:15
 * @author: Pengsy
 */
@Configuration
@EnableWebSecurity
// prePostEnable属性决定Spring Security在接口前注解是否可用 @PreAuthorize,@PostAuthorize等注解,设置true,会拦截加了这些注解的接口
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.matchers}")
    private String antMatchers;

//    /**
//     * 权限拒绝处理逻辑
//     */
//    @Autowired
//    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;
//
//    /**
//     * 匿名用户访问无权限资源时的异常
//     */
//    @Autowired
//    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    /**
     * 会话信息过期策略
     */
    @Autowired
    private CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;

    /**
     * 登出成功处理逻辑
     */
    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

    /**
     * 校验jwt过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 设置默认的加密方式（强hash方式加密）
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨站请求伪造保护,如果使用POST请求进行登录的话,需要关闭
        http.cors().and().csrf().disable();

        //登出管理
        http.logout()
                // 登出成功处理逻辑
                .logoutSuccessHandler(customizeLogoutSuccessHandler)
                //登出之后删除cookie
                .deleteCookies("JSESSIONID");

//        //配置异常处理器 (权限拒绝、登录失效等)  两种异常均已被全局异常处理器处理，则不需要再次处理
//        http.exceptionHandling()
//                //匿名用户访问无权限资源时的异常处理
//                .authenticationEntryPoint(customizeAuthenticationEntryPoint)
//                //权限拒绝处理逻辑( org.springframework.security.access.AccessDeniedException 异常被全局异常处理器处理,则不需要再次处理 )
//                .accessDeniedHandler(customizeAccessDeniedHandler);

        //会话管理
        http.sessionManagement()
                //同一账号同时登录最大用户数
                .maximumSessions(1)
                //会话失效(账号被挤下线)处理逻辑
                .expiredSessionStrategy(customizeSessionInformationExpiredStrategy);

        http
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers(buildAnyMatchers()).anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //把token校验过滤器添加到过滤器链中,token过滤器在 UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 自动构建 允许匿名访问的资源路径
     *
     * @return String[]
     */
    private String[] buildAnyMatchers() {
        return antMatchers.split(",");
    }


}

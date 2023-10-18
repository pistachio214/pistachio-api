package com.pistachio.framework.filter;

import com.pistachio.common.core.redis.RedisCache;
import com.pistachio.common.utils.ConvertUtil;
import com.pistachio.common.utils.GsonUtil;
import com.pistachio.common.utils.JwtUtil;
import com.pistachio.framework.dto.LoginUserDto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @description: Jwt 认证过滤器
 * @date: 2023/10/17 16:40
 * @author: Pengsy
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${spring.security.token-name}")
    private String tokenName;

    @Value("${spring.security.token-prefix}")
    private String tokenPrefix;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = request.getHeader(tokenName);
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        String prefix = token.substring(0, tokenPrefix.length());
        if (!prefix.equals(tokenPrefix)) {
            throw new RuntimeException("token携带非法字符");
        }

        // 由于前端传过来的数据格式类似这样  tokenName: tokenPrefix tokenValue,所以真实token值需要截取
        token = token.substring(tokenPrefix.length() + 1);

        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userid;
        LoginUserDto loginUser = ConvertUtil.map(redisCache.getCacheObject(redisKey), LoginUserDto.class);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}

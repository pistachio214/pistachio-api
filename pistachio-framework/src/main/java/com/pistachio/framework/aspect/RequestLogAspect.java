package com.pistachio.framework.aspect;

import cn.hutool.core.date.StopWatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @date: 2023/10/31 15:15
 * @author: Pengsy
 */
@Aspect
@Component
public class RequestLogAspect {

    private final static Logger log = LoggerFactory.getLogger(RequestLogAspect.class);

    private final static String line = System.lineSeparator();

    @Autowired
    private ObjectMapper mapper;

    /**
     * 打印`controller`包下的api接口,不包括子包
     */
    @Pointcut("execution(* com.pistachio.*.controller.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> header = new HashMap<>(10);
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            header.put(headerName, request.getHeader(headerName));
        }
        List<Object> paramList = Stream.of(point.getArgs()).filter(args -> !(args instanceof ServletRequest)).filter(args -> !(args instanceof ServletResponse)).collect(Collectors.toList());
        System.out.println("----------------------------------------- Start -----------------------------------------------------");
        log.info("request,method:{}" + line + "path:{}" + line + "headers:{}" + line + "args:{}", request.getMethod(), request.getRequestURI(), mapper.writeValueAsString(header), mapper.writeValueAsString(paramList));
        StopWatch watch = new StopWatch();
        watch.start();
        Object response = point.proceed();
        watch.stop();
        log.info("response,method:{}" + line + "path:{}" + line + "response:{}" + line + "timeCost:{}", request.getMethod(), request.getRequestURI(), mapper.writeValueAsString(response), watch.getTotalTimeSeconds());
        System.out.println("----------------------------------------- End -----------------------------------------------------");
        return response;
    }
}

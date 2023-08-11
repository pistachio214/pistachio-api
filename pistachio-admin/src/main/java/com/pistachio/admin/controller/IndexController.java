package com.pistachio.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试模块
 * @author: Pengsy
 * @date: 2023/08/01 18:06
 * @description: test controller
 */
@RestController
public class IndexController {

    /**
     * 测试使用，无任何实际意义
     */
    @GetMapping("/test")
    public void test() {
        System.out.println("hello,world!");
    }

}

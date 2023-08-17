package com.pistachio.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试模块
 *
 * @author: Pengsy
 * @date: 2023/08/01 18:06
 * @description: test controller
 * @ignore
 */
@Tag(name = "测试模块")
@RestController
public class IndexController {

    /**
     * 测试使用，无任何实际意义
     */
    @Operation(summary = "测试用例", tags = "测试1", description = "拿来随便搞,就是用来写各种案例的")
    @GetMapping("/test")
    public void test() {
        System.out.println("hello,world!");
    }

}

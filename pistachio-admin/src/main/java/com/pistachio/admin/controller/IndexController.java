package com.pistachio.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Pengsy
 * @date: 2023/08/01 18:06
 * @description: test controller
 */
@RestController
public class IndexController {

    @GetMapping("/test")
    public void test() {
        System.out.println("hello,world!");
    }

}

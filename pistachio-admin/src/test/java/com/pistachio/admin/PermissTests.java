package com.pistachio.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 关于权限部分的测试案例
 * @date: 2023/08/25 14:11
 * @author: Pengsy
 */
@SpringBootTest
public class PermissTests {



    @Test
    void restRequestPath() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode("123456");
        System.out.println(pass);
    }

    @Test
    void restAdminPermiss() {
        String tokenPrefix = "Authorization";
        String tokenValue = "Authorization fjdsaklfjioewjqiofgjioqhgioqjiofjioweqjfiowjeqifjwoqj";

        String s = tokenValue.substring(0, tokenPrefix.length());
        System.out.println(s);


        String s1 = tokenValue.substring(tokenPrefix.length() + 1);
        System.out.println(s1);
    }

}

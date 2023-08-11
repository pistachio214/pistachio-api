package com.pistachio.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: Pengsy
 * @date: 2023/08/01 17:34
 * @description: 启动程序
 */
@SpringBootApplication(scanBasePackages = {
        "com.pistachio.admin.*",
        "com.pistachio.common.*",
        "com.pistachio.framework.*",
        "com.pistachio.system.*"
})
@EntityScan(basePackages = "com.pistachio.system.entity")
@EnableJpaRepositories(basePackages = "com.pistachio.system.repository")
@EnableJpaAuditing // 开启JPA审计
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  开心果启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}

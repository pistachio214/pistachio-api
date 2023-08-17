package com.pistachio.framework.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


/**
 * @description:
 * @date: 2023/08/17 10:21
 * @author: Pengsy
 */

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Pengsy");
        contact.setUrl("https://github.com/RogerPeng123");
        contact.setEmail("songyang410@outlook.com");

        return new OpenAPI()
                .info(new Info()
                        .title("Pistachio Management APIs Docs")
                        .version("1.0")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .termsOfService("https://github.com/RogerPeng123")
                        .contact(contact)
                        .license(new License().name("Apache 2.0")
                                .url("https://github.com/RogerPeng123"))
                ).addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                .components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
                        .name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }

}

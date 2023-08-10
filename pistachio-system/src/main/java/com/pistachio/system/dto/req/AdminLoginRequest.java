package com.pistachio.system.dto.req;

import lombok.Data;

/**
 * @author: Pengsy
 * @date: 2023/08/02 14:58
 * @description: 登录对象
 */
@Data
public class AdminLoginRequest {
    private String username;

    private String password;

    private String code;

    private String uuid;

    private boolean isRemember;
}

package com.pistachio.system.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Pengsy
 * @date: 2023/08/03 10:59
 * @description: 登录成功传输对象
 */
@Data
@AllArgsConstructor
public class LoginSuccessVo {

    private String tokenName;

    private String tokenValue;

    private String tokenPrefix;
}

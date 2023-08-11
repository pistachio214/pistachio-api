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

    /**
     * token 名称
     * @mock satoken
     */
    private String tokenName;

    /**
     * token 值
     * @mock bebf4a2a-8db5-456f-a8a0-496e13209c3f
     */
    private String tokenValue;

    /**
     * token 传入携带标记
     * @mock Authorization
     * @since Authorization
     */
    private String tokenPrefix;
}

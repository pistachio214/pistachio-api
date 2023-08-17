package com.pistachio.system.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Pengsy
 * @date: 2023/08/03 10:59
 * @description: 登录成功传输对象
 */
@Schema(name = "登录成功传输对象")
@Data
@AllArgsConstructor
public class LoginSuccessVo {

    @Schema(description = "token 名称", defaultValue = "satoken")
    private String tokenName;

    @Schema(description = "token 值", defaultValue = "bebf4a2a-8db5-456f-a8a0-496e13209c3f")
    private String tokenValue;

    @Schema(description = "token 传入携带标记", defaultValue = "Authorization")
    private String tokenPrefix;
}

package com.pistachio.system.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @date: 2023/08/10 16:49
 * @author: Pengsy
 */
@Data
public class RoleCreateRequest {
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @NotBlank(message = "角色编码不能为空")
    private String code;

    private String remark;

    private Integer status;
}

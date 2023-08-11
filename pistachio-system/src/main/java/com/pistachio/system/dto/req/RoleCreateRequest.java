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

    /**
     * 角色名称
     *
     * @mock 商品管理员
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;

    /**
     * 角色编码
     *
     * @mock goods_manager
     * @since 不可重复
     */
    @NotBlank(message = "角色编码不能为空")
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     *
     * @mock 1
     */
    private Integer status;
}

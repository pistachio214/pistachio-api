package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @date: 2023/08/11 10:32
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuUpdateRequest extends MenuCreateRequest {

    @Schema(description = "菜单id", defaultValue = "11", required = true)
    @NotNull(message = "菜单id不能为空")
    private Long id;
}

package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description: dict item edit dto
 * @date: 2023/08/09 14:03
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class DictItemEditRequest extends DictItemCreateRequest {

    @Schema(description = "字典项ID", defaultValue = "100", required = true)
    @NotNull(message = "字典项ID不能为空")
    private Long id;
}

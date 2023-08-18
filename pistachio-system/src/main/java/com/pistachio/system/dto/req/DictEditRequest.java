package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @description: edit dict dto
 * @date: 2023/08/09 11:45
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class DictEditRequest extends DictCreateRequest {

    @Schema(description = "字典ID", defaultValue = "19", required = true)
    @NotNull(message = "字典ID不能为空")
    private Long id;
}

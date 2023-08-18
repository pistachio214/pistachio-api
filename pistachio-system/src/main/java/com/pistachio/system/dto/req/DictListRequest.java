package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Pengsy
 * @date: 2023/08/04 17:02
 * @description:
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class DictListRequest extends ReqPageDto {

    @Schema(description = "字典类型")
    private Integer system;

    @Schema(description = "类型名称")
    private String type;
}

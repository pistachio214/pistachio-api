package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @date: 2023/08/09 16:41
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleListRequest extends ReqPageDto {

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色编码")
    private String code;

}

package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: user list dto
 * @date: 2023/08/09 16:05
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserListRequest extends ReqPageDto {

    @Schema(description = "管理员账号")
    private String username;
}

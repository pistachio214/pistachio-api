package com.pistachio.system.dto.req;

import com.pistachio.system.dto.ReqPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @date: 2023/08/25 9:54
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class OperLogListRequest extends ReqPageDto {

    @Schema(description = "操作员名称", defaultValue = "萧十一郎")
    private String operUserName;

    @Schema(description = "开始操作时间", defaultValue = "2023-08-25 09:56:32")
    private String startAt;

    @Schema(description = "结束操作时间", defaultValue = "2023-08-25 09:56:32")
    private String endAt;
}

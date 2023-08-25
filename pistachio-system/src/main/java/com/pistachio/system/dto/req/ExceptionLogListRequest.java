package com.pistachio.system.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @date: 2023/08/25 10:25
 * @author: Pengsy
 */
@Schema(description = "请求传输参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionLogListRequest extends OperLogListRequest {
}

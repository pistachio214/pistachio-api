package com.pistachio.common.utils;

import com.pistachio.common.constant.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author: Pengsy
 * @date: 2023/08/02 16:32
 * @description: 接口统一返回数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {

    @Schema(description = "状态码", defaultValue = "200")
    private Integer code;

    @Schema(description = "执行描述", defaultValue = "success")
    private String message;

    @Schema(name = "data", description = "执行结果", defaultValue = "{}")
    private T data;

    private static <T> R<T> of(Integer code, String message, T data) {
        return new R<>(code, message, data);
    }

    public static <T> R<T> success(int code, String msg, T data) {
        return of(code, msg, data);
    }

    public static <T> R<T> success() {
        return of(HttpStatus.SUCCESS, "success", null);
    }

    public static <T> R<T> success(T data) {
        return of(HttpStatus.SUCCESS, "success", data);
    }

    public static <T> R<T> error() {
        return of(HttpStatus.ERROR, "error", null);
    }

    public static <T> R<T> error(String message) {
        return of(HttpStatus.ERROR, message, null);
    }

    public static <T> R<T> error(Integer code) {
        return of(code, "error", null);
    }

    public static <T> R<T> error(Integer code, String message) {
        return of(code, message, null);
    }
}
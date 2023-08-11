package com.pistachio.common.utils;

import com.pistachio.common.constant.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Pengsy
 * @date: 2023/08/02 16:32
 * @description: 接口统一返回数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {

    /**
     * 状态码
     * @mock 200
     * @since 200、500
     */
    private Integer code;

    /**
     * 文字描述
     * @mock success
     * @since success
     */
    private String message;

    /**
     * 泛型结果
     * @mock Object
     * @since null or Object
     */
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
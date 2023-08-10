package com.pistachio.common.enums;

/**
 * @author: Pengsy
 * @date: 2023/08/01 16:25
 * @description: 限流类型
 */
public enum LimitType {
    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP
}

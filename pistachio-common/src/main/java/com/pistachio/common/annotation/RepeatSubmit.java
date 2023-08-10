package com.pistachio.common.annotation;

import java.lang.annotation.*;

/**
 * @author: Pengsy
 * @date: 2023/08/01 16:57
 * @description: 自定义注解防止表单重复提交
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit
{
    /**
     * 间隔时间(ms)，小于此时间视为重复提交
     */
    public int interval() default 5000;

    /**
     * 提示消息
     */
    public String message() default "不允许重复提交，请稍候再试";
}

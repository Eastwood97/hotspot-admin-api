package com.jsc.hotspot.api.service;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ww
 * @createTime 2020年01月10日
 * @description  定义一个方法级别的@log注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogService {
    String value() default "";
}
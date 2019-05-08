package org.pay.polymer.api.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>
 * 注册方法
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterMethod {

    /**
     * 方法编码
     */
    @AliasFor("method")
    String value() default "";

    /**
     * 方法编码
     */
    @AliasFor("value")
    String method() default "";

    /**
     * 方法地址
     */
    String url() default "";
}

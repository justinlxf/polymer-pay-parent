package org.pay.polymer.provider.annotation;


import java.lang.annotation.*;

/**
 * @program: polymer-pay-provider
 * @description: 支付统一管理日志
 * @author: Mr.Yang
 * @create: 2018-10-28 12:10
 **/
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PaymentLogMnager {

    /**
     * 具体哪种支付平台
     */
    String group() default "";

    /**
     * 支付平台哪种操作
     */
    String module() default "";

    /**
     * 支付类型
     */
    String type() default "";


}

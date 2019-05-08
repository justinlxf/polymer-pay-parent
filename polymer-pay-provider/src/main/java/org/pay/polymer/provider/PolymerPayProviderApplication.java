package org.pay.polymer.provider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @program: polymer-pay-provider
 * @description: 聚合支付启动类
 * @author: Mr.Yang
 * @create: 2018-10-28 12:13
 **/
@SpringBootApplication
public class PolymerPayProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PolymerPayProviderApplication.class)
                .run(args);
    }
}

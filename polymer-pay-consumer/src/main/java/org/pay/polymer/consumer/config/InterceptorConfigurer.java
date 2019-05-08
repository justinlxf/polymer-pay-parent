package org.pay.polymer.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * <p>
 * 配置拦截器
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurationSupport {


    @Bean
    public HandlerInterceptor getReqInterceptor() {
        return new ReqInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getReqInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
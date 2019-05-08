package org.pay.polymer.consumer.config;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.pay.polymer.common.util.PayMentConstants.DUBBO_REGISTRY_ADDRESS;
import static org.pay.polymer.common.util.PayMentConstants.ZOOKEEPER_PREFIX;

/**
 * <p>
 * 初始化处理
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Configuration
@Order(2)
public class InitConfig {

    @Autowired
    Environment environment;

    /**
     * 全局第三方支付返回码
     */
    public static Map<String, String> payCode = new HashMap<>(16);

    /**
     * 统一初始化
     *
     * @author Mr.Yang
     * @date 2018/10/31
     */
    @PostConstruct
    private void init() {
        //所有渠道错误码初始化
        payCodeInit();
    }

    @Bean
    public ZooKeeper zooKeeper() {
        try {
            return new ZooKeeper(Objects.requireNonNull(environment.getProperty(DUBBO_REGISTRY_ADDRESS))
                    .substring(ZOOKEEPER_PREFIX.length()), 60000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 所有渠道错误码初始化
     *
     * @author Mr.Yang
     * @date 2018/10/31
     */
    private void payCodeInit() {
        try {
            //从指定路径加载信息
            Properties properties =
                    PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource("config/paycode.properties"), StandardCharsets.UTF_8));
            //获取所有code
            Enumeration e = properties.propertyNames();
            while (e.hasMoreElements()) {
                //压入返回码
                String key = (String) e.nextElement();
                payCode.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

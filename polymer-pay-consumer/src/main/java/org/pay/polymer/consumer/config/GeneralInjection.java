package org.pay.polymer.consumer.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.pay.polymer.api.annotation.RegisterMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

import static org.pay.polymer.common.util.PayMentConstants.POLYMER_PATH;

/**
 * <p>
 *通用注入
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Configuration
@Order(3)
public class GeneralInjection {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(GeneralInjection.class);

    @Autowired
    private ZooKeeper zooKeeper;

    @Autowired
    WebApplicationContext applicationContext;

    /**
     * 初始化检索所有requestMapping
     *
     * @author Mr.Yang
     * @date 2018/12/7 0007
     */
    @PostConstruct
    public void retrievalRequestMapping() {
        //获取使用RequestMapping注解方法
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        //集合反射
        JSONArray array = new JSONArray();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            JSONArray urlArray = JSONArray.parseArray(JSONObject.toJSONString(p.getPatterns()));
            if (urlArray.isEmpty()) {
                continue;
            }
            //反射获取自定义注解
            RegisterMethod registerMethod = method.getMethodAnnotation(RegisterMethod.class);
            if (null == registerMethod) {
                continue;
            }
            String methodUrl = urlArray.get(0).toString();
            //获取请求路径
            methodUrl = StringUtils.isEmpty(registerMethod.url()) ? methodUrl : registerMethod.url();
            //获取方法
            String methodValue = StringUtils.isEmpty(registerMethod.value()) ? registerMethod.method() : registerMethod.value();
            //key-value
            JSONObject resutl = new JSONObject();
            resutl.put(methodValue, methodUrl);
            array.add(resutl);
        }
        try {
            if (!array.isEmpty()) {
                //是否存在该路径节点
                Stat stat = zooKeeper.exists(POLYMER_PATH, true);
                if (stat != null) {
                    zooKeeper.delete(POLYMER_PATH, 0);
                }
                //加反射的信息加入zk
                zooKeeper.create(POLYMER_PATH, array.toString().getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

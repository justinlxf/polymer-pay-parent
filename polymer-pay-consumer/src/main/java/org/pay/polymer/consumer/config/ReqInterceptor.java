package org.pay.polymer.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static org.pay.polymer.common.util.DesktopEndRequest.getIpAddr;
import static org.pay.polymer.common.util.PayMentConstants.*;

/**
 * <p>
 * 请求拦截
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Component
public class ReqInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(ReqInterceptor.class);

    /**
     * 字符串缓存
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取ip
        String[] ips = getIpAddr(request).split(",");
//        if (this.stringRedisTemplate.opsForHash().hasKey(FIELD_PRIVATE_KEY_LIST, ips[NUM_ZERO])) {
//            //获取私钥信息
//            String privateKey = Objects.requireNonNull(this.stringRedisTemplate.opsForHash().get(FIELD_PRIVATE_KEY_LIST, ips[NUM_ZERO])).toString();
//            //添加到请求中
//            request.setAttribute(FIELD_PRIVATE_KEY, privateKey);
//        }
        return true;
    }
}

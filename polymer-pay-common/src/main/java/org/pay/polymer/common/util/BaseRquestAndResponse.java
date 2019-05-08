package org.pay.polymer.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 封装request、response
 *
 * @program: semitransfer
 * @author: Mr.Yang
 * @date: 2018-07-04 08:49
 * @version:2.0
 **/
public class BaseRquestAndResponse {

    /**
     * 获取request请求
     *
     * @return 返回HttpServletRequest对象
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /**
     * 获取response请求
     *
     * @return 返回HttpServletResponse对象
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取session请求
     *
     * @return 返回HttpSession对象
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

}

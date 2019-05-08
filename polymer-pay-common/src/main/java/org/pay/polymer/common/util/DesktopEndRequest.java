package org.pay.polymer.common.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.pay.polymer.common.util.PayMentConstants.*;
import static org.pay.polymer.common.util.RsaUtils.getPrivateKey;
import static org.pay.polymer.common.util.RsaUtils.privateDecrypt;

/**
 * <p>
 * 请求工具
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class DesktopEndRequest {

    private static Logger logger = LoggerFactory.getLogger(DesktopEndRequest.class);

    /**
     * 从request中获取请求信息
     *
     * @param request 解析信息
     * @return 返回处理的信息
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static JSONObject requestMessage(HttpServletRequest request) {
        return requestMessage(request, null);
    }

    /**
     * 从request中获取请求信息
     *
     * @param request 解析信息
     * @param fields  必填字段
     * @return 返回处理的信息
     * @author Mr.Yang
     * @date 2018/12/2
     */
    public static JSONObject requestMessage(HttpServletRequest request, String... fields) {
        //获取requestParameter信息
        String requestParameter = request.getParameter(FIELD_PARAMS);
        //解密处理
        if (!StringUtils.isEmpty(request.getAttribute(FIELD_PRIVATE_KEY))) {
            try {
                String privatekey = request.getAttribute(FIELD_PRIVATE_KEY).toString();
                requestParameter = privateDecrypt(requestParameter, getPrivateKey(privatekey));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("公私密钥解密失败", e);
                return null;
            }
        }
        //获取requestAttribute信息
        String requestAttribute = String.valueOf(request.getAttribute(FIELD_PARAMS));
        //从请求中获取数据
        if (!StringUtils.isEmpty(requestParameter)
                || !StringUtils.isEmpty(requestAttribute)) {
            //获取参数
            String requestParams = StringUtils.isEmpty(requestParameter) ? requestAttribute : requestParameter;
            try {
                //转换json格式
                JSONObject params = JSONObject.parseObject(requestParams);
                params.put(FIELD_CHECK_STATUS, false);
                if (fields != null) {
                    // 校验请求参数
                    for (String key : fields) {
                        // 判断是否存在该字段、字段是否为空
                        if (!params.containsKey(key) || StringUtils.isEmpty(params.getString(key))) {
                            params.clear();
                            // 请求参数缺失
                            params.put(FIELD_CODE, 40001);
                            params.put(FIELD_MSG, "请求缺少必选参数");
                            params.put(FIELD_CHECK_STATUS, true);
                            return params;
                        }
                    }
                }
                return params;
            } catch (Exception e) {
                logger.error("请求信息转换为JSON格式报错", e);
                e.printStackTrace();
                return null;
            }
        }
        //转换json格式
        JSONObject params = new JSONObject();
        // 请求参数缺失
        params.put(FIELD_CODE, 40001);
        params.put(FIELD_MSG, "请求缺少必选参数");
        params.put(FIELD_CHECK_STATUS, true);
        return params;
    }

    /**
     * 获取用户请求ip
     *
     * @param request 请求对象
     * @return 返回请求ip
     * @author Mr.Yang
     * @date 2019/3/25 0025
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}

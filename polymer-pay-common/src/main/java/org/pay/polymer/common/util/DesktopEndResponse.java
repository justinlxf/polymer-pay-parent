package org.pay.polymer.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.pay.polymer.common.util.PayMentConstants.*;
import static org.pay.polymer.common.util.RsaUtils.getPrivateKey;
import static org.pay.polymer.common.util.RsaUtils.privateEncrypt;

/**
 * <p>
 * 响应工具类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class DesktopEndResponse {

    /**
     * 响应返回信息 默认json
     *
     * @param httpResponse 响应对象
     * @param result       返回内容
     * @author Mr.Yang
     * @date 2019/3/5 0005
     */
    public static void reponseResult(HttpServletResponse httpResponse, JSONObject result) {
        reponseResult(CONTENT_TYPE_JSON, httpResponse, result.toJSONString());
    }


    /**
     * 响应返回信息 默认json (私钥加密)
     *
     * @param request      请求对象
     * @param httpResponse 响应对象
     * @param result       返回内容
     * @author Mr.Yang
     * @date 2019/3/5 0005
     */
    public static void reponseResultEncrypt(HttpServletRequest request, HttpServletResponse httpResponse, JSONObject result) {
        //私钥是否存在
        if (!StringUtils.isEmpty(request.getAttribute(FIELD_PRIVATE_KEY))) {
            //获取私钥
            String privatekey = request.getAttribute(FIELD_PRIVATE_KEY).toString();
            try {
                //移除私钥
                request.removeAttribute(FIELD_PRIVATE_KEY);
                //私钥加密输出
                reponseResult(CONTENT_TYPE_HTML, httpResponse, privateEncrypt(result.toJSONString(), getPrivateKey(privatekey)));
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 响应返回信息 默认html
     *
     * @param httpResponse 响应对象
     * @param result       返回内容
     * @author Mr.Yang
     * @date 2019/3/5 0005
     */
    public static void reponseResult(HttpServletResponse httpResponse, String result) {
        reponseResult(CONTENT_TYPE_HTML, httpResponse, result);
    }

    /**
     * 响应返回信息
     *
     * @param contentType  内容协调器
     * @param httpResponse 响应对象
     * @param result       返回内容
     * @author Mr.Yang
     * @date 2019/3/5 0005
     */
    public static void reponseResult(String contentType, HttpServletResponse httpResponse, String result) {
        httpResponse.setContentType(contentType);
        try {
            //直接将完整的表单html输出到页面
            httpResponse.getWriter().write(result);
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

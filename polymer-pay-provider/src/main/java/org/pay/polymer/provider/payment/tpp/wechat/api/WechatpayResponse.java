package org.pay.polymer.provider.payment.tpp.wechat.api;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

import static org.pay.polymer.common.tpp.wechat.WechatConstants.CODE;
import static org.pay.polymer.common.tpp.wechat.WechatConstants.MSG;


/**
 * <p>
 * API基础响应信息。
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Data
public abstract class WechatpayResponse implements Serializable {

    private static final long serialVersionUID = -7929571302715786520L;
    /**
     * 网关返回码
     */
    private String code;
    /**
     * 网关返回码描述
     */
    private String msg;
    /**
     * 业务返回码
     */
    private String subCode;
    /**
     * 业务返回码描述
     */
    private String subMsg;

    /**
     * 请求参数
     */
    private Map<String, String> params;

    /**
     * 响应信息
     */
    private String body;

    /**
     * 返回服务是否正常
     *
     * @return true为服务不可用
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public boolean isSuccess() {
        return StringUtils.isEmpty(subCode);
    }

    /**
     * 返回服务不可用信息
     *
     * @return 返回不可用信息
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public String getResponseCode() {
        //创建返回值
        JSONObject outcome = new JSONObject();
        outcome.put(CODE, subCode);
        outcome.put(MSG, subMsg);
        return outcome.toJSONString();
    }

}

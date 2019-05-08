package org.pay.polymer.provider.payment.unionpay.api;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

import static org.pay.polymer.common.unionpay.UnionpayConstants.CODE;
import static org.pay.polymer.common.unionpay.UnionpayConstants.MSG;

/**
 * @program: polymer-pay-provider
 * @description: API基础响应信息。
 * @author: Mr.Yang
 * @create: 2018-11-03 13:02
 **/
@Data
public abstract class UnionpayResponse implements Serializable {


    private static final long serialVersionUID = 7453662390336844021L;
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
     * 网页html或json
     */
    private String body;

    private Map<String, String> params;


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

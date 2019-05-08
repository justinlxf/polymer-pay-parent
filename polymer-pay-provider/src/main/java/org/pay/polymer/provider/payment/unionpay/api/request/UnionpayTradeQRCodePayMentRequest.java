package org.pay.polymer.provider.payment.unionpay.api.request;

import com.alibaba.fastjson.JSONObject;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayRequest;
import org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.UnionpayHashMap;
import org.pay.polymer.provider.payment.unionpay.api.response.UnionpayTradeQRCodePayMentResponse;

import java.util.Map;

/**
 * @program: polymer-pay-provider
 * @description: 银联二维码
 * @author: Mr.Yang
 * @create: 2018-11-03 12:57
 **/
public class UnionpayTradeQRCodePayMentRequest implements UnionpayRequest<UnionpayTradeQRCodePayMentResponse> {

    public UnionpayTradeQRCodePayMentRequest() {
    }

    public UnionpayTradeQRCodePayMentRequest(String serverUrl, String bizContent) {
        this.serverUrl = serverUrl;
        this.bizContent = bizContent;
    }

    /**
     * 在线支付信息
     */
    private String bizContent;

    /**
     * 請求地址
     */
    private String serverUrl;

    /**
     * 获取请求信息
     *
     * @return 返回map格式
     * @author Mr.Yang
     * @date 2018/11/3
     */
    @Override
    public Map<String, String> getTextParams() {
        //自定义map
        UnionpayHashMap txtParams = new UnionpayHashMap();
        //字符串转map
        JSONObject reqParams = JSONObject.parseObject(bizContent);
        //遍历操作
        for (Map.Entry<String, Object> entry : reqParams.entrySet()) {
            txtParams.put(entry.getKey(), entry.getValue());
        }
        return txtParams;
    }

    @Override
    public Class<UnionpayTradeQRCodePayMentResponse> getResponseClass() {
        return UnionpayTradeQRCodePayMentResponse.class;
    }

    @Override
    public String getServerUrl() {
        return this.serverUrl;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

}

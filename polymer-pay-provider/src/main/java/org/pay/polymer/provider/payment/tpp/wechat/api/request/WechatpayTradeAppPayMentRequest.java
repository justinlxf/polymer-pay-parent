package org.pay.polymer.provider.payment.tpp.wechat.api.request;

import com.alibaba.fastjson.JSONObject;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayRequest;
import org.pay.polymer.provider.payment.tpp.wechat.api.internal.util.WechatpayHashMap;
import org.pay.polymer.provider.payment.tpp.wechat.api.response.WechatpayTradeAppPayMentResponse;

import java.util.Map;

/**
 * @program: polymer-pay-provider
 * @description: 微信App支付
 * @author: Mr.Yang
 * @create: 2018-11-03 12:57
 **/
public class WechatpayTradeAppPayMentRequest implements WechatpayRequest<WechatpayTradeAppPayMentResponse> {

    public WechatpayTradeAppPayMentRequest() {

    }

    public WechatpayTradeAppPayMentRequest(String serverUrl, String key, String bizContent) {
        this.serverUrl = serverUrl;
        this.key = key;
        this.bizContent = bizContent;
    }

    /**
     * 請求地址
     */
    private String serverUrl;

    /**
     * 秘钥
     */
    private String key;

    /**
     * 在线支付信息
     */
    private String bizContent;

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
        WechatpayHashMap txtParams = new WechatpayHashMap();
        //字符串转map
        JSONObject reqParams = JSONObject.parseObject(bizContent);
        //遍历操作
        for (Map.Entry<String, Object> entry : reqParams.entrySet()) {
            txtParams.put(entry.getKey(), entry.getValue());
        }
        return txtParams;
    }

    @Override
    public Class<WechatpayTradeAppPayMentResponse> getResponseClass() {
        return WechatpayTradeAppPayMentResponse.class;
    }


    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    @Override
    public String getServerUrl() {
        return this.serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getCertPath() {
        return null;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

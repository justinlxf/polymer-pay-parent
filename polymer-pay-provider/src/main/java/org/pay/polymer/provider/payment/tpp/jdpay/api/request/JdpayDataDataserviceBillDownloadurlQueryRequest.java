package org.pay.polymer.provider.payment.tpp.jdpay.api.request;

import com.alibaba.fastjson.JSONObject;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayRequest;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.JdpayHashMap;
import org.pay.polymer.provider.payment.tpp.jdpay.api.response.JdpayDataDataserviceBillDownloadurlQueryResponse;

import java.util.Map;


/**
 * <p>
 * 京东对账单
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class JdpayDataDataserviceBillDownloadurlQueryRequest implements JdpayRequest<JdpayDataDataserviceBillDownloadurlQueryResponse> {

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
        JdpayHashMap txtParams = new JdpayHashMap();
        //字符串转map
        JSONObject reqParams = JSONObject.parseObject(bizContent);
        //遍历操作
        for (Map.Entry<String, Object> entry : reqParams.entrySet()) {
            txtParams.put(entry.getKey(), entry.getValue());
        }
        return txtParams;
    }

    @Override
    public Class<JdpayDataDataserviceBillDownloadurlQueryResponse> getResponseClass() {
        return JdpayDataDataserviceBillDownloadurlQueryResponse.class;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

}

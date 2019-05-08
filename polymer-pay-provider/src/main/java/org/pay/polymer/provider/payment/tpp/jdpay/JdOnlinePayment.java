package org.pay.polymer.provider.payment.tpp.jdpay;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.tpp.jdpay.JdPayment;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.tpp.jdpay.api.DefaultJdpayClient;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayApiException;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayClient;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeOnlinePayMentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.*;
import static org.pay.polymer.provider.payment.tpp.jdpay.JdPublicService.*;

/**
 * <p>
 * 京东在线支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = JDPAY_ONLINE)
public class JdOnlinePayment implements JdPayment {

    private static Logger logger = LoggerFactory.getLogger(JdOnlinePayment.class);


    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_ONLINE, module = JD_MODULE_PAY)
    public String doPay(PayMent doPay) {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(doPay));
        //获取拼接map
        Map<String, String> clientMap = spliceClientMap(payJson);
        //PC端
        String serviceUrl;
        if (JD_TYPE_PAGE.equals(payJson.getString(TYPE))) {
            serviceUrl = PC_SAVE_ORDER;
        } else {
            serviceUrl = H_SAVE_ORDER;
        }
        //在线支付
        JdpayTradeOnlinePayMentRequest request = new JdpayTradeOnlinePayMentRequest();
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //京东服务器主动通知商户服务器里指定的页面http/https路径。
        if (!bizContent.containsKey(NOTIFY_URL)) {
            bizContent.put(NOTIFY_URL, String.valueOf(clientMap.get(NOTIFY_URL)));
        }
        //回调接口
        if (!bizContent.containsKey(CALLBACK_URL)) {
            bizContent.put(CALLBACK_URL, String.valueOf(clientMap.get(CALLBACK_URL)));
        }
        //订单类型 固定值：0或者1 （0：实物，1：虚拟）
        if (!bizContent.containsKey(FIELD_ORDER_TYPE)) {
            bizContent.put(FIELD_ORDER_TYPE, ORDER_TYPE);
        }
        //货币种类
        if (!bizContent.containsKey(FIELD_CURRENCY_TYPE)) {
            bizContent.put(FIELD_CURRENCY_TYPE, CURRENCY_TYPE);
        }
        //重新获取值覆盖
        clientMap = jsonToMap(clientMap);
        //客户端实例
        JdpayClient client = new DefaultJdpayClient(serviceUrl, clientMap.get(MERCHANT),
                clientMap.get(JDPAY_PUBLIC_KEY), clientMap.get(DES_KEY),
                clientMap.get(PRIVATE_KEY), clientMap.get(CHARSET));
        //填充业务参数
        request.setBizContent(bizContent.toString());
        try {
            //调用SDK生成表单
            return processReponseResult(client.pageExecute(request));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Online^_^Pay^_^Error^_^{}", e);
        }
        return null;
    }
}

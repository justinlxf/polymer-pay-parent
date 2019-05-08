package org.pay.polymer.provider.payment.tpp.jdpay;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.tpp.jdpay.JdPayment;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.tpp.jdpay.api.DefaultJdpayClient;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayApiException;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayClient;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeStripePayMentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.*;
import static org.pay.polymer.provider.payment.tpp.jdpay.JdPublicService.*;


/**
 * <p>
 * 京东付款码
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = JDPAY_STRIPE)
public class JdStripePayment implements JdPayment {

    private static Logger logger = LoggerFactory.getLogger(JdStripePayment.class);


    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_STRIPE, module = JD_MODULE_PAY)
    public String doPay(PayMent doPay) {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(doPay));
        //获取拼接map
        Map<String, String> clientMap = spliceClientMap(payJson);
        //扫描二维码支付
        JdpayTradeStripePayMentRequest request = new JdpayTradeStripePayMentRequest();
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //京东服务器主动通知商户服务器里指定的页面http/https路径。
        if (!bizContent.containsKey(NOTIFY_URL)) {
            bizContent.put(NOTIFY_URL, String.valueOf(clientMap.get(NOTIFY_URL)));
        }
        //重新获取值覆盖
        clientMap = jsonToMap(clientMap);
        //客户端实例
        JdpayClient client = new DefaultJdpayClient(FKM_PAY, clientMap.get(MERCHANT),
                clientMap.get(JDPAY_PUBLIC_KEY), clientMap.get(DES_KEY),
                clientMap.get(PRIVATE_KEY), clientMap.get(CHARSET));
        //填充业务参数
        request.setBizContent(bizContent.toString());
        try {
            //调用SDK生成表单
            return processReponseResult(client.execute(request));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Stripe^_^Pay^_^Error^_^{}", e);
        }
        return null;
    }
}

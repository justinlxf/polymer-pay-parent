package org.pay.polymer.provider.payment.tpp.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.tpp.alipay.AlipayPayment;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.pay.polymer.common.tpp.alipay.AlipayConstants.*;
import static org.pay.polymer.provider.payment.tpp.alipay.AlipayPublicService.processResponseResult;
import static org.pay.polymer.provider.payment.tpp.alipay.AlipayPublicService.spliceClientMap;


/**
 * <p>
 * 收银员使用扫码设备读取用户手机支付宝“付款码”/
 *  声波获取设备（如麦克风）读取用户手机支付宝的声波信息后，
 *  将二维码或条码信息/声波信息通过本接口上送至支付宝发起支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = ALI_STRIPE)
public class AlipayStripePayment implements AlipayPayment {

    private static Logger logger = LoggerFactory.getLogger(AlipayStripePayment.class);


    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_STRIPE, module = ALI_MODULE_PAY)
    public String doPay(PayMent doPay) {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(doPay));
        //获取拼接map
        Map<String, Object> clientMap = spliceClientMap(payJson);
        //获取客户端实例
        AlipayClient alipayClient = (DefaultAlipayClient) clientMap.get(ALI_PAY_SDK);
        //创建API对应的request
        AlipayTradePayRequest alipayRequest = new AlipayTradePayRequest();
        //业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //支付宝服务器主动通知商户服务器里指定的页面http/https路径。
        if (bizContent.containsKey(NOTIFY_URL)) {
            alipayRequest.setNotifyUrl(bizContent.getString(NOTIFY_URL));
        } else {
            alipayRequest.setNotifyUrl(String.valueOf(clientMap.get(NOTIFY_URL)));
        }
        //填充业务参数
        alipayRequest.setBizContent(bizContent.toString());
        try {
            //调用SDK生成表单
            return processResponseResult(alipayClient.execute(alipayRequest));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Stripe^_^Pay^_^Error^_^{}", e);
        }
        return null;
    }
}

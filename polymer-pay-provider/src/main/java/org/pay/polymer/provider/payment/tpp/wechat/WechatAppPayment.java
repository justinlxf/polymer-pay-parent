package org.pay.polymer.provider.payment.tpp.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.tpp.wechat.WechatPayment;
import org.pay.polymer.api.tpp.wechat.Wechatpay;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.tpp.wechat.api.DefaultWechatpayClient;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayApiException;
import org.pay.polymer.provider.payment.tpp.wechat.api.request.WechatpayTradeAppPayMentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.tpp.wechat.WechatConstants.*;
import static org.pay.polymer.provider.payment.tpp.wechat.WechatPublicService.*;


/**
 * <p>
 * 微信app支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = WECHATPAY_APP)
public class WechatAppPayment implements WechatPayment {

    private static Logger logger = LoggerFactory.getLogger(WechatAppPayment.class);


    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_APP, module = WECHAT_MODULE_PAY)
    public String doPay(PayMent doPay) {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(doPay));
        //获取实例化
        Wechatpay wechat = spliceClient(payJson);
        //转换请求参数
        JSONObject bizContent = convertParams(payJson, wechat);
        //交易类型
        bizContent.put(TRADE_TYPE, TYPE_APP);
        //微信App支付
        WechatpayTradeAppPayMentRequest request = new WechatpayTradeAppPayMentRequest(
                UNIFIEDORDER_URL_SUFFIX, wechat.getKey(), bizContent.toString());
        try {
            //调用SDK生成表单
            return processResponseResult(new DefaultWechatpayClient().execute(request));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^WechatPay^_^App^_^Pay^_^Error^_^{}", e);
        }
        return null;
    }
}

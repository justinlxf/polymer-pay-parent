package org.pay.polymer.provider.payment.unionpay;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.unionpay.Unionpay;
import org.pay.polymer.api.unionpay.UnionpayPayment;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.unionpay.api.DefaultUnionpayClient;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayApiException;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayClient;
import org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.SDKConstants;
import org.pay.polymer.provider.payment.unionpay.api.request.UnionpayTradeStripePayMentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.unionpay.UnionpayConstants.*;
import static org.pay.polymer.provider.payment.unionpay.UnionpayPublicService.*;


/**
 * <p>
 * 银联付款码
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = UNIONPAY_STRIPE)
public class UnionPayStripePayMent implements UnionpayPayment {

    private static Logger logger = LoggerFactory.getLogger(UnionPayStripePayMent.class);


    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_STRIPE, module = UNIONPAY_MODULE_PAY)
    public String doPay(PayMent doPay) {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(doPay));
        //银联信息
        Unionpay unionpay = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //银联服务器主动通知商户服务器里指定的页面http/https路径。
        if (!bizContent.containsKey(BACK_URL)) {
            bizContent.put(BACK_URL, unionpay.getBackUrl());
        }
        //请求参数修改
        splicingParameter(UNIONPAY_TYPE_QR_CODE_TYPE, bizContent);
        //交易类型 01-消费
        bizContent.put(FIELD_TXN_TYPE, TXN_TYPE);
        //交易子类型  06：二维码消费
        bizContent.put(FIELD_TXN_SUB_TYPE, TXN_SUB_TYPE_SIX);
        //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
        bizContent.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_WAP);
        //银联付款码支付
        UnionpayTradeStripePayMentRequest request = new UnionpayTradeStripePayMentRequest(SDKConstants.GATEWAY_BACK_TRANS_REQ
                , bizContent.toString());
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(spliceClient(payJson));
        //执行请求信息
        try {
            return processReponseResult(client.execute(request));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Stripe^_^Pay^_^Error^_^{}", e);
        }
        return null;
    }
}

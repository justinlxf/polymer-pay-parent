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
import org.pay.polymer.provider.payment.unionpay.api.request.UnionpayTradeUnifiedPayMentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.unionpay.UnionpayConstants.*;
import static org.pay.polymer.provider.payment.unionpay.UnionpayPublicService.processReponseResult;
import static org.pay.polymer.provider.payment.unionpay.UnionpayPublicService.spliceClient;


/**
 * <p>
 * 银联在线网关支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = UNIONPAY_UNIFIED)
public class UnionPayUnifiedPayMent implements UnionpayPayment {

    private static Logger logger = LoggerFactory.getLogger(UnionPayUnifiedPayMent.class);


    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_UNIFIED, module = UNIONPAY_MODULE_PAY)
    public String doPay(PayMent doPay) {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(doPay));
        //银联信息
        Unionpay unionpay = spliceClient(payJson);
        //在线网关支付
        UnionpayTradeUnifiedPayMentRequest request = new UnionpayTradeUnifiedPayMentRequest();
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //银联服务器主动通知商户服务器里指定的页面http/https路径。
        if (!bizContent.containsKey(BACK_URL)) {
            bizContent.put(BACK_URL, unionpay.getBackUrl());
        }
        //回调接口
        if (!bizContent.containsKey(FRONT_URL)) {
            bizContent.put(FRONT_URL, unionpay.getFrontUrl());
        }
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(unionpay);
        //填充业务参数
        request.setBizContent(bizContent.toString());
        try {
            //调用SDK生成表单
            return processReponseResult(client.pageExecute(request));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Unified^_^Pay^_^Error^_^{}", e);
        }
        return null;
    }
}

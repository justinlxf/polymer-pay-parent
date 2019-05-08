package org.pay.polymer.provider.payment.tpp.wechat;

import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.tpp.wechat.WechatConstants.*;
import static org.pay.polymer.provider.payment.tpp.wechat.WechatPublicService.*;

/**
 * <p>
 *微信公共接口服务（查询、退款、账单等）
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = WECHATPAY_COMMON)
public class WechatPublicPayment implements PayMentInterface {

    private static Logger logger = LoggerFactory.getLogger(WechatPublicPayment.class);


    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_PUBLIC, module = WECHAT_MODULE_PAY_QUERY)
    public String doPayQuery(PayMent payQuery) {
        //支付查询
        try {
            //执行
            return processResponseResult(payQuery(payQuery));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^Wechatpay^_^Public^_^PayQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_PUBLIC, module = WECHAT_MODULE_REFUND)
    public String doRefund(PayMent refund) {
        //支付退款
        try {
            //执行
            return processResponseResult(payRefund(refund));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^Wechatpay^_^Public^_^Refund^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_PUBLIC, module = WECHAT_MODULE_REFUND_QUERY)
    public String doRefundQuery(PayMent refundQuery) {
        //支付查询
        try {
            //执行
            return processResponseResult(payRefundQuery(refundQuery));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^Wechatpay^_^Public^_^RefundQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_PUBLIC, module = WECHAT_MODULE_REFUND_CLOSE)
    public String doClose(PayMent close) {
        //支付关单
        try {
            //执行
            return processResponseResult(payClose(close));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^Wechatpay^_^Public^_^Close^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_PUBLIC, module = WECHAT_MODULE_CANCEL)
    public String doCancel(PayMent cancel) {
        //交易撤销
        try {
            return processResponseResult(payReverse(cancel));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^Wechatpay^_^Public^_^Reverse^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    public String doSync(PayMent sync) {
        return null;
    }

    @Override
    @PaymentLogMnager(group = WECHAT_PAY, type = WECHAT_TYPE_PUBLIC, module = WECHAT_MODULE_DOWNLOAD)
    public String downloadurl(PayMent download) {
        //下载对账单
        try {
            return processResponseResult(payDownloadBill(download));
        } catch (WechatpayApiException e) {
            logger.error("Polymer^_^Wechatpay^_^Public^_^Download^_^Error^_^{}", e);
        }
        return null;
    }
}

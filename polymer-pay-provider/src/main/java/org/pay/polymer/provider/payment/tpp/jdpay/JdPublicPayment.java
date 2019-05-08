package org.pay.polymer.provider.payment.tpp.jdpay;

import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.*;
import static org.pay.polymer.provider.payment.tpp.jdpay.JdPublicService.*;


/**
 * <p>
 * 京东公共接口服务（查询、退款、账单等）
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = JDPAY_COMMON)
public class JdPublicPayment implements PayMentInterface {

    private static Logger logger = LoggerFactory.getLogger(JdPublicPayment.class);


    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_PUBLIC, module = JD_MODULE_PAY_QUERY)
    public String doPayQuery(PayMent payQuery) {
        //支付查询
        try {
            //执行
            return processReponseResult(payQuery(payQuery));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Public^_^PayQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_PUBLIC, module = JD_MODULE_REFUND)
    public String doRefund(PayMent refund) {
        //支付退款
        try {
            //执行
            return processReponseResult(payRefund(refund));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Public^_^Refund^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_PUBLIC, module = JD_MODULE_REFUND_QUERY)
    public String doRefundQuery(PayMent refundQuery) {
        //支付查询
        try {
            return processReponseResult(payQuery(refundQuery));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Public^_^RefundQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    public String doClose(PayMent close) {
        return null;
    }

    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_PUBLIC, module = JD_MODULE_CANCEL)
    public String doCancel(PayMent cancel) {
        //交易撤销
        try {
            //执行
            return processReponseResult(payCancel(cancel));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Public^_^Cancel^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    public String doSync(PayMent sync) {
        return null;
    }

    @Override
    @PaymentLogMnager(group = JD_PAY, type = JD_TYPE_PUBLIC, module = JD_MODULE_DOWNLOAD)
    public String downloadurl(PayMent download) {
        //下载对账单
        try {
            //执行
            return processReponseResult(payDownloadurl(download, "D:\\logs"));
        } catch (JdpayApiException e) {
            logger.error("Polymer^_^Jdpay^_^Public^_^Download^_^Error^_^{}", e);
        }
        return null;
    }
}

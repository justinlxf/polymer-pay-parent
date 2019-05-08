package org.pay.polymer.provider.payment.unionpay;

import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.unionpay.UnionpayConstants.*;
import static org.pay.polymer.provider.payment.unionpay.UnionpayPublicService.*;


/**
 * <p>
 * 银联公共接口服务（查询、退款、账单等）
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = UNIONPAY_COMMON)
public class UnionpayPublicPayment implements PayMentInterface {

    private static Logger logger = LoggerFactory.getLogger(UnionpayPublicPayment.class);


    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_PUBLIC, module = JD_MODULE_PAY_QUERY)
    public String doPayQuery(PayMent payQuery) {
        //支付查询
        try {
            return processReponseResult(payOnlineQuery(payQuery));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Public^_^PayQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_PUBLIC, module = UNIONPAY_MODULE_REFUND)
    public String doRefund(PayMent refund) {
        //支付退款
        try {
            return processReponseResult(payRefund(refund));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Public^_^Refund^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_PUBLIC, module = UNIONPAY_MODULE_REFUND_QUERY)
    public String doRefundQuery(PayMent refundQuery) {
        return null;
    }

    @Override
    public String doClose(PayMent close) {
        return null;
    }

    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_PUBLIC, module = UNIONPAY_MODULE_CANCEL)
    public String doCancel(PayMent cancel) {
        //支付撤销
        try {
            return processReponseResult(payCancel(cancel));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Cancel^_^Refund^_^Error^_^{}", e);
        }
        return null;
    }

    /**
     * 付款码冲正服务
     * 冲正必须与原始消费在同一天（准确讲是昨日23:00至本日23:00之间）。
     * 冲正交易，仅用于超时无应答等异常场景，
     * 只有发生支付系统超时或者支付结果未知时可调用冲正，
     * 其他正常支付的订单如果需要实现相通功能，请调用消费撤销或者退货。
     *
     * @param deacidize 请求参数
     * @return 返回结果信息
     * @author Mr.Yang
     * @date 2018/11/25
     */
    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_PUBLIC, module = UNIONPAY_MODULE_DEACIDIZE)
    public String doSync(PayMent deacidize) {
        //冲正
        try {
            return processReponseResult(payDeacidize(deacidize));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Public^_^Deacidize^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = UNION_PAY, type = UNIONPAY_TYPE_PUBLIC, module = UNIONPAY_MODULE_DOWNLOAD)
    public String downloadurl(PayMent download) {
        //冲正
        try {
            return processReponseResult(payDownloadbill(download));
        } catch (UnionpayApiException e) {
            logger.error("Polymer^_^Unionpay^_^Public^_^Download^_^Error^_^{}", e);
        }
        return null;
    }
}

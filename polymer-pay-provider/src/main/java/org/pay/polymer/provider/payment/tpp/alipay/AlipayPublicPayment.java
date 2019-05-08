package org.pay.polymer.provider.payment.tpp.alipay;

import com.alipay.api.AlipayApiException;
import org.apache.dubbo.config.annotation.Service;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.provider.annotation.PaymentLogMnager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.pay.polymer.common.tpp.alipay.AlipayConstants.*;
import static org.pay.polymer.provider.payment.tpp.alipay.AlipayPublicService.*;


/**
 * <p>
 * 付宝公共接口服务（查询、退款、账单等）
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Service(group = ALI_COMMON)
public class AlipayPublicPayment implements PayMentInterface {

    private static Logger logger = LoggerFactory.getLogger(AlipayPagePayment.class);

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_PAY_QUERY)
    public String doPayQuery(PayMent payQuery) {
        //支付查询
        try {
            //执行
            return processResponseResult(payQuery(payQuery));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^PayQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_REFUND)
    public String doRefund(PayMent refund) {
        //支付退款
        try {
            //执行
            return processResponseResult(payRefund(refund));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^Refund^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_REFUND_QUERY)
    public String doRefundQuery(PayMent refundQuery) {
        //支付退款查询
        try {
            //执行
            return processResponseResult(payRefundQuery(refundQuery));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^RefundQuery^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_CLOSE)
    public String doClose(PayMent close) {
        //交易关闭
        try {
            //执行
            return processResponseResult(payClose(close));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^Close^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_CANCEL)
    public String doCancel(PayMent cancel) {
        //交易撤销
        try {
            //执行
            return processResponseResult(payCancel(cancel));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^Cancel^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_SYNC)
    public String doSync(PayMent sync) {
        //交易信息同步
        try {
            //执行
            return processResponseResult(paySync(sync));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^Sync^_^Error^_^{}", e);
        }
        return null;
    }

    @Override
    @PaymentLogMnager(group = ALI_PAY, type = ALI_TYPE_PUBLIC, module = ALI_MODULE_DOWNLOAD)
    public String downloadurl(PayMent download) {
        //下载对账单
        try {
            //执行
            return processResponseResult(payDownload(download));
        } catch (AlipayApiException e) {
            logger.error("Polymer^_^Alipay^_^Public^_^Download^_^Error^_^{}", e);
        }
        return null;
    }
}

package org.pay.polymer.api.tpp.alipay;

import org.pay.polymer.api.PayMent;

/**
 * <p>
 * 支付宝支付接口
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@FunctionalInterface
public interface AlipayPayment {

    /**
     * 支付方法
     *
     * @param doPay 支付参数
     * @return 返回支付结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doPay(PayMent doPay);
}

package org.pay.polymer.api.tpp.wechat;

import org.pay.polymer.api.PayMent;

/**
 * @program: polymer-pay-parent
 * @description: 微信支付接口
 * @author: Mr.Yang
 * @create: 2018-10-28 18:43
 **/
@FunctionalInterface
public interface WechatPayment {

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

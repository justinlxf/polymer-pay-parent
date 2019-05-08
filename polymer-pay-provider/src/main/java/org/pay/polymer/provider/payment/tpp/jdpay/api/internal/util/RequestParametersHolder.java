package org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util;

/**
 * @program: polymer-pay-provider
 * @description: 请求参数包装
 * @author: Mr.Yang
 * @create: 2018-11-03 15:27
 **/
public class RequestParametersHolder {

    private JdpayHashMap protocalMustParams;
    private JdpayHashMap protocalOptParams;
    private JdpayHashMap applicationParams;

    public JdpayHashMap getProtocalMustParams() {
        return protocalMustParams;
    }
    public void setProtocalMustParams(JdpayHashMap protocalMustParams) {
        this.protocalMustParams = protocalMustParams;
    }
    public JdpayHashMap getProtocalOptParams() {
        return protocalOptParams;
    }
    public void setProtocalOptParams(JdpayHashMap protocalOptParams) {
        this.protocalOptParams = protocalOptParams;
    }
    public JdpayHashMap getApplicationParams() {
        return applicationParams;
    }
    public void setApplicationParams(JdpayHashMap applicationParams) {
        this.applicationParams = applicationParams;
    }
}

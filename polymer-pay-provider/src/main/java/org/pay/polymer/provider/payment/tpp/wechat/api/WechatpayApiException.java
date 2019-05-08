package org.pay.polymer.provider.payment.tpp.wechat.api;


/**
 * <p>
 * 微信支付异常
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class WechatpayApiException extends Exception {

    private static final long serialVersionUID = -7724983930394919114L;

    public WechatpayApiException() {
        super();
    }

    public WechatpayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatpayApiException(String message) {
        super(message);
    }

    public WechatpayApiException(Throwable cause) {
        super(cause);
    }

    public WechatpayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
    }
}

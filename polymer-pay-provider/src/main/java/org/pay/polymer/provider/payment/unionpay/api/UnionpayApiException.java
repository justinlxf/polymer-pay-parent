package org.pay.polymer.provider.payment.unionpay.api;

/**
 * @program: polymer-pay-parent
 * @description: 银联自定义异常
 * @author: Mr.Yang
 * @create: 2018-11-24 14:17
 **/
public class UnionpayApiException extends Exception {


    private static final long serialVersionUID = -4816113236101214469L;

    public UnionpayApiException() {
        super();
    }

    public UnionpayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnionpayApiException(String message) {
        super(message);
    }

    public UnionpayApiException(Throwable cause) {
        super(cause);
    }

    public UnionpayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
    }
}

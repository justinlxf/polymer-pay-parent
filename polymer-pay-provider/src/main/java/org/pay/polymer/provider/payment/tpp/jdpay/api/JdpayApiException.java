package org.pay.polymer.provider.payment.tpp.jdpay.api;


/**
 * <p>
 * 京东支付异常
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class JdpayApiException extends Exception {

    private static final long serialVersionUID = -50339674599486368L;


    public JdpayApiException() {
        super();
    }

    public JdpayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdpayApiException(String message) {
        super(message);
    }

    public JdpayApiException(Throwable cause) {
        super(cause);
    }

    public JdpayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
    }
}

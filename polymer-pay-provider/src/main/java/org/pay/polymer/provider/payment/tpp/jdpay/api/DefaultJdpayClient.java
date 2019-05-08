package org.pay.polymer.provider.payment.tpp.jdpay.api;


/**
 * <p>
 * 京东默认客户端sdk
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class DefaultJdpayClient extends AbstractJdpayClient {

    public DefaultJdpayClient(String serverUrl, String merchant, String privateKey,
                              String desKey, String charset) {
        super(serverUrl, merchant, charset, privateKey, desKey);
    }


    public DefaultJdpayClient(String serverUrl, String merchant, String jdpayPublicKey, String desKey, String privateKey,
                              String charset) {
        super(serverUrl, merchant, charset, privateKey, jdpayPublicKey, desKey);
    }
}

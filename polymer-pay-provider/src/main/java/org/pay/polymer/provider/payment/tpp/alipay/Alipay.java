package org.pay.polymer.provider.payment.tpp.alipay;

import lombok.Data;


/**
 * <p>
 * 支付宝公共参数
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Data
public class Alipay {

    /**
     * 默认支付宝网关
     */
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 应用ID,您的APPID
     */
    private String appId;

    /**
     * 签名类型
     */
    private String signType = "RSA2";

    /**
     * 数据交互格式
     */
    private String format = "json";

    /**
     * 字符集
     */
    private String charset = "utf-8";
    /**
     * 商户私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    private String notifyUrl;

    /**
     * 同步返回地址，HTTP/HTTPS开头字符串
     */
    private String returnUrl;


}

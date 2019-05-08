package org.pay.polymer.api.tpp.wechat;

import lombok.Data;
import org.pay.polymer.api.PayMent;

/**
 * @program: polymer-pay-parent
 * @description: 微信支付基类
 * @author: Mr.Yang
 * @create: 2018-11-17 15:09
 **/
@Data
public class Wechatpay extends PayMent {

    private static final long serialVersionUID = 5028823134551944608L;
    /**
     * 微信分配的公众账号ID
     */
    private String appid;

    /**
     * 微信支付分配的商户号
     */
    private String mch_id;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 通知地址
     */
    private String notify_url;

    /**
     * 密钥
     */
    private String key;

    /**
     * 证书路径
     */
    private String certPath;
}

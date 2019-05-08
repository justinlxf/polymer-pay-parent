package org.pay.polymer.api.tpp.jdpay;

import lombok.Data;
import org.pay.polymer.api.PayMent;

import java.io.Serializable;

/**
 * @program: polymer-pay-api
 * @description: 京东模型
 * @author: Mr.Yang
 * @create: 2018-10-28 13:23
 **/
@Data
public class JdpayModel extends PayMent implements Serializable {

    private static final long serialVersionUID = -3807428913639503869L;

    public JdpayModel(String merchant) {
        this.merchant = merchant;
        this.type = "Page";
    }

    public JdpayModel(String merchant, String type) {
        this.merchant = merchant;
        this.type = type;
    }

    /**
     * 类型
     */
    private String type;

    /**
     * 商户号
     */
    private String merchant;

    /**
     * 业务请求参数的集合，最大长度不限
     */
    private String bizContent;
}

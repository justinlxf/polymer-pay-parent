package org.pay.polymer.api.unionpay;

import lombok.Data;
import org.pay.polymer.api.PayMent;

import java.io.Serializable;

/**
 * @program: polymer-pay-api
 * @description: 银联模型
 * @author: Mr.Yang
 * @create: 2018-10-28 13:23
 **/
@Data
public class UnionpayModel extends PayMent implements Serializable {

    private static final long serialVersionUID = -859322174047956773L;

    public UnionpayModel(String merId) {
        this.merId = merId;
    }

    public UnionpayModel(String merId, String refundType) {
        this.merId = merId;
        this.refundType = refundType;
    }

    /**
     * 退款类型 page 电脑 wap手机网页 app手机 qrcode二维码
     */
    private String refundType="page";

    /**
     * 商户号
     */
    private String merId;

    /**
     * 业务请求参数的集合，最大长度不限
     */
    private String bizContent;
}

package org.pay.polymer.api.tpp.wechat;

import lombok.Data;
import org.pay.polymer.api.PayMent;

import java.io.Serializable;

/**
 * @program: polymer-pay-api
 * @description: 微信模型
 * @author: Mr.Yang
 * @create: 2018-10-28 13:23
 **/
@Data
public class WechatpayModel extends PayMent implements Serializable {

    public WechatpayModel(String appid) {
        this.appid = appid;
    }

    /**
     * 商户号
     */
    private String appid;

    /**
     * 业务请求参数的集合，最大长度不限
     */
    private String bizContent;


}

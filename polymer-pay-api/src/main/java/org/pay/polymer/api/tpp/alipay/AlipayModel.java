package org.pay.polymer.api.tpp.alipay;

import lombok.Data;
import org.pay.polymer.api.PayMent;

import java.io.Serializable;


/**
 * <p>
 * 支付宝模型
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Data
public class AlipayModel extends PayMent implements Serializable {

    private static final long serialVersionUID = 6417814473698693472L;

    public AlipayModel(String appId) {
        this.appId = appId;
    }

    /**
     * 支付宝应用appid
     */
    private String appId;

    /**
     * 业务请求参数的集合，最大长度不限
     */
    private String bizContent;
}

package org.pay.polymer.provider.payment.tpp.jdpay.api.response;

import com.jd.jr.pay.gate.signature.vo.JdPayBaseResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.pay.polymer.provider.payment.tpp.jdpay.api.PayTradeVo;
import org.pay.polymer.provider.payment.tpp.jdpay.api.RefundInfo;

import java.util.List;


/**
 * <p>
 * 京东公共响应类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@Data
@XStreamAlias("jdpay")
public class JdpayXmlResponse extends JdPayBaseResponse {

    private String orderId;

    private String merchantName;

    private Long amount;

    private String tradeNum;

    private String qrCode;

    private String expireTime;

    private String currency;

    private String tradeTime;

    private String tradeType;

    private String note;

    private Integer status;

    private String oTradeNum;

    /**
     * 交易列表
     */
    private List<PayTradeVo> payList;

    @XStreamAlias("refList")
    private List<RefundInfo> refList;

}

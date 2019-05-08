package org.pay.polymer.provider.payment.tpp.jdpay.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * <p>
 * 支付实体
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@XStreamAlias("pay")
public class PayTradeVo {
   
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 交易金额
     */
    private Long amount;
    /**
     * 交易币种
     */
    private String currency;
    /**
     * 交易时间 yyyyMMddHHmmss
     */
    private String tradeTime;
    /**
     * 支付明细，不同支付方式的明细信息也不同
     */
    private PayTradeDetail detail;
    

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public PayTradeDetail getDetail() {
        return detail;
    }

    public void setDetail(PayTradeDetail detail) {
        this.detail = detail;
    }

	/** 
	 * @Title:        toString
	 * @param:        @return    
	 * @throws 
	 * @author       mythling
	 * @Date          2016年4月28日 下午2:51:40 
	 */
	@Override
	public String toString() {
		return "PayTradeVo [payType=" + payType + ", amount=" + amount + ", currency=" + currency + ", tradeTime=" + tradeTime
				+ ", detail=" + detail + "]";
	}

	

	
    
    
}

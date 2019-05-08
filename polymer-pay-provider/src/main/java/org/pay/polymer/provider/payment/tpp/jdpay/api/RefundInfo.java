/**   
 * @Title: RefundIno.java 
 * @Package com.jd.jr.pay.demo.model
 * @author mythling   
 * @date 2016年9月26日 下午5:04:01 
 * @version V1.0   
 */
package org.pay.polymer.provider.payment.tpp.jdpay.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * <p>
 * 京东退款信息
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@XStreamAlias("refund")
public class RefundInfo {
	private String tradeNum;

	private String tradeType;

	private String oTradeNum;

	private String amount;

	private String currency;

	private String tradeTime;

	private String status;

	private String note;

	/**
	 * @return the tradeNum
	 */
	public String getTradeNum() {
		return tradeNum;
	}

	/**
	 * @param tradeNum
	 *            the tradeNum to set
	 */
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

	/**
	 * @return the tradeType
	 */
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * @param tradeType
	 *            the tradeType to set
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * @return the oTradeNum
	 */
	public String getoTradeNum() {
		return oTradeNum;
	}

	/**
	 * @param oTradeNum
	 *            the oTradeNum to set
	 */
	public void setoTradeNum(String oTradeNum) {
		this.oTradeNum = oTradeNum;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the tradeTime
	 */
	public String getTradeTime() {
		return tradeTime;
	}

	/**
	 * @param tradeTime
	 *            the tradeTime to set
	 */
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/** 
	 * @Title:        toString
	 * @param:        @return    
	 * @throws 
	 * @author       mythling
	 * @Date          2016年9月27日 上午11:00:33 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RefundInfo [tradeNum=");
		builder.append(tradeNum);
		builder.append(", tradeType=");
		builder.append(tradeType);
		builder.append(", oTradeNum=");
		builder.append(oTradeNum);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", tradeTime=");
		builder.append(tradeTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}
	
	

}

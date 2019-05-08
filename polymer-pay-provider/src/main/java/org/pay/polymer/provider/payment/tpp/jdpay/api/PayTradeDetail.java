package org.pay.polymer.provider.payment.tpp.jdpay.api;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <p>
 * 京东详情
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@XStreamAlias("detail")
public class PayTradeDetail {
	/**
	 * 持卡人人姓名 掩码显示（隐去第一位）
	 */
	private String cardHolderName;
	/**
	 * 持卡人手机号 掩码显示（手机号的前三位与后四位）
	 */
	private String cardHolderMobile;
	/**
	 * 证件类型 ID("0", "身份证"), PASSPORT("1", "护照"), OFFICER("2", "军官证"),
	 * SOLDIER("3", "士兵证"), TWHK_PASSPORT("4", "港奥台通行证"), TEMP_ID("5", "临时身份证"),
	 * HOUSEHOLDREGISTER("6", "户口本"), OTHER("7", "其它类型证件")
	 */
	private String cardHolderType;
	/**
	 * 身份证号
	 */
	private String cardHolderId;
	/**
	 * 卡号 掩码显示（前六位及后四位）
	 */
	private String cardNo;
	/**
	 * 银行编码
	 */
	private String bankCode;
	/**
	 * 银行卡类型 DEBIT_CARD：借记卡CREDIT_CARD：信用卡SEMI_CREDIT_CARD：准贷记卡
	 */
	private String cardType;

	private String cardNum;
	
	 /**
     * 支付金额
     */
    private Long payMoney;

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardHolderMobile() {
		return cardHolderMobile;
	}

	public void setCardHolderMobile(String cardHolderMobile) {
		this.cardHolderMobile = cardHolderMobile;
	}

	public String getCardHolderType() {
		return cardHolderType;
	}

	public void setCardHolderType(String cardHolderType) {
		this.cardHolderType = cardHolderType;
	}

	public String getCardHolderId() {
		return cardHolderId;
	}

	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum
	 *            the cardNum to set
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return the payMoney
	 */
	public Long getPayMoney() {
		return payMoney;
	}

	/**
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}

	/** 
	 * @Title:        toString
	 * @param:        @return    
	 * @throws 
	 * @author       mythling
	 * @Date          2017年1月9日 下午4:28:59 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayTradeDetail [cardHolderName=");
		builder.append(cardHolderName);
		builder.append(", cardHolderMobile=");
		builder.append(cardHolderMobile);
		builder.append(", cardHolderType=");
		builder.append(cardHolderType);
		builder.append(", cardHolderId=");
		builder.append(cardHolderId);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", cardNum=");
		builder.append(cardNum);
		builder.append(", payMoney=");
		builder.append(payMoney);
		builder.append("]");
		return builder.toString();
	}

	

}

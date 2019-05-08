package org.pay.polymer.api.tpp.jdpay;

import lombok.Data;
import org.pay.polymer.api.PayMent;

/**
 * @program: polymer-pay-provider
 * @description: 京东在线支付
 * @author: Mr.Yang
 * @create: 2018-11-03 12:32
 **/
@Data
public class Jdpay extends PayMent {

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 交易类型。GEN-普通支付 ；QR-扫码支付。为空时默认GEN。（扫一扫支付使用QR，SDK支付使用GEN）
     */
    private String tradeType;
    /**
     * 交易信息签名
     */
    private String sign;
    /**
     * 商户号
     */
    private String merchant;

    /**
     * 版本号d
     */
    private String version;
    /**
     * 门店号
     */
    private String device;
    /**
     * 交易流水号
     */
    private String tradeNum;
    /**
     * 交易名称
     */
    private String tradeName;
    /**
     * 交易描述
     */
    private String tradeDesc;
    /**
     * 交易时间 	订单生成时间。格式：“yyyyMMddHHmmss”
     */
    private String tradeTime;
    /**
     * 交易金额 商户订单的资金总额。单位：分，大于0
     */
    private String amount;
    /**
     * 商户备注
     */
    private String note;
    /**
     * 用户IP
     */
    private String ip;
    /**
     * 用户指定卡号
     */
    private String specCardNo;
    /**
     * 用户指定身份证
     */
    private String specId;
    /**
     * 用户指定姓名
     */
    private String specName;
    /**
     * 支付渠道
     */
    private String payChannel;
    /**
     * 用户账号
     */
    private String userId;
    /**
     * 订单失效时长
     */
    private Integer expireTime;
    /**
     * 订单类型 固定值：0或者1 （0：实物，1：虚拟）
     */
    private String orderType;
    /**
     * 业务类型
     */
    private String industryCategoryCode;
    /**
     * 业务类型
     */
    private String industryCategory;
    /**
     * 收款商户
     */
    private String payMerchant;
    /**
     * 厂商编码
     */
    private String vendorId;
    /**
     * 商品信息
     */
    private String goodsInfo;
    /**
     * 业务信息
     */
    private String kjInfo;
    /**
     * 订单商品数量
     */
    private Integer orderGoodsNum;
    /**
     * 收货信息
     */
    private String receiverInfo;
    /**
     * 终端信息
     */
    private String termInfo;
    /**
     * 证书验证模式
     */
    private String cert;
    /**
     * 风控信息
     */
    private String riskInfo;

    /**
     * 分期数 分期数为：3、6、12、24,白条分期前置时必传
     */
    private String installmentNum;

    /**
     * 结算币种
     */
    private String settleCurrency;

    /**
     * 前置支付方式
     */
    private String preProduct;
    /**
     * 异步通知页面地址
     */
    private String notifyUrl;
    /**
     * 同步通知页面
     */
    private String callbackUrl;
    /**
     * 付款码
     */
    private String token;

    /**
     * 货币
     */
    private String currency = "CNY";

    /**
     * 原交易流水号
     */
    private String oTradeNum;

    /**
     * 终端号
     */
    private String termInfoId;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 字符集
     */
    private String charset;

    /**
     * 京东公钥
     */
    private String jdpayPublicKey;

    /**
     * des密钥
     */
    private String desKey;

    /**
     * md5密钥 下载对账单
     */
    private String mdkey;
}

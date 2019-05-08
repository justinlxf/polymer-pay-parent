package org.pay.polymer.api.unionpay;

import lombok.Data;
import org.pay.polymer.api.PayMent;

/**
 * @program: polymer-pay-parent
 * @description: 银联支付基类
 * @author: Mr.Yang
 * @create: 2018-11-18 20:47
 **/
@Data
public class Unionpay extends PayMent {

    /**
     * 版本号.
     */
    public String version;
    /**
     * 证书ID.
     */
    public String certId;
    /**
     * 签名.
     */
    public String signature;
    /**
     * 签名方法.
     */
    public String signMethod;
    /**
     * 编码方式.
     */
    public String encoding;
    /**
     * 交易类型.
     */
    public String txnType;
    /**
     * 交易子类.
     */
    public String txnSubType;
    /**
     * 业务类型.
     */
    public String bizType;
    /**
     * 前台通知地址 .
     */
    public String frontUrl;
    /**
     * 后台通知地址.
     */
    public String backUrl;
    /**
     * 接入类型.
     */
    public String accessType;
    /**
     * 收单机构代码.
     */
    public String acqInsCode;
    /**
     * 商户类别.
     */
    public String merCatCode;
    /**
     * 商户类型.
     */
    public String merType;
    /**
     * 商户代码.
     */
    public String merId;
    /**
     * 商户名称.
     */
    public String merName;
    /**
     * 商户简称.
     */
    public String merAbbr;
    /**
     * 二级商户代码.
     */
    public String subMerId;
    /**
     * 二级商户名称.
     */
    public String subMerName;
    /**
     * 二级商户简称.
     */
    public String subMerAbbr;
    /**
     * Cupsecure 商户代码.
     */
    public String csMerId;
    /**
     * 商户订单号.
     */
    public String orderId;
    /**
     * 交易时间.
     */
    public String txnTime;
    /**
     * 发送时间.
     */
    public String txnSendTime;
    /**
     * 订单超时时间间隔.
     */
    public String orderTimeoutInterval;
    /**
     * 支付超时时间.
     */
    public String payTimeoutTime;
    /**
     * 默认支付方式.
     */
    public String defaultPayType;
    /**
     * 支持支付方式.
     */
    public String supPayType;
    /**
     * 支付方式.
     */
    public String payType;
    /**
     * 自定义支付方式.
     */
    public String customPayType;
    /**
     * 物流标识.
     */
    public String shippingFlag;
    /**
     * 收货地址-国家.
     */
    public String shippingCountryCode;
    /**
     * 收货地址-省.
     */
    public String shippingProvinceCode;
    /**
     * 收货地址-市.
     */
    public String shippingCityCode;
    /**
     * 收货地址-地区.
     */
    public String shippingDistrictCode;
    /**
     * 收货地址-详细.
     */
    public String shippingStreet;
    /**
     * 商品总类.
     */
    public String commodityCategory;
    /**
     * 商品名称.
     */
    public String commodityName;
    /**
     * 商品URL.
     */
    public String commodityUrl;
    /**
     * 商品单价.
     */
    public String commodityUnitPrice;
    /**
     * 商品数量.
     */
    public String commodityQty;
    /**
     * 是否预授权.
     */
    public String isPreAuth;
    /**
     * 币种.
     */
    public String currencyCode;
    /**
     * 账户类型.
     */
    public String accType;
    /**
     * 账号.
     */
    public String accNo;
    /**
     * 支付卡类型.
     */
    public String payCardType;
    /**
     * 发卡机构代码.
     */
    public String issInsCode;
    /**
     * 持卡人信息.
     */
    public String customerInfo;
    /**
     * 交易金额.
     */
    public String txnAmt;
    /**
     * 余额.
     */
    public String balance;
    /**
     * 地区代码.
     */
    public String districtCode;
    /**
     * 附加地区代码.
     */
    public String additionalDistrictCode;
    /**
     * 账单类型.
     */
    public String billType;
    /**
     * 账单号码.
     */
    public String billNo;
    /**
     * 账单月份.
     */
    public String billMonth;
    /**
     * 账单查询要素.
     */
    public String billQueryInfo;
    /**
     * 账单详情.
     */
    public String billDetailInfo;
    /**
     * 账单金额.
     */
    public String billAmt;
    /**
     * 账单金额符号.
     */
    public String billAmtSign;
    /**
     * 绑定标识号.
     */
    public String bindId;
    /**
     * 风险级别.
     */
    public String riskLevel;
    /**
     * 绑定信息条数.
     */
    public String bindInfoQty;
    /**
     * 绑定信息集.
     */
    public String bindInfoList;
    /**
     * 批次号.
     */
    public String batchNo;
    /**
     * 总笔数.
     */
    public String totalQty;
    /**
     * 总金额.
     */
    public String totalAmt;
    /**
     * 文件类型.
     */
    public String fileType;
    /**
     * 文件名称.
     */
    public String fileName;
    /**
     * 批量文件内容.
     */
    public String fileContent;
    /**
     * 商户摘要.
     */
    public String merNote;
    /**
     * 请求方保留域.
     */
    public String reqReserved;
    /**
     * 保留域.
     */
    public String reserved;
    /**
     * 终端号.
     */
    public String termId;
    /**
     * 终端类型.
     */
    public String termType;
    /**
     * 交互模式.
     */
    public String interactMode;
    /**
     * 发卡机构识别模式.
     */
    public String issuerIdentifyMode;
    /**
     * 商户端用户号.
     */
    public String merUserId;
    /**
     * 持卡人IP.
     */
    public String customerIp;
    /**
     * 查询流水号.
     */
    public String queryId;
    /**
     * 原交易查询流水号.
     */
    public String origQryId;
    /**
     * 系统跟踪号.
     */
    public String traceNo;
    /**
     * 交易传输时间.
     */
    public String traceTime;
    /**
     * 清算日期.
     */
    public String settleDate;
    /**
     * 清算币种.
     */
    public String settleCurrencyCode;
    /**
     * 清算金额.
     */
    public String settleAmt;
    /**
     * 清算汇率.
     */
    public String exchangeRate;
    /**
     * 兑换日期.
     */
    public String exchangeDate;
    /**
     * 响应时间.
     */
    public String respTime;
    /**
     * 原交易应答码.
     */
    public String origRespCode;
    /**
     * 原交易应答信息.
     */
    public String origRespMsg;
    /**
     * 应答码.
     */
    public String respCode;
    /**
     * 应答码信息.
     */
    public String respMsg;
    // 新增四个报文字段merUserRegDt merUserEmail checkFlag activateStatus
    /**
     * 商户端用户注册时间.
     */
    public String merUserRegDt;
    /**
     * 商户端用户注册邮箱.
     */
    public String merUserEmail;
    /**
     * 验证标识.
     */
    public String checkFlag;
    /**
     * 开通状态.
     */
    public String activateStatus;
    /**
     * 加密证书ID.
     */
    public String encryptCertId;
    /**
     * 用户MAC、IMEI串号、SSID.
     */
    public String userMac;
    /** 关联交易. */
    // public  String    relationTxnType;
    /**
     * 短信类型
     */
    public String smsType;

    /**
     * 风控信息域
     */
    public String riskCtrlInfo;

    /**
     * IC卡交易信息域
     */
    public String ICTransData;

    /**
     * VPC交易信息域
     */
    public String VPCTransData;

    /**
     * 安全类型
     */
    public String securityType;

    /**
     * 银联订单号
     */
    public String tn;

    /**
     * 分期付款手续费率
     */
    public String instalRate;

    /**
     * 分期付款手续费率
     */
    public String mchntFeeSubsidy;

    /**
     * 签名公钥证书
     */
    public String signPubKeyCert;

    /**
     * 加密公钥证书
     */
    public String encryptPubKeyCert;

    /**
     * 证书类型
     */
    public String certType;

    /**
     *敏感信息加密证书路径
     */
    public String acpEnc;
    /**
     *私钥证书.pfx
     */
    public String acpSign;
    /**
     * 签名证书密码
     */
    public String cerPwd;
    /**
     * 验签中级证书路径(银联提供)
     */
    public String acpMiddle;
    /**
     *验签根证书路径(银联提供)
     */
    public String acpRoot;
}

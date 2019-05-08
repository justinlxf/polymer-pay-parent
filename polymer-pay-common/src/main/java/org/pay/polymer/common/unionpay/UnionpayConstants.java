package org.pay.polymer.common.unionpay;

/**
 * @program: polymer-pay-api
 * @description: 银联支付常量
 * @author: Mr.Yang
 * @create: 2018-10-28 14:45
 **/
public class UnionpayConstants {

    /**
     * 网关返回码
     */
    public static final String CODE = "code";

    /**
     * 网关返回码描述
     */
    public static final String MSG = "msg";

    /**
     * 银联版本号
     */
    public static final String VERSION_NO = "5.1.0";

    /**
     * 银联版本字段
     */
    public static final String FIELD_VERSION = "version";

    /**
     * 字符集字段
     */
    public static final String FIELD_ENCODING = "encoding";

    /**
     * 交易类型字段
     */
    public static final String FIELD_TXN_TYPE = "txnType";

    /**
     * 交易子类字段
     */
    public static final String FIELD_TXN_SUB_TYPE = "txnSubType";

    /**
     * 交易类型
     */
    public static final String TXN_TYPE = "01";

    /**
     * 交易子类
     */
    public static final String TXN_SUB_TYPE = "01";

    /**
     * 交易类型
     */
    public static final String TXN_TYPE_ZERO = "00";

    /**
     * 交易类型 76对账单
     */
    public static final String TXN_TYPE_SEVENTY_SIX = "76";

    /**
     * 交易类型 退款
     */
    public static final String TXN_TYPE_FOUR = "04";

    /**
     * 交易类型 冲正
     */
    public static final String TXN_TYPE_NINETY_NINTH = "99";

    /**
     * 交易子类 消费
     */
    public static final String TXN_SUB_TYPE_ONE = "01";

    /**
     * 交易类型 撤销
     */
    public static final String TXN_TYPE_THIRTY_ONE = "31";

    /**
     * 交易子类
     */
    public static final String TXN_SUB_TYPE_ZERO = "00";

    /**
     * 交易子类 申请消费二维码
     */
    public static final String TXN_SUB_TYPE_SEVEN = "07";

    /**
     * 交易子类 二维码消费
     */
    public static final String TXN_SUB_TYPE_SIX = "06";

    /**
     * 接入类型字段
     */
    public static final String FIELD_ACCESS_TYPE = "accessType";

    /**
     * 接入类型 0：商户直连接入 1：收单机构接入 2：平台商户接入
     */
    public static final String ACCESS_TYPE = "0";

    /**
     * 订单发送时间字段
     */
    public static final String FIELD_TXN_TIME = "txnTime";

    /**
     * 交易币种字段
     */
    public static final String FIELD_CURRENCY_CODE = "currencyCode";

    /**
     * 交易币种字段
     */
    public static final String CURRENCY_CODE = "156";
    /**
     * 交易币种字段
     */
    public static final String FIELD_TXN_AMT = "txnAmt";
    /**
     * 产品类型字段
     */
    public static final String FIELD_BIZ_TYPE = "bizType";
    /**
     * 文件类型 依据实际业务情况定义 参考7.1：商户索取的文件类型约定
     */
    public static final String FIELD_FILE_TYPE = "fileType";

    /**
     * 渠道类型字段
     */
    public static final String FIELD_CHANNEL_TYPE = "channelType";

    /**
     * 产品类型 在线网关
     */
    public static final String BIZ_TYPE = "000201";
    /**
     * 产品类型 二维码
     */
    public static final String BIZ_TYPE_ZERO = "000000";
    /**
     * 产品类型 二维码
     */
    public static final String BIZ_TYPE_THREE = "000301";

    /**
     * 渠道类型 07电脑 平板
     */
    public static final String CHANNEL_TYPE_PC = "07";
    /**
     * 渠道类型 08手机
     */
    public static final String CHANNEL_TYPE_WAP = "08";

    /**
     * 签名类型
     * 非对称签名： 01（表示采用RSA签名） HASH表示散列算法 11：支持散列方式验证SHA-256 12：支持散列方式验证SM3
     */
    public static final String SIGN_METHOD = "01";

    /**
     * 签名类型字段
     */
    public static final String FIELD_SIGN_METHOD = "signMethod";

    /**
     * Date默认时区
     **/
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * 前台通知地址
     **/
    public static final String FRONT_URL = "frontUrl";

    /**
     * 失败交易前台跳转地址
     **/
    public static final String FRONT_FAIL_URL = "frontFailUrl";

    /**
     * 后台通知地址
     **/
    public static final String BACK_URL = "backUrl";

    /**
     * UTF-8字符集
     **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * 默认时间格式
     **/
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";


    /**
     * 默认时间格式
     **/
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 银联支付
     */
    public static final String UNION_PAY = "Unionpay";

    /**
     * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    public static final String BIZ_CONTENT_KEY = "bizContent";

    /**
     * 银联支付类型 在线网关
     */
    public static final String UNIONPAY_TYPE_UNIFIED = "Unified";

    /**
     * 银联支付类型 公共接口（查询、退款、对账单）
     */
    public static final String UNIONPAY_TYPE_PUBLIC = "Public";

    /**
     * 银联公共接口服务（查询、退款、账单等）
     */
    public static final String UNIONPAY_COMMON = "unionpay-common";

    /**
     * 银联app
     */
    public static final String UNIONPAY_APP = "unionpay-app";

    /**
     * 用户扫描银联二维码
     */
    public static final String UNIONPAY_QRCODE = "unionpay-qrcode";

    /**
     * 银联付款码
     */
    public static final String UNIONPAY_STRIPE= "unionpay-stripe";

    /**
     * 银联在线网关支付
     */
    public static final String UNIONPAY_UNIFIED = "unionpay-unified";

    /**
     * 银联sdk
     */
    public static final String UNIONPAY_SDK = "unionpay_sdk";

    /**
     * 银联支付类型 PC网页
     */
    public static final String UNIONPAY_PAGE = "Page";

    /**
     * 银联支付类型 在线支付
     */
    public static final String UNIONPAY_ONLINE = "Online";

    /**
     * 银联支付类型 手机H5
     */
    public static final String UNIONPAY_TYPE_WAP = "Wap";

    /**
     * 银联支付类型 手机App
     */
    public static final String UNIONPAY_TYPE_APP = "App";

    /**
     * 银联支付类型 读取用户手机银联付款码取设备
     */
    public static final String UNIONPAY_TYPE_STRIPE = "Stripe";

    /**
     * 银联支付类型 商户二维码支付接口
     */
    public static final String UNIONPAY_TYPE_QR_CODE = "QRCode";


    /**
     * 支付交易操作类型(支付)
     */
    public static final String UNIONPAY_MODULE_PAY = "Pay";

    /**
     * 支付交易操作类型(支付查询)
     */
    public static final String JD_MODULE_PAY_QUERY = "PayQuery";

    /**
     * 支付交易操作类型(退款)
     */
    public static final String UNIONPAY_MODULE_REFUND = "Refund";


    /**
     * 支付交易操作类型(退款查询)
     */
    public static final String UNIONPAY_MODULE_REFUND_QUERY = "RefundQuery";

    /**
     * 支付交易操作类型(交易关闭)
     */
    public static final String UNIONPAY_MODULE_CLOSE = "Close";

    /**
     * 支付交易操作类型(交易取消)
     */
    public static final String UNIONPAY_MODULE_CANCEL = "Cancel";


    /**
     * 支付交易操作类型(冲正)
     */
    public static final String UNIONPAY_MODULE_DEACIDIZE = "Deacidize";


    /**
     * 支付交易操作类型(交易同步)
     */
    public static final String UNIONPAY_MODULE_SYNC = "Sync";


    /**
     * 支付交易操作类型(下载账单)
     */
    public static final String UNIONPAY_MODULE_DOWNLOAD = "Download";

    /**
     * 商户号
     */
    public static final String FIELD_MERID = "merId";

    /**
     * 退款类型
     */
    public static final String FIELD_REFUND_TYPE = "refundType";

    /**
     * 银联返回信息
     */
    public static final String FIELD_RESP_MSG = "respMsg";


    /**
     * 银联支付类型 商户二维码支付接口
     */
    public static final String UNIONPAY_TYPE_QR_CODE_TYPE = "qrcode";

    /**
     * 银联支付类型 PC网页
     */
    public static final String UNIONPAY_PAGE_TYPE = "page";

    /**
     * 银联支付类型 手机H5
     */
    public static final String UNIONPAY_TYPE_WAP_TYPE = "wap";

    /**
     * 银联支付类型 手机App
     */
    public static final String UNIONPAY_TYPE_APP_TYPE = "app";

    /**
     * 结果信息
     */
    public static String FORM = "form";

    /**
     * 下载地址
     */
    public static final String BILL_DOWNLOAD_URL = "billDownloadUrl";

    /**
     * 交易号
     */
    public static final String UNIONPAY_TRADE_NO = "trade_no";

    /**
     * 交易号
     */
    public static final String ORDER_ID = "orderId";
    /**
     * 查询流水号
     */
    public static final String QUERY_ID = "queryId";
    /**
     * 原交易查询流水号
     */
    public static final String ORIG_ORY_ID = "origQryId";

    /**
     * 原交易商户订单号
     */
    public static final String ORIG_ORDER_ID = "origOrderId";

    /**
     * 门店号
     */
    public static final String UNIONPAY_DEVICE = "device";

    /**
     * 商户名称
     */
    public static final String UNIONPAY_MERCHANTNAME = "merchantName";

    /**
     * 商户门店编号
     */
    public static final String UNIONPAY_STORE_ID= "store_id";

    /**
     * 商户门店编号
     */
    public static final String DEVICE= "device";

    /**
     * 商家订单号
     */
    public static final String UNIONPAY_OUT_TRADE_NO = "out_trade_no";

    /**
     * 交易流水号
     */
    public static final String TRADE_NUM = "tradeNum";


    /**
     * 当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码
     */
    public static final String UNIONPAY_QR_CODE = "qr_code";
    /**
     * 交易订单金额
     */
    public static final String UNIONPAY_TOTAL_AMOUNT = "total_amount";

    /**
     * 本次交易打款给卖家的时间
     */
    public static final String UNIONPAY_SEND_PAY_DATE = "send_pay_date";

    /**
     * 交易支付时间
     */
    public static final String UNIONPAY_GMT_PAYMENT = "gmt_payment";

    /**
     * 交易支付时间
     */
    public static final String TRADE_TIME = "tradeTime";

    /**
     * 本次退款是否发生了资金变化
     */
    public static final String UNIONPAY_FUND_CHANGE = "fund_change";

    /**
     * 退款总金额
     */
    public static final String UNIONPAY_REFUND_FEE = "refund_fee";

    /**
     * 退款支付时间
     */
    public static final String UNIONPAY_GMT_REFUND_PAY = "gmt_refund_pay";

    /**
     * 发起退款时，传入的退款原因
     */
    public static final String UNIONPAY_REFUND_REASON = "refund_reason";

    /**
     * 交易备注
     */
    public static final String NOTE = "note";
    /**
     * 请求方保留域
     */
    public static final String REQ_RESERVED = "reqReserved";

    /**
     * 本次退款请求，对应的退款金额
     */
    public static final String UNIONPAY_REFUND_AMOUNT = "refund_amount";

    /**
     * 本笔退款对应的退款请求号
     */
    public static final String UNIONPAY_OUT_REQUEST_NO = "out_request_no";

    /**
     * 原交易流水号
     */
    public static final String O_TRADE_NUM = "oTradeNum";

    /**
     * 是否需要重试
     */
    public static final String UNIONPAY_RETRY_FLAG = "retry_flag";

    /**
     * 本次撤销触发的交易动作
     * close：关闭交易，无退款
     * refund：产生了退款
     */
    public static final String UNIONPAY_ACTION = "action";

    /**
     * 银联二维码
     */
    public static final String QR_CODE = "qrCode";

    /**
     * 详情描述
     */
    public static final String DESC = "desc";

    /**
     * 交易类型
     */
    public static final String TRADE_TYPE = "tradeType";

    /**
     * 账单下载地址链接，获取连接后30秒后未下载，链接地址失效。
     */
    public static final String UNIONPAY_DOWNLOAD_URL = "bill_download_url";

    /**
     * 交易状态
     */
    public static final String UNIONPAY_TRADE_STATUS = "trade_status";


    /**
     * 交易传输时间
     */
    public static final String UNIONPAY_TRACE_TIME = "traceTime";


    /**
     * 交易状态
     */
    public static final String STATUS = "status";

    /**
     * 返回码
     */
    public static final String RESP_CODE = "respCode";

    /**
     * 返回信息
     */
    public static final String RESP_MSG = "respMsg";

    /**
     * 返回信息
     */
    public static final String SUB_MSG = "sub_msg";


    /**
     * 返回码
     */
    public static final String SUB_CODE = "sub_code";

}

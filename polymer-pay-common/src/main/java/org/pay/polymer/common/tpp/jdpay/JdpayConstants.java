package org.pay.polymer.common.tpp.jdpay;

/**
 * @program: polymer-pay-api
 * @description: 京东常量
 * @author: Mr.Yang
 * @create: 2018-10-28 12:20
 **/
public class JdpayConstants {
    /**
     * form表单字段
     */
    public static final String FORM = "form";

    /**
     * 1
     */
    public static final int NUMBER_ONE = 1;

    /**
     * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    public static final String BIZ_CONTENT_KEY = "bizContent";

    /**
     * 结果字段
     */
    public static final String RESULT = "result";

    /**
     * 详情描述
     */
    public static final String DESC = "desc";

    /**
     * 商户号
     **/
    public static final String MERCHANT = "merchant";
    /**
     * 查询
     **/
    public static final String FIELD_QUERY = "query";
    /**
     * 退款
     **/
    public static final String FIELD_REFUND = "refund";
    /**
     * 类型
     **/
    public static final String TYPE = "type";

    /**
     * 对账单日期 20140603
     **/
    public static final String BILL_DATE = "billDate";

    /**
     * 账单类型
     **/
    public static final String BILL_TYPE = "billType";

    /**
     * 异步通知页面地址
     **/
    public static final String NOTIFY_URL = "notifyUrl";
    /**
     * 版本号字段
     **/
    public static final String VERSION = "version";
    /**
     * 版本号
     **/
    public static final String VERSION_NO = "V2.0";
    /**
     * 支付成功跳转路径
     **/
    public static final String CALLBACK_URL = "callbackUrl";

    /**
     * 京东sdk
     */
    public static final String JD_PAY_SDK = "jdpay_sdk";

    /**
     * 京东
     */
    public static final String JD_PAY = "Jdpay";

    /**
     * 京东支付类型 PC网页
     */
    public static final String JD_TYPE_PAGE = "Page";

    /**
     * 京东支付类型 在线支付
     */
    public static final String JD_TYPE_ONLINE = "Online";
    /**
     * 交易信息签名
     **/
    public static final String SIGN = "sign";

    /**
     * 数量类型
     **/
    public static final String METHOD = "method";
    /**
     * 交易时间
     **/
    public static final String TRADETIME = "tradeTime";

    /**
     * 京东支付类型 统一支付
     */
    public static final String JD_TYPE_UNIFIED = "Unified";

    /**
     * 京东支付类型 公共接口（查询、退款、对账单）
     */
    public static final String JD_TYPE_PUBLIC = "Public";

    /**
     * 货币类型，固定填CNY
     **/
    public static final String CURRENCY = "currency";

    /**
     * 京东公共接口服务（查询、退款、账单等）
     **/
    public static final String JDPAY_COMMON = "jdpay-common";

    /**
     * 京东在线支付
     **/
    public static final String JDPAY_ONLINE = "jdpay-online";

    /**
     * 京东二维码
     **/
    public static final String JDPAY_QRCODE = "jdpay-qrcode";

    /**
     * 京东付款码
     **/
    public static final String JDPAY_STRIPE= "jdpay-stripe";

    /**
     * 京东统一支付
     **/
    public static final String JDPAY_UNIFIED= "jdpay-unified";

    /**
     * 默认时间格式
     **/
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";


    /**
     * 默认时间格式
     **/
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * SDK版本号
     */
    public static final String SDK_VERSION = "pay-gate-signature-1.0.10";

    /**
     * Date默认时区
     **/
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * UTF-8字符集
     **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * GBK字符集
     **/
    public static final String CHARSET_GBK = "GBK";

    /**
     * JSON 应格式
     */
    public static final String FORMAT_JSON = "json";

    /**
     * XML 应格式
     */
    public static final String FORMAT_XML = "xml";

    /**
     * http协调格式
     */
    public static final String CONTENT_TYPE = "application/xml";

    /**
     * 京东支付类型 手机H5
     */
    public static final String JD_TYPE_WAP = "Wap";

    /**
     * 京东支付类型 手机App
     */
    public static final String JD_TYPE_APP = "App";

    /**
     * 京东支付类型 读取用户手机京东付款码取设备
     */
    public static final String JD_TYPE_STRIPE = "Stripe";

    /**
     * 京东支付类型 商户二维码支付接口
     */
    public static final String JD_TYPE_QR_CODE = "QRCode";

    /**
     * 京东二维码
     */
    public static final String QR_CODE = "qrCode";

    /**
     * 支付交易操作类型(支付)
     */
    public static final String JD_MODULE_PAY = "Pay";

    /**
     * 支付交易操作类型(支付查询)
     */
    public static final String JD_MODULE_PAY_QUERY = "PayQuery";

    /**
     * 支付交易操作类型(退款)
     */
    public static final String JD_MODULE_REFUND = "Refund";


    /**
     * 支付交易操作类型(退款查询)
     */
    public static final String JD_MODULE_REFUND_QUERY = "RefundQuery";

    /**
     * 支付交易操作类型(交易关闭)
     */
    public static final String JD_MODULE_CLOSE = "Close";

    /**
     * 支付交易操作类型(交易取消)
     */
    public static final String JD_MODULE_CANCEL = "Cancel";


    /**
     * 支付交易操作类型(交易同步)
     */
    public static final String JD_MODULE_SYNC = "Sync";


    /**
     * 支付交易操作类型(下载账单)
     */
    public static final String JD_MODULE_DOWNLOAD = "Download";

    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    public static final String CHARSET = "charset";

    /**
     * 默认utf-8
     */
    public static final String DEFAULT_CHARSET = "UTF-8";


    /**
     * H5版调用地址
     */
    public static final String H_SAVE_ORDER = "https://h5pay.jd.com/jdpay/saveOrder";

    /**
     * PC版调用地址
     */
    public static final String PC_SAVE_ORDER = "https://wepay.jd.com/jdpay/saveOrder";

    /**
     * 统一下单接口
     */
    public static final String UNIORDER = "https://paygate.jd.com/service/uniorder";

    /**
     * 商户二维码支付接口
     */
    public static final String CUSTOMER_PAY = "https://h5pay.jd.com/jdpay/customerPay";

    /**
     * 付款码支付接口
     */
    public static final String FKM_PAY = "https://paygate.jd.com/service/fkmPay";

    /**
     * 交易查询接口
     */
    public static final String QUERY = "https://paygate.jd.com/service/query";

    /**
     * 退款申请接口
     */
    public static final String REFUND = "https://paygate.jd.com/service/refund";


    /**
     * 对账单接口
     */
    public static final String DOWNLOAD = "https://bapi.jdpay.com/api/download.do";

    /**
     * 撤销申请接口
     */
    public static final String REVOKE = "https://paygate.jd.com/service/revoke";

    /**
     * 京东商户私钥
     */
    public static final String PRIVATE_KEY = "privateKey";

    /**
     * 京东公钥
     */
    public static final String JDPAY_PUBLIC_KEY = "jdpayPublicKey";

    /**
     * des密秘钥
     */
    public static final String DES_KEY = "desKey";

    /**
     * 网关返回码
     */
    public static final String CODE = "code";

    /**
     * 网关返回码描述
     */
    public static final String MSG = "msg";
    /**
     * 交易金额
     */
    public static final String AMOUNT = "amount";
    /**
     * 订单类型 固定值：0或者1 （0：实物，1：虚拟）
     */
    public static final String FIELD_ORDER_TYPE = "orderType";
    /**
     * 货币类型。固定值：CNY
     */
    public static final String FIELD_CURRENCY_TYPE = "currency";
    /**
     * 交易类型。GEN-普通支付 ；QR-扫码支付。为空时默认GEN。（扫一扫支付使用QR，SDK支付使用GEN）
     */
    public static final String FIELD_TRADE_TYPE = "tradeType";
    /**
     * 订单类型 固定值：0或者1 （0：实物，1：虚拟）
     */
    public static final String ORDER_TYPE = "1";
    /**
     * 货币类型。固定值：CNY
     */
    public static final String CURRENCY_TYPE = "CNY";
    /**
     * 交易类型。GEN-普通支付 ；QR-扫码支付。为空时默认GEN。（扫一扫支付使用QR，SDK支付使用GEN）
     */
    public static final String TRADE_TYPE_QR = "QR";
    /**
     * CUST-无金额支付
     */
    public static final String TRADE_TYPE_CUST = "CUST";

    /**
     * DRCT-有金额支付
     */
    public static final String TRADE_TYPE_DRCT = "DRCT";

    /**
     * 对账单密钥 md5
     */
    public static final String MD5_KEY = "mdkey";
    /**
     * 下载地址
     */
    public static final String BILL_DOWNLOAD_URL = "billDownloadUrl";

    /**
     * 交易号
     */
    public static final String JD_TRADE_NO = "trade_no";

    /**
     * 交易号
     */
    public static final String ORDER_ID = "orderId";

    /**
     * 门店号
     */
    public static final String JD_DEVICE = "device";

    /**
     * 商户名称
     */
    public static final String JD_MERCHANTNAME = "merchantName";

    /**
     * 商户门店编号
     */
    public static final String JD_STORE_ID = "store_id";

    /**
     * 商户门店编号
     */
    public static final String DEVICE = "device";

    /**
     * 商家订单号
     */
    public static final String JD_OUT_TRADE_NO = "out_trade_no";

    /**
     * 交易流水号
     */
    public static final String TRADE_NUM = "tradeNum";


    /**
     * 当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码
     */
    public static final String JD_QR_CODE = "qr_code";
    /**
     * 交易订单金额
     */
    public static final String JD_TOTAL_AMOUNT = "total_amount";

    /**
     * 本次交易打款给卖家的时间
     */
    public static final String JD_SEND_PAY_DATE = "send_pay_date";

    /**
     * 交易支付时间
     */
    public static final String JD_GMT_PAYMENT = "gmt_payment";

    /**
     * 交易支付时间
     */
    public static final String TRADE_TIME = "tradeTime";

    /**
     * 本次退款是否发生了资金变化
     */
    public static final String JD_FUND_CHANGE = "fund_change";

    /**
     * 退款总金额
     */
    public static final String JD_REFUND_FEE = "refund_fee";

    /**
     * 退款支付时间
     */
    public static final String JD_GMT_REFUND_PAY = "gmt_refund_pay";

    /**
     * 发起退款时，传入的退款原因
     */
    public static final String JD_REFUND_REASON = "refund_reason";

    /**
     * 交易备注
     */
    public static final String NOTE = "note";

    /**
     * 本次退款请求，对应的退款金额
     */
    public static final String JD_REFUND_AMOUNT = "refund_amount";

    /**
     * 本笔退款对应的退款请求号
     */
    public static final String JD_OUT_REQUEST_NO = "out_request_no";

    /**
     * 原交易流水号
     */
    public static final String O_TRADE_NUM = "oTradeNum";

    /**
     * 是否需要重试
     */
    public static final String JD_RETRY_FLAG = "retry_flag";

    /**
     * 本次撤销触发的交易动作
     * close：关闭交易，无退款
     * refund：产生了退款
     */
    public static final String JD_ACTION = "action";

    /**
     * 交易类型
     */
    public static final String TRADE_TYPE = "tradeType";

    /**
     * 账单下载地址链接，获取连接后30秒后未下载，链接地址失效。
     */
    public static final String JD_DOWNLOAD_URL = "bill_download_url";

    /**
     * 交易状态
     */
    public static final String JD_TRADE_STATUS = "trade_status";

    /**
     * 交易状态
     */
    public static final String STATUS = "status";

    /**
     * 返回信息
     */
    public static final String SUB_MSG = "sub_msg";


    /**
     * 返回码
     */
    public static final String SUB_CODE = "sub_code";

}

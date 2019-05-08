package org.pay.polymer.common.tpp.alipay;

/**
 * @program: polymer-pay-api
 * @description: 支付宝常量
 * @author: Mr.Yang
 * @create: 2018-10-28 12:20
 **/
public interface AlipayConstants {

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    String SIGN_TYPE = "signType";

    /**
     * 签名算法类型 RSA2
     */
    String DEFAULT_SIGN_TYPE = "RSA2";


    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    String CHARSET = "charset";

    /**
     * 默认utf-8
     */
    String DEFAULT_CHARSET = "UTF-8";


    /**
     * 仅支持JSON
     */
    String FORMAT = "format";

    /**
     * 默认json
     */
    String DEFAULT_FORMAT = "json";

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    String APP_ID = "appId";


    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    String PRIVATE_KEY = "privateKey";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    String ALIPAY_PUBLIC_KEY = "alipayPublicKey";

    /**
     * 支付宝网关
     */
    String GATEWAY_URL = "gatewayUrl";


    /**
     * 默认支付宝网关
     */
    String DEFAULT_GATEWAY = "https://openapi.alipay.com/gateway.do";

    /**
     * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    String BIZ_CONTENT_KEY = "bizContent";

    String PRODUCT_CODE = "product_code";

    /**
     * 商品编码 pc
     */
    String FAST_INSTANT_TRADE_PAY="FAST_INSTANT_TRADE_PAY";

    /**
     * 商品编码 pc
     */
    String QUICK_WAP_WAY="QUICK_WAP_WAY";

    /**
     * 返回码
     */
    String CODE = "code";

    /**
     * 返回信息
     */
    String SUB_MSG = "sub_msg";


    /**
     * 返回码
     */
    String SUB_CODE = "sub_code";

    /**
     * 返回信息
     */
    String MSG = "msg";

    /**
     * form表单字段
     */
    String FORM = "form";

    /**
     * app响应
     */
    String ALIPAY_TRADE_APP_PAY_RESPONSE = "alipay_trade_app_pay_response";

    /**
     * pc网页响应值
     */
    String ALIPAY_TRADE_PAGE_PAY_RESPONSE = "alipay_trade_page_pay_response";

    /**
     * 申请二维码
     */
    String ALIPAY_TRADE_PRECREATE_RESPONSE = "alipay_trade_precreate_response";
    /**
     * 支付查询
     */
    String ALIPAY_TRADE_QUERY_RESPONSE = "alipay_trade_query_response";
    /**
     * 退款
     */
    String ALIPAY_TRADE_REFUND_RESPONSE = "alipay_trade_refund_response";
    /**
     * 手机支付
     */
    String ALIPAY_TRADE_WAP_PAY_RESPONSE = "alipay_trade_wap_pay_response";
    /**
     * 退款查询
     */
    String ALIPAY_TRADE_FASTPAY_REFUND_QUERY_RESPONSE = "alipay_trade_fastpay_refund_query_response";

    /**
     * 订单关闭
     */
    String ALIPAY_TRADE_CLOSE_RESPONSE = "alipay_trade_close_response";
    /**
     * 订单关撤销
     */
    String ALIPAY_TRADE_CANCEL_RESPONSE = "alipay_trade_cancel_response";
    /**
     * 订单同步
     */
    String ALIPAY_TRADE_ORDERINFO_SYNC_RESPONSE = "alipay_trade_orderinfo_sync_response";
    /**
     * 下载对账单
     */
    String ALIPAY_DATA_DATASERVICE_BILL_DOWNLOADURL_QUERY_RESPONSE = "alipay_data_dataservice_bill_downloadurl_query_response";

    /**
     * 支付响应
     */
    String ALIPAY_TRADE_PAY_RESPONSE = "alipay_trade_pay_response";

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
     */
    String NOTIFY_URL = "notify_url";

    /**
     * 同步返回地址，HTTP/HTTPS开头字符串
     */
    String RETURN_URL = "return_url";

    /**
     * 支付宝
     */
    String ALI_PAY = "Alipay";

    /**
     * 支付宝sdk
     */
    String ALI_PAY_SDK = "alipay_sdk";

    /**
     * 支付宝支付类型 PC网页
     */
    String ALI_TYPE_PAGE = "Page";

    /**
     * 支付宝支付类型 统一收单线下交易预创建
     */
    String ALI_TYPE_PRE_CREATE = "Precreate";

    /**
     * 支付宝app
     */
    String ALI_APP = "alipay-app";

    /**
     * 支付宝pc
     */
    String ALI_PAGE = "alipay-page";

    /**
     * 支付宝wap
     */
    String ALI_WAP= "alipay-wap";

    /**
     * 支付宝统一收单线下交易预创建
     */
    String ALI_PRECREATE = "alipay-precreate";


    /**
     * 支付宝公共接口服务（查询、退款、账单等）
     */
    String ALI_COMMON= "alipay-common";

    /**
     * 支付宝付款码
     */
    String ALI_STRIPE= "alipay-stripe";

    /**
     * 支付宝支付类型 公共接口（查询、退款、对账单）
     */
    String ALI_TYPE_PUBLIC = "Public";

    /**
     * 支付宝支付类型 手机H5
     */
    String ALI_TYPE_WAP = "Wap";

    /**
     * 支付宝支付类型 手机App
     */
    String ALI_TYPE_APP = "App";

    /**
     * 支付宝支付类型 读取用户手机支付宝“付款码”/声波获取设备
     */
    String ALI_TYPE_STRIPE = "Stripe";


    /**
     * 支付交易操作类型(支付)
     */
    String ALI_MODULE_PAY = "Pay";

    /**
     * 支付交易操作类型(支付查询)
     */
    String ALI_MODULE_PAY_QUERY = "PayQuery";

    /**
     * 支付交易操作类型(退款)
     */
    String ALI_MODULE_REFUND = "Refund";


    /**
     * 支付交易操作类型(退款查询)
     */
    String ALI_MODULE_REFUND_QUERY = "RefundQuery";

    /**
     * 支付交易操作类型(交易关闭)
     */
    String ALI_MODULE_CLOSE = "Close";

    /**
     * 支付交易操作类型(交易取消)
     */
    String ALI_MODULE_CANCEL = "Cancel";


    /**
     * 支付交易操作类型(交易同步)
     */
    String ALI_MODULE_SYNC = "Sync";


    /**
     * 支付交易操作类型(下载账单)
     */
    String ALI_MODULE_DOWNLOAD = "Download";

    /**
     * 交易状态
     */
    String ALIPAY_TRADE_STATUS = "trade_status";

    /**
     * 交易支付成功
     */
    String ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS";

    /**
     * 成功
     */
    String ALIPAY_SUCCESS="Success";

    /**
     * 未付款交易超时关闭，或支付完成后全额退款
     */
    String ALIPAY_TRADE_CLOSED = "TRADE_CLOSED";


    /**
     * 交易创建，等待买家付款
     */
    String ALIPAY_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";

    /**
     * 交易结束，不可退款
     */
    String ALIPAY_TRADE_FINISHED = "TRADE_FINISHED";

    /**
     * 支付宝交易号
     */
    String ALIPAY_TRADE_NO = "trade_no";

    /**
     * 商家订单号
     */
    String ALIPAY_OUT_TRADE_NO = "out_trade_no";

    /**
     * 当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码
     */
    String ALIPAY_QR_CODE = "qr_code";

    /**
     * 买家支付宝账号
     */
    String ALIPAY_BUYER_LOGON_ID = "buyer_logon_id";
    /**
     * 交易订单金额
     */
    String ALIPAY_TOTAL_AMOUNT= "total_amount";
    /**
     * 本次交易打款给卖家的时间
     */
    String ALIPAY_SEND_PAY_DATE = "send_pay_date";

    /**
     * 交易支付时间
     */
    String ALIPAY_GMT_PAYMENT = "gmt_payment";

    /**
     * 本次退款是否发生了资金变化
     */
    String ALIPAY_FUND_CHANGE = "fund_change";

    /**
     * 退款总金额
     */
    String ALIPAY_REFUND_FEE = "refund_fee";

    /**
     * 退款支付时间
     */
    String ALIPAY_GMT_REFUND_PAY = "gmt_refund_pay";

    /**
     * 发起退款时，传入的退款原因
     */
    String ALIPAY_REFUND_REASON= "refund_reason";

    /**
     * 商户门店编号
     */
    String ALIPAY_STORE_ID= "store_id";

    /**
     * 本次退款请求，对应的退款金额
     */
    String ALIPAY_REFUND_AMOUNT= "refund_amount";

    /**
     * 本笔退款对应的退款请求号
     */
    String ALIPAY_OUT_REQUEST_NO= "out_request_no";

    /**
     * 是否需要重试
     */
    String ALIPAY_RETRY_FLAG= "retry_flag";

    /**
     * 本次撤销触发的交易动作
     * close：关闭交易，无退款
     * refund：产生了退款
     */
    String ALIPAY_ACTION= "action";

    /**
     * 账单下载地址链接，获取连接后30秒后未下载，链接地址失效。
     */
    String ALIPAY_DOWNLOAD_URL= "bill_download_url";
}

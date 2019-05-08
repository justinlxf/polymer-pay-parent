package org.pay.polymer.consumer.config;

/**
 * <p>
 * 字段属性常量
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public interface FieldConstant {

    /**
     * 微信
     */
    String WECHAT = "wechat";

    /**
     * 银联
     */
    String UNION_PAY = "unionpay";

    /**
     * 支付宝
     */
    String ALI_PAY = "alipay";

    /**
     * 京东
     */
    String JDPAY = "jdpay";

    /**
     * 银联线上支付
     */
    String UNION_PAY_UNIFIED = "unionpay-unified";

    /**
     * 银联二维码
     */
    String UNION_PAY_QRCODE = "unionpay-qrcode";

    /**
     * 银联公共服务
     */
    String UNION_PAY_COMMON = "unionpay-common";

    /**
     * 银联付款码
     */
    String UNION_PAY_STRIPE = "unionpay-stripe";

    /**
     * 银联app支付
     */
    String UNION_PAY_APP = "unionpay-app";

    /**
     * 支付宝pc
     */
    String ALI_PAY_PAGE = "alipay-page";

    /**
     * 支付宝二维码
     */
    String ALI_PAY_PRECREATE = "alipay-precreate";

    /**
     * 支付宝手机
     */
    String ALI_PAY_WAP = "alipay-wap";

    /**
     * 支付宝付款码
     */
    String ALI_PAY_STRIPE = "alipay-stripe";

    /**
     * 支付宝公共方法
     */
    String ALI_PAY_COMMON = "alipay-common";

    /**
     * 京东在线支付
     */
    String JD_PAY_ONLINE = "jdpay-online";

    /**
     * 京东二维码
     */
    String JD_PAY_QRCODE = "jdpay-qrcode";

    /**
     * 京东付款码
     */
    String JD_PAY_STRIPE = "jdpay-stripe";

    /**
     * 京东统一下单
     */
    String JD_PAY_UNIFIED = "jdpay-unified";

    /**
     * 京东公共方法
     */
    String JD_PAY_COMMON = "jdpay-common";

    /**
     * 微信app支付
     */
    String WECHAT_PAY_APP = "wechatpay-app";

    /**
     * 微信公众号支付
     */
    String WECHAT_PAY_JSAPI = "wechatpay-jsapi";

    /**
     * 微信付款码支付
     */
    String WECHAT_PAY_MICROPAY = "wechatpay-micropay";

    /**
     * 微信商户二维码支付
     */
    String WECHAT_PAY_NATIVE = "wechatpay-native";

    /**
     * 微信wap支付
     */
    String WECHAT_PAY_MWEB = "wechatpay-mweb";

    /**
     * 微信公众服务
     */
    String WECHAT_PAY_COMMON = "wechatpay-common";

    /**
     * 商户订单号
     */
    String FIELD_OUT_TRADE_NO = "out_trade_no";

    /**
     * 原交易流水号
     */
    String FIELD_O_TRADE_NUM = "oTradeNum";

    /**
     * 订单金额
     */
    String FIELD_TOTAL_AMOUNT = "total_amount";

    /**
     * 订单标题
     */
    String FIELD_SUBJECT = "subject";

    /**
     * 订单描述
     */
    String FIELD_BODY = "body";

    /**
     * 门店号
     */
    String FIELD_DEVICE = "device";

    /**
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数
     */
    String FIELD_PASSBACK_PARAMS = "passback_params";


    /**
     * 本笔退款对应的退款请求号
     */
    String FIELD_OUT_REQUEST_NO = "out_request_no";

    /**
     * 付款码
     */
    String FIELD_TOKEN = "token";

    /**
     * 后台回调地址
     */
    String FIELD_NOTIFY_URL = "notify_url";

    /**
     * 商户订单号
     */
    String FIELD_ORDER_ID = "orderId";

    /**
     * 交易金额
     */
    String FIELD_TXN_AMT = "txnAmt";

    /**
     * 前台通知地址
     */
    String FIELD_FRONT_URL = "frontUrl";

    /**
     * 后台通知地址
     */
    String FIELD_BACK_URL = "backUrl";

    /**
     * 支付宝前缀
     */
    String ALIPAY_STARTS_WITH = "alipay_trade";

    /**
     * 订单描述
     */
    String FIELD_ORDER_DESC = "orderDesc";

    /**
     * 请求方保留域
     */
    String FIELD_REQ_RESERVED = "reqReserved";

    /**
     * 原交易查询流水号
     */
    String FIELD_ORIGQRY_ID = "origQryId";
    /**
     * C2B码
     */
    String FIELD_QR_NO = "qrNo";
    /**
     * 终端号
     */
    String FIELD_TERM_ID = "termId";
    /**
     * 清算日期
     */
    String FIELD_SETTLE_DATE = "settleDate";
    /**
     * 同步页面
     */
    String FIELD_RETURN_URL = "return_url";

    /**
     * 商户号
     */
    String FIELD_APPID = "app_id";

    /**
     * 页面html
     */
    String FIELD_FORM = "form";

    /**
     * 交易类型 0：消费；1：退款
     */
    String FIELD_TRADE_TYPE = "tradeType";

    /**
     * pc端支付
     */
    String DO_PAGE_PAY = "doPagePay";

    /**
     * wap端支付
     */
    String DO_WAP_PAY = "doWapPay";

    /**
     * 退款查询
     */
    String DO_REFUND_QUERY_PAY = "doRefundQuery";

    /**
     * 退款
     */
    String DO_REFUND_PAY = "doRefund";

    /**
     * 支付查询
     */
    String DO_QUERY_PAY = "doQueryPay";

    /**
     * 下载订单
     */
    String DO_WNLOADBILL = "downloadbill";

    /**
     * 关闭
     */
    String DO_CLOSE = "doClose";

    /**
     * 取消
     */
    String DO_CANCEL = "doCancel";

    /**
     * 二维码支付
     */
    String DO_QR_CODE_PAY = "doQrcodePay";

    /**
     * 读取用户手机支付宝“付款码”/声波获取设备
     */
    String DO_STRIPE_PAY = "doStripePay";

    /**
     * 二维码支付
     */
    String DO_PRECREATE_PAY = "doPrecreatePay";

    /**
     * 公众号支付
     */
    String DO_JSAPI_PAY = "doJsapiPay";

    /**
     * app支付
     */
    String DO_APP_PAY = "doAppPay";


    /**
     * wap端支付
     */
    String FIELD_WAP = "Wap";

}

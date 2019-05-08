package org.pay.polymer.common.tpp.wechat;

/**
 * @program: polymer-pay-api
 * @description: 微信支付常量
 * @author: Mr.Yang
 * @create: 2018-10-28 14:46
 **/
public class WechatConstants {

    /**
     * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
     */
    public static final String BIZ_CONTENT_KEY = "bizContent";

    /**
     * 微信支付
     */
    public static final String WECHAT_PAY = "Wechatpay";

    /**
     * 微信外部浏览器H5支付
     */
    public static final String WECHATPAY_MWEB = "wechatpay-mweb";

    /**
     * 微信公共接口服务（查询、退款、账单等）
     */
    public static final String WECHATPAY_COMMON = "wechatpay-common";

    /**
     * 用户扫商家二维码
     */
    public static final String WECHATPAY_NATIVE = "wechatpay-native";

    /**
     * 微信付款码支付
     */
    public static final String WECHATPAY_MICROPAY = "wechatpay-micropay";

    /**
     * 微信公众号号支付
     */
    public static final String WECHATPAY_JSAPI = "wechatpay-jsapi";

    /**
     * 微信app支付
     */
    public static final String WECHATPAY_APP = "wechatpay-app";

    /**
     * 微信支付类型 浏览器H5
     */
    public static final String WECHAT_TYPE_MWEB = "Mweb";

    /**
     * H5支付
     */
    public static final String TYPE_MWEB = "MWEB";

    /**
     * 用户扫码
     */
    public static final String TYPE_NATIVE = "NATIVE";


    /**
     * App支付
     */
    public static final String TYPE_APP = "APP";


    /**
     * 公众号支付
     */
    public static final String TYPE_JSAPI = "JSAPI";


    /**
     * 微信支付类型 App
     */
    public static final String WECHAT_TYPE_APP = "App";


    /**
     * 公众账号ID
     */
    public static final String WECHAT_APPID = "appid";

    /**
     * 微信key
     */
    public static final String WECHAT_KEY = "key";

    /**
     * 微信支付类型 用户扫二维码
     */
    public static final String WECHAT_TYPE_NATIVE = "Native";


    /**
     * 微信支付类型 公众号支付
     */
    public static final String WECHAT_TYPE_JSAPI = "Jsapi";


    /**
     * 微信支付类型 付款码支付
     */
    public static final String WECHAT_TYPE_MICROPAY = "Micropay";

    /**
     * 支付交易操作类型(支付)
     */
    public static final String WECHAT_MODULE_PAY = "Pay";

    /**
     * 支付交易操作类型(查询)
     */
    public static final String WECHAT_MODULE_PAY_QUERY = "PayQuery";

    /**
     * 支付交易操作类型(退款)
     */
    public static final String WECHAT_MODULE_REFUND = "Refund";

    /**
     * 支付交易操作类型(退款查询)
     */
    public static final String WECHAT_MODULE_REFUND_QUERY = "RefundQuery";

    /**
     * 支付交易操作类型(关闭订单)
     */
    public static final String WECHAT_MODULE_REFUND_CLOSE = "Close";

    /**
     * 支付交易操作类型(撤销)
     */
    public static final String WECHAT_MODULE_CANCEL = "Cancel";

    /**
     * 支付交易操作类型(下载对账单)
     */
    public static final String WECHAT_MODULE_DOWNLOAD = "Download";


    /**
     * 异步回调
     */
    public static final String NOTIFY_URL = "notify_url";

    /**
     * 微信支付sdk
     */
    public static final String WECHAT_PAY_SDK = "wechatpay_sdk";

    /**
     * 公共服务
     */
    public static final String WECHAT_TYPE_PUBLIC = "Public";
    /**
     * 交易类型
     */
    public static final String TRADE_TYPE = "trade_type";

    /**
     * 枚举签名类型
     *
     * @author Mr.Yang
     * @date 2018/11/17
     */
    public enum SignType {
        MD5, HMACSHA256
    }

    /**
     * 域名
     */
    public static final String DOMAIN_API = "api.mch.weixin.qq.com";

    /**
     * 网关返回码
     */
    public static final String CODE = "code";

    /**
     * 网关返回码描述
     */
    public static final String MSG = "msg";

    /**
     * form表单字段
     */
    public static final String FORM = "form";

    /**
     * 返回信息
     */
    public static final String RETURN_MSG = "return_msg";

    /**
     * 随机字符串
     */
    public static final String TOTAL_FEE = "total_fee";

    /**
     * 商户号
     */
    public static final String MCH_ID = "mch_id";

    /**
     * 支付金额
     */
    public static final String NONCE_STR = "nonce_str";

    /**
     * 返回失败
     */
    public static final String FAIL = "FAIL";
    /**
     * 返回成功
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 签名类型 SHA256
     */
    public static final String HMACSHA256 = "HMAC-SHA256";
    /**
     * 签名类型 MD5
     */
    public static final String MD5 = "MD5";
    /**
     * 签名字段
     */
    public static final String FIELD_SIGN = "sign";
    /**
     * 签名类型字段
     */
    public static final String FIELD_SIGN_TYPE = "sign_type";

    /**
     * 微信支付头
     */
    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";

    /**
     * 提交付款码支付
     */
    public static final String MICROPAY_URL_SUFFIX = DOMAIN_API + "/pay/micropay";
    /**
     * 统一下单
     */
    public static final String UNIFIEDORDER_URL_SUFFIX = DOMAIN_API + "/pay/unifiedorder";
    /**
     * 订单查询
     */
    public static final String ORDERQUERY_URL_SUFFIX = DOMAIN_API + "/pay/orderquery";
    /**
     * 订单撤销
     */
    public static final String REVERSE_URL_SUFFIX = DOMAIN_API + "/secapi/pay/reverse";
    /**
     * 订单关闭
     */
    public static final String CLOSEORDER_URL_SUFFIX = DOMAIN_API + "/pay/closeorder";
    /**
     * 申请退款
     */
    public static final String REFUND_URL_SUFFIX = DOMAIN_API + "/secapi/pay/refund";
    /**
     * 退款查询
     */
    public static final String REFUNDQUERY_URL_SUFFIX = DOMAIN_API + "/pay/refundquery";
    /**
     * 下载对账单
     */
    public static final String DOWNLOADBILL_URL_SUFFIX = DOMAIN_API + "/pay/downloadbill";
    /**
     * 交易保障
     */
    public static final String REPORT_URL_SUFFIX = DOMAIN_API + "/payitil/report";
    /**
     * 转换短链
     */
    public static final String SHORTURL_URL_SUFFIX = DOMAIN_API + "/tools/shorturl";
    /**
     * 授权码查询openid
     */
    public static final String AUTHCODETOOPENID_URL_SUFFIX = DOMAIN_API + "/tools/authcodetoopenid";

    public static final String SANDBOX_UNIFIEDORDER_URL_SUFFIX = "/sandboxnew/pay/unifiedorder";
    public static final String SANDBOX_ORDERQUERY_URL_SUFFIX = "/sandboxnew/pay/orderquery";
    public static final String SANDBOX_REVERSE_URL_SUFFIX = "/sandboxnew/secapi/pay/reverse";
    public static final String SANDBOX_CLOSEORDER_URL_SUFFIX = "/sandboxnew/pay/closeorder";
    public static final String SANDBOX_REFUND_URL_SUFFIX = "/sandboxnew/secapi/pay/refund";
    public static final String SANDBOX_REFUNDQUERY_URL_SUFFIX = "/sandboxnew/pay/refundquery";
    public static final String SANDBOX_DOWNLOADBILL_URL_SUFFIX = "/sandboxnew/pay/downloadbill";
    public static final String SANDBOX_REPORT_URL_SUFFIX = "/sandboxnew/payitil/report";
    public static final String SANDBOX_SHORTURL_URL_SUFFIX = "/sandboxnew/tools/shorturl";
    public static final String SANDBOX_AUTHCODETOOPENID_URL_SUFFIX = "/sandboxnew/tools/authcodetoopenid";
}

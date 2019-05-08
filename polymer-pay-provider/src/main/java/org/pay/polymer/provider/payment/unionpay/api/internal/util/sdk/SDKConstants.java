package org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk;


/**
 * @program: polymer-pay-provider
 * @description: 常量类
 * @author: Mr.Yang
 * @create: 2018-10-28 18:46
 **/
public class SDKConstants {

    public final static String COLUMN_DEFAULT = "-";

    public final static String KEY_DELIMITER = "#";

    public static final String WINDOWS = "windows";

    /**
     * memeber variable: blank.
     */
    public static final String BLANK = "";

    /**
     * member variabel: space.
     */
    public static final String SPACE = " ";

    /**
     * memeber variable: unline.
     */
    public static final String UNLINE = "_";

    /**
     * memeber varibale: star.
     */
    public static final String STAR = "*";

    /**
     * memeber variable: line.
     */
    public static final String LINE = "-";

    /**
     * memeber variable: add.
     */
    public static final String ADD = "+";

    /**
     * memeber variable: colon.
     */
    public final static String COLON = "|";

    /**
     * memeber variable: point.
     */
    public final static String POINT = ".";

    /**
     * memeber variable: comma.
     */
    public final static String COMMA = ",";

    /**
     * memeber variable: slash.
     */
    public final static String SLASH = "/";

    /**
     * memeber variable: div.
     */
    public final static String DIV = "/";

    /**
     * memeber variable: left .
     */
    public final static String LB = "(";

    /**
     * memeber variable: right.
     */
    public final static String RB = ")";

    /**
     * memeber variable: rmb.
     */
    public final static String CUR_RMB = "RMB";

    /**
     * memeber variable: .page size
     */
    public static final int PAGE_SIZE = 10;

    /**
     * memeber variable: String ONE.
     */
    public static final String ONE = "1";

    /**
     * memeber variable: String ZERO.
     */
    public static final String ZERO = "0";

    /**
     * memeber variable: number six.
     */
    public static final int NUM_SIX = 6;

    /**
     * memeber variable: equal mark.
     */
    public static final String EQUAL = "=";

    /**
     * memeber variable: operation ne.
     */
    public static final String NE = "!=";

    /**
     * memeber variable: operation le.
     */
    public static final String LE = "<=";

    /**
     * memeber variable: operation ge.
     */
    public static final String GE = ">=";

    /**
     * memeber variable: operation lt.
     */
    public static final String LT = "<";

    /**
     * memeber variable: operation gt.
     */
    public static final String GT = ">";

    /**
     * memeber variable: list separator.
     */
    public static final String SEP = "./";

    /**
     * memeber variable: Y.
     */
    public static final String Y = "Y";

    /**
     * memeber variable: AMPERSAND.
     */
    public static final String AMPERSAND = "&";

    /**
     * memeber variable: SQL_LIKE_TAG.
     */
    public static final String SQL_LIKE_TAG = "%";

    /**
     * memeber variable: @.
     */
    public static final String MAIL = "@";

    /**
     * memeber variable: number zero.
     */
    public static final int NZERO = 0;

    public static final String LEFT_BRACE = "{";

    public static final String RIGHT_BRACE = "}";

    /**
     * memeber variable: string true.
     */
    public static final String TRUE_STRING = "true";
    /**
     * memeber variable: string false.
     */
    public static final String FALSE_STRING = "false";

    /**
     * memeber variable: forward success.
     */
    public static final String SUCCESS = "success";
    /**
     * memeber variable: forward fail.
     */
    public static final String FAIL = "fail";
    /**
     * memeber variable: global forward success.
     */
    public static final String GLOBAL_SUCCESS = "$success";
    /**
     * memeber variable: global forward fail.
     */
    public static final String GLOBAL_FAIL = "$fail";

    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String GBK_ENCODING = "GBK";
    public static final String CONTENT_TYPE = "Content-type";
    public static final String APP_XML_TYPE = "application/xml;charset=utf-8";
    public static final String APP_FORM_TYPE = "application/x-www-form-urlencoded;charset=";

    public static final String VERSION_1_0_0 = "1.0.0";
    public static final String VERSION_5_0_0 = "5.0.0";
    public static final String VERSION_5_0_1 = "5.0.1";
    public static final String VERSION_5_1_0 = "5.1.0";
    public static final String SIGNMETHOD_RSA = "01";
    public static final String SIGNMETHOD_SHA256 = "11";
    public static final String SIGNMETHOD_SM3 = "12";
    public static final String UNIONPAY_CNNAME = "中国银联股份有限公司";
    /**
     * 敏感信息加密公钥
     */
    public static final String CERTTYPE_01 = "01";
    /**
     * 磁道加密公钥
     */
    public static final String CERTTYPE_02 = "02";

    /******************************************** 5.0报文接口定义 ********************************************/
    /**
     * 版本号.
     */
    public static final String PARAM_VERSION = "version";
    /**
     * 证书ID.
     */
    public static final String PARAM_CERTID = "certId";
    /**
     * 签名.
     */
    public static final String PARAM_SIGNATURE = "signature";
    /**
     * 签名方法.
     */
    public static final String PARAM_SIGNMETHOD = "signMethod";
    /**
     * 编码方式.
     */
    public static final String ENCODING = "encoding";
    /**
     * 交易类型.
     */
    public static final String param_txnType = "txnType";
    /**
     * 交易子类.
     */
    public static final String param_txnSubType = "txnSubType";
    /**
     * 业务类型.
     */
    public static final String param_bizType = "bizType";
    /**
     * 前台通知地址 .
     */
    public static final String PARAM_FRONTURL = "frontUrl";
    /**
     * 后台通知地址.
     */
    public static final String PARAM_BACKURL = "backUrl";
    /**
     * 商户代码.
     */
    public static final String PARAM_MERID = "merId";
    /**
     * 商户订单号.
     */
    public static final String PARAM_ORDERID = "orderId";
    /**
     * 商户名称.
     */
    public static final String PARAM_MERNAME = "merName";

    /**
     * 批量文件内容.
     */
    public static final String PARAM_FILE_CONTENT = "fileContent";

    /**
     * 签名公钥证书
     */
    public static final String PARAM_SIGN_PUBKEY_CERT = "signPubKeyCert";

    /**
     * 加密公钥证书
     */
    public static final String PARAM_ENCRYPT_PUBKEY_CERT = "encryptPubKeyCert";

    /**
     * 证书类型
     */
    public static final String PARAM_CERT_TYPE = "certType";

    /**
     * 前台交易请求地址:
     */
    public static final String GATEWAY_FRONT_TRANS_REQ = "https://gateway.test.95516.com/gateway/api/frontTransReq.do";

    /**
     * APP交易请求地址:
     */
    public static final String GATEWAY_APP_TRANS_REQ = "https://gateway.test.95516.com/gateway/api/appTransReq.do";

    /**
     * 后台交易请求地址(无卡交易配置该地址):
     */
    public static final String GATEWAY_BACK_TRANS_REQ = "https://gateway.test.95516.com/gateway/api/backTransReq.do";

    /**
     * 后台交易请求地址(若为有卡交易配置该地址)：
     */
    public static final String GATEWAY_CARD_TRANS_REQ = "https://gateway.test.95516.com/gateway/api/cardTransReq.do";

    /**
     * 单笔查询请求地址:
     */
    public static final String GATEWAY_QUERY_TRANS = "https://gateway.test.95516.com/gateway/api/queryTrans.do";

    /**
     * 批量交易请求地址:
     */
    public static final String GATEWAY_BATCH_TRANS = "https://gateway.test.95516.com/gateway/api/batchTrans.do";

    /**
     * 文件传输类交易地址:
     */
    public static final String FILE_DOWNLOAD = "https://filedownload.test.95516.com/";

    /**
     * 缴费产品前台交易请求地址:
     */
    public static final String JIAOFEI_FRONT_TRANS_REQ = "https://gateway.test.95516.com/jiaofei/api/frontTransReq.do";

    /**
     * 缴费产品手机APP交易请求地址:
     */
    public static final String JIAOFEI_APP_TRANS_REQ = "https://gateway.test.95516.com/jiaofei/api/appTransReq.do";

    /**
     * 缴费产品后台交易请求地址(无卡交易配置该地址):
     */
    public static final String JIAOFEI_BACK_TRANS_REQ = "https://gateway.test.95516.com/jiaofei/api/backTransReq.do";

    /**
     * 缴费产品笔查询请求地址:
     */
    public static final String JIAOFEI_QUERY_TRANS = "https://gateway.test.95516.com/jiaofei/api/queryTrans.do";


}

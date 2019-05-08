package org.pay.polymer.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.api.unionpay.UnionpayModel;
import org.pay.polymer.api.unionpay.UnionpayPayment;
import org.pay.polymer.common.util.BaseRquestAndResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.pay.polymer.common.tpp.alipay.AlipayConstants.CODE;
import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.MSG;
import static org.pay.polymer.common.unionpay.UnionpayConstants.*;
import static org.pay.polymer.common.util.DesktopEndRequest.requestMessage;
import static org.pay.polymer.common.util.DesktopEndResponse.reponseResult;
import static org.pay.polymer.common.util.PayMentConstants.*;
import static org.pay.polymer.consumer.config.FieldConstant.FIELD_TXN_AMT;
import static org.pay.polymer.consumer.config.FieldConstant.UNION_PAY;
import static org.pay.polymer.consumer.config.FieldConstant.*;
import static org.pay.polymer.consumer.config.InitConfig.payCode;


/**
 * <p>
 * 银联支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@RestController
@RequestMapping(UNION_PAY)
public class UnionpayConsumerController extends BaseRquestAndResponse {

    @Reference(group = UNION_PAY_UNIFIED)
    private UnionpayPayment pagePayMent;

    @Reference(group = UNION_PAY_QRCODE)
    private UnionpayPayment qrcodePayMent;

    @Reference(group = UNION_PAY_COMMON)
    private PayMentInterface unionpayInterface;


    @Reference(group = UNION_PAY_STRIPE)
    private UnionpayPayment stripePayMent;


    @Reference(group = UNION_PAY_APP)
    private UnionpayPayment appPayMent;

    /**
     * 下载账单
     *
     * @return 返回账单地址信息
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_WNLOADBILL)
    public void doWnloadbill() {
        //下载账单
        processPayMent(NUM_EIGHT);
    }

    /**
     * app支付
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_APP_PAY)
    public void doAppPay() {
        //app支付
        processPayMent(NUM_SEVEN);
    }

    /**
     * 撤销支付
     *
     * @return 返回撤销信息
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_CANCEL)
    public void doCancel() {
        //撤销支付
        processPayMent(NUM_SIX);
    }

    /**
     * 支付退款
     *
     * @return 返回退款信息
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_REFUND_PAY)
    public void doRefund() {
        //退款
        processPayMent(NUM_FIVE);
    }

    /**
     * 支付查询
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_QUERY_PAY)
    public void doQueryPay() {
        //支付查询
        processPayMent(NUM_FOUR);
    }

    /**
     * 商户二维码支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_STRIPE_PAY)
    public void doStripePay() {
        //商户二维码支付
        processPayMent(NUM_THREE);
    }

    /**
     * 二维码支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_QR_CODE_PAY)
    public void doQrcodePay() {
        //二维码支付
        processPayMent(NUM_TWO);
    }

    /**
     * 银联wap支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_WAP_PAY)
    public void doWapPay() {
        //wap支付
        processPayMent(NUM_ZERO);
    }

    /**
     * 银联pc支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/11 0011
     */
    @PostMapping(DO_PAGE_PAY)
    public void doPayPage() {
        //pc支付
        processPayMent(NUM_ONE);
    }


    /**
     * 处理线上支付
     *
     * @param type 1 pc与wap支付  2二维码 3付款码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    private void processPayMent(int type) {
        //获取请求参数
        JSONObject params = requestMessage(getRequest(), FIELD_APPID);
        //是否存在空值，如果存在直接返回
        if (Objects.requireNonNull(params).getBoolean(FIELD_CHECK_STATUS)) {
            reponseResult(getResponse(), params);
            return;
        }
        //初始化银联实例
        UnionpayModel unionpay = new UnionpayModel(params.getString(FIELD_APPID));
        //填充业务参数
        JSONObject content = new JSONObject();
        //订单金额
        if (params.containsKey(FIELD_TOTAL_AMOUNT)) {
            content.put(FIELD_TXN_AMT, params.getString(FIELD_TOTAL_AMOUNT));
        }
        //查询流水号
        if (params.containsKey(UNIONPAY_TRADE_NO)) {
            content.put(ORIG_ORY_ID, params.getString(UNIONPAY_TRADE_NO));
        }
        //同步页面
        if (params.containsKey(FIELD_RETURN_URL)) {
            content.put(FIELD_FRONT_URL, params.getString(FIELD_RETURN_URL));
        }
        //后台回调地址
        if (params.containsKey(FIELD_NOTIFY_URL)) {
            content.put(FIELD_BACK_URL, params.getString(FIELD_NOTIFY_URL));
        }
        //商户订单号
        if (params.containsKey(FIELD_OUT_TRADE_NO)) {
            content.put(FIELD_ORDER_ID, params.getString(FIELD_OUT_TRADE_NO));
        }
        //原交易商户订单号
        if (params.containsKey(FIELD_OUT_REQUEST_NO)) {
            content.put(ORIG_ORY_ID, params.getString(FIELD_OUT_REQUEST_NO));
        }
        //C2B码
        if (params.containsKey(FIELD_QR_NO)) {
            content.put(FIELD_QR_NO, params.getString(FIELD_QR_NO));
        }
        //终端号
        if (params.containsKey(FIELD_TERM_ID)) {
            content.put(FIELD_TERM_ID, params.getString(FIELD_TERM_ID));
        }
        //清算日期
        if (params.containsKey(FIELD_SETTLE_DATE)) {
            content.put(FIELD_SETTLE_DATE, params.getString(FIELD_SETTLE_DATE));
        }
        //订单详情
        if (params.containsKey(FIELD_BODY)) {
            content.put(FIELD_ORDER_DESC, params.getString(FIELD_BODY));
        }
        //公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数
        if (params.containsKey(FIELD_PASSBACK_PARAMS)) {
            content.put(FIELD_REQ_RESERVED, params.getString(FIELD_PASSBACK_PARAMS));
        }
        if (type == NUM_ZERO) {
            content.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_WAP);
        }
        //填充业务参数
        unionpay.setBizContent(content.toString());
        switch (type) {
            case NUM_TWO:
                //响应值
                packagePayResult(JSONObject.parseObject(qrcodePayMent.doPay(unionpay)));
                break;
            case NUM_THREE:
                //响应值
                packagePayResult(JSONObject.parseObject(stripePayMent.doPay(unionpay)));
                break;
            case NUM_FOUR:
                //响应值
                packagePayResult(JSONObject.parseObject(unionpayInterface.doPayQuery(unionpay)));
                break;
            case NUM_FIVE:
                //响应值
                packagePayResult(JSONObject.parseObject(unionpayInterface.doRefund(unionpay)));
                break;
            case NUM_SIX:
                //响应值
                packagePayResult(JSONObject.parseObject(unionpayInterface.doCancel(unionpay)));
                break;
            case NUM_SEVEN:
                //响应值
                packagePayResult(JSONObject.parseObject(appPayMent.doPay(unionpay)));
                break;
            case NUM_EIGHT:
                //响应值
                packagePayResult(JSONObject.parseObject(unionpayInterface.downloadurl(unionpay)));
                break;
            default:
                packageHtmlPayResult(JSONObject.parseObject(pagePayMent.doPay(unionpay)));
                break;
        }
    }


    /**
     * 包装银联返回结果信息
     *
     * @param parseResult 解析支付结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packagePayResult(JSONObject parseResult) {
        JSONObject result = new JSONObject();
        //获取错误信息
        JSONObject tempbody = parseResult.getJSONObject(FIELD_FORM);
        if (tempbody != null &&
                tempbody.getString(RESP_CODE).equals(NUM_TWO_ZERO)) {
            //全局响应码
            globalResponseCode(parseResult, result);
            //组装数据
            handlerUnionPayField(parseResult, result);
            reponseResult(getResponse(), result);
            return;
        }
        if (null == tempbody) {
            tempbody = parseResult.getJSONObject(FIELD_FORM);
        }
        //错误信息
        handlerUnionPayWrongResult(parseResult, tempbody, result);
        reponseResult(getResponse(), result);
    }


    /**
     * 处理错误信息
     *
     * @param form        响应信息
     * @param tempbody    result区域
     * @param parseResult 响应结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void handlerUnionPayWrongResult(JSONObject form, JSONObject tempbody, JSONObject parseResult) {
        //全局响应码
        globalResponseCode(form, parseResult);
        //业务响应码
        parseResult.put(SUB_CODE, tempbody.getIntValue(RESP_CODE));
        parseResult.put(SUB_MSG, payCode.containsKey(tempbody.getString(RESP_CODE))
                ? payCode.get(tempbody.getString(RESP_CODE)) : tempbody.getString(RESP_MSG));
    }

    /**
     * 全局响应码
     *
     * @param form           临时数据对象
     * @param responseResult 返回对象
     * @author Mr.Yang
     * @date 2019/3/14 0014
     */
    private void globalResponseCode(JSONObject form, JSONObject responseResult) {
        //清空原有信息,筛选出返回字段
        responseResult.clear();
        //网关响应码
        responseResult.put(MSG, form.getString(MSG));
        responseResult.put(CODE, form.getIntValue(CODE));
    }

    /**
     * 查询与付款码组装数据
     *
     * @param tempbody       临时数据对象
     * @param responseResult 返回对象
     * @author Mr.Yang
     * @date 2019/3/14 0014
     */
    private void handlerUnionPayField(JSONObject tempbody, JSONObject responseResult) {
        tempbody = tempbody.getJSONObject(FIELD_FORM);
        //交易状态
        responseResult.put(UNIONPAY_TRADE_STATUS, tempbody.getString(STATUS));
        //查询流水号
        responseResult.put(UNIONPAY_TRADE_NO, tempbody.getString(QUERY_ID));
        if (tempbody.containsKey(FIELD_TXN_AMT)) {
            //交易订单金额
            responseResult.put(UNIONPAY_TOTAL_AMOUNT, new BigDecimal(tempbody.getString(FIELD_TXN_AMT))
                    .divide(new BigDecimal(NUM_HUNDRED), NUM_TWO, RoundingMode.HALF_UP));
        }
        //商户流水
        responseResult.put(UNIONPAY_OUT_TRADE_NO, tempbody.getString(ORDER_ID));
        //	门店号
        responseResult.put(UNIONPAY_STORE_ID, tempbody.getString(DEVICE));
        //二维码路径
        responseResult.put(UNIONPAY_QR_CODE, tempbody.getString(QR_CODE));
        //交易备注
        responseResult.put(UNIONPAY_REFUND_REASON, tempbody.getString(REQ_RESERVED));
        //原交易流水号
        responseResult.put(UNIONPAY_OUT_REQUEST_NO, tempbody.getString(ORIG_ORY_ID));
        //付款码交易支付时间
        responseResult.put(UNIONPAY_GMT_PAYMENT, tempbody.getString(UNIONPAY_TRACE_TIME));
        //订单发送时间
        responseResult.put(UNIONPAY_SEND_PAY_DATE, timeStampDate(String.valueOf(tempbody.getDate(FIELD_TXN_TIME).getTime())));
    }

    /**
     * 包装银联pc wap返回结果信息
     *
     * @param parseResult 解析支付html
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packageHtmlPayResult(JSONObject parseResult) {
        reponseResult(getResponse(), parseResult);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return 返回时间
     * @author Mr.Yang
     * @date 2019/3/24
     */
    private String timeStampDate(String seconds) {
        if (StringUtils.isEmpty(seconds)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

}
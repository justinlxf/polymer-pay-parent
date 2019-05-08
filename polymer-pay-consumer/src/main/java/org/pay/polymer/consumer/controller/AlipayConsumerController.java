package org.pay.polymer.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.api.tpp.alipay.AlipayModel;
import org.pay.polymer.api.tpp.alipay.AlipayPayment;
import org.pay.polymer.common.util.BaseRquestAndResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static org.pay.polymer.common.tpp.alipay.AlipayConstants.*;
import static org.pay.polymer.common.util.DesktopEndRequest.requestMessage;
import static org.pay.polymer.common.util.DesktopEndResponse.reponseResult;
import static org.pay.polymer.common.util.NumberEnum.SUCCESS_CODE;
import static org.pay.polymer.common.util.PayMentConstants.*;
import static org.pay.polymer.consumer.config.FieldConstant.ALI_PAY;
import static org.pay.polymer.consumer.config.FieldConstant.*;
import static org.pay.polymer.consumer.config.InitConfig.payCode;


/**
 * <p>
 * 支付宝支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@RestController
@RequestMapping(ALI_PAY)
public class AlipayConsumerController extends BaseRquestAndResponse {

    @Reference(group = ALI_PAY_PAGE)
    private AlipayPayment pagePayMent;

    @Reference(group = ALI_PAY_PRECREATE)
    private AlipayPayment precreatePayMent;

    @Reference(group = ALI_PAY_WAP)
    private AlipayPayment wapPayment;

    @Reference(group = ALI_PAY_STRIPE)
    private AlipayPayment stripePayMent;

    @Reference(group = ALI_PAY_COMMON)
    private PayMentInterface payMentInterface;

    /**
     * 下载对账单
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_WNLOADBILL)
    public void downloadbill() {
        //处理对账单
        processPayMent(NUM_TEN);
    }

    /**
     * 支付取消
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_CANCEL)
    public void doCancel() {
        //处理支付取消
        processPayMent(NUM_NINE);
    }

    /**
     * 支付关闭
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_CLOSE)
    public void doClose() {
        //处理支付关闭
        processPayMent(NUM_EIGHT);
    }

    /**
     * 退款查询
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_REFUND_QUERY_PAY)
    public void doRefundQuery() {
        //处理退款查询
        processPayMent(NUM_SEVEN);
    }

    /**
     * 支付退款
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_REFUND_PAY)
    public void doRefund() {
        //处理支付查询
        processPayMent(NUM_SIX);
    }

    /**
     * 支付查询
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_QUERY_PAY)
    public void doQueryPay() {
        //处理支付查询
        processPayMent(NUM_FIVE);
    }

    /**
     * 收银员使用扫码设备读取用户手机支付宝“付款码”
     * /声波获取设备（如麦克风）读取用户手机支付宝的声波信息后，
     * 将二维码或条码信息/声波信息通过本接口上送至支付宝发起支付。
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_STRIPE_PAY)
    public void doStripePay() {
        //处理付款码支付
        processPayMent(NUM_FOUR);
    }

    /**
     * 生成二维码后，展示给用户，由用户扫描二维码完成订单支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_PRECREATE_PAY)
    public void doPrecreatePay() {
        //处理二维码支付
        processPayMent(NUM_THREE);
    }

    /**
     * 支付宝wap支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_WAP_PAY)
    public void doWapPay() {
        //处理wap支付
        processPayMent(NUM_TWO);
    }

    /**
     * 支付宝pc端支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_PAGE_PAY)
    public void doPagePay() {
        //处理pc支付
        processPayMent(NUM_ONE);
    }

    /**
     * 处理线上支付
     *
     * @param type 1 pc支付 2wap支付 3二维码 4付款码
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
        //初始化支付宝实例
        AlipayModel alipay = new AlipayModel(params.getString(FIELD_APPID));
        //填充业务参数
        alipay.setBizContent(params.toString());
        switch (type) {
            case NUM_ONE:
                //响应值
                packagePagePayResult(JSONObject.parseObject(pagePayMent.doPay(alipay)));
                break;
            case NUM_TWO:
                //响应值
                packageWapPayResult(JSONObject.parseObject(wapPayment.doPay(alipay)));
                break;
            case NUM_THREE:
                //响应值
                packageResult(JSONObject.parseObject(precreatePayMent.doPay(alipay)), ALIPAY_TRADE_PRECREATE_RESPONSE);
                break;
            case NUM_FOUR:
                //响应值
                packageResult(JSONObject.parseObject(stripePayMent.doPay(alipay)), ALIPAY_TRADE_PAY_RESPONSE);
                break;
            case NUM_FIVE:
                packageResult(JSONObject.parseObject(payMentInterface.doPayQuery(alipay)), ALIPAY_TRADE_QUERY_RESPONSE);
                break;
            case NUM_SIX:
                packageResult(JSONObject.parseObject(payMentInterface.doRefund(alipay)), ALIPAY_TRADE_REFUND_RESPONSE);
                break;
            case NUM_SEVEN:
                packageResult(JSONObject.parseObject(payMentInterface.doRefundQuery(alipay)), ALIPAY_TRADE_FASTPAY_REFUND_QUERY_RESPONSE);
                break;
            case NUM_EIGHT:
                packageResult(JSONObject.parseObject(payMentInterface.doClose(alipay)), ALIPAY_TRADE_CLOSE_RESPONSE);
                break;
            case NUM_NINE:
                packageResult(JSONObject.parseObject(payMentInterface.doCancel(alipay)), ALIPAY_TRADE_CANCEL_RESPONSE);
                break;
            case NUM_TEN:
                packageResult(JSONObject.parseObject(payMentInterface.downloadurl(alipay)), ALIPAY_DATA_DATASERVICE_BILL_DOWNLOADURL_QUERY_RESPONSE);
                break;
            default:
                break;
        }
    }

    /**
     * 包装结果信息
     *
     * @param parseResult 解析返回结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packageResult(JSONObject parseResult, String respType) {
        //获取返回值是否存在错误
        String form = parseResult.getString(FIELD_FORM);
        //获取错误信息
        JSONObject tempbody = JSON.parseObject(form).getJSONObject(respType);
        if (tempbody.getIntValue(CODE) == NUM_THE_THOUSAND) {
            //全局响应码
            globalResponseCode(tempbody, parseResult);
            //组装数据
            handlerAliPayField(tempbody, parseResult);
            reponseResult(getResponse(), parseResult);
            return;
        }
        //获取错误信息返回值
        handlerAliPayWrongResult(form, respType, parseResult);
        reponseResult(getResponse(), parseResult);
    }

    /**
     * 包装支付宝wap返回结果信息
     *
     * @param parseResult 解析wap支付返回结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packageWapPayResult(JSONObject parseResult) {
        //获取返回值是否存在错误
        String form = parseResult.getString(FIELD_FORM);
        if (form.startsWith(ALIPAY_STARTS_WITH)) {
            //获取错误信息返回值
            handlerAliPayWrongResult(form, ALIPAY_TRADE_WAP_PAY_RESPONSE, parseResult);
        }
        reponseResult(getResponse(), parseResult);
    }

    /**
     * 包装支付宝pc返回结果信息
     *
     * @param parseResult 解析pc支付返回结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packagePagePayResult(JSONObject parseResult) {
        //获取返回值是否存在错误
        String form = parseResult.getString(FIELD_FORM);
        if (form.startsWith(ALIPAY_STARTS_WITH)) {
            //获取错误信息返回值
            handlerAliPayWrongResult(form, ALIPAY_TRADE_PAGE_PAY_RESPONSE, parseResult);
        }
        reponseResult(getResponse(), parseResult);
    }

    /**
     * 处理错误信息
     *
     * @param form         响应信息
     * @param responseType 响应类型
     * @param parseResult  响应结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void handlerAliPayWrongResult(String form, String responseType, JSONObject parseResult) {
        //获取错误信息
        JSONObject tempbody = JSON.parseObject(form).getJSONObject(responseType);
        if (parseResult.containsKey(CODE)) {
            int code = parseResult.getIntValue(CODE);
            String msg = parseResult.getString(MSG);
            //清空原有信息,筛选出返回字段
            parseResult.clear();
            parseResult.put(MSG, tempbody.getString(MSG).equals(ALIPAY_SUCCESS) ? SUCCESS_CODE.getName() : tempbody.getString(MSG));
            parseResult.put(CODE, code);
        }
        if (tempbody.containsKey(SUB_CODE)) {
            //业务响应码
            parseResult.put(SUB_CODE, tempbody.getString(SUB_CODE));
            parseResult.put(SUB_MSG, payCode.containsKey(tempbody.getString(SUB_CODE))
                    ? payCode.get(tempbody.getString(SUB_CODE)) : tempbody.getString(SUB_MSG));
        }
    }

    /**
     * 全局响应码
     *
     * @param tempbody       临时数据对象
     * @param responseResult 返回对象
     * @author Mr.Yang
     * @date 2019/3/14 0014
     */
    private void globalResponseCode(JSONObject tempbody, JSONObject responseResult) {
        //清空原有信息,筛选出返回字段
        responseResult.clear();
        //网关响应码
        responseResult.put(MSG, tempbody.getString(MSG).equals(ALIPAY_SUCCESS) ? SUCCESS_CODE.getName() : tempbody.getString(MSG));
        responseResult.put(CODE, tempbody.getIntValue(CODE));
    }

    /**
     * 查询与付款码组装数据
     *
     * @param tempbody       临时数据对象
     * @param responseResult 返回对象
     * @author Mr.Yang
     * @date 2019/3/14 0014
     */
    private void handlerAliPayField(JSONObject tempbody, JSONObject responseResult) {
        //交易状态
        responseResult.put(ALIPAY_TRADE_STATUS, tempbody.getString(ALIPAY_TRADE_STATUS));
        //支付宝交易号
        responseResult.put(ALIPAY_TRADE_NO, tempbody.getString(ALIPAY_TRADE_NO));
        //买家支付宝账号
        responseResult.put(ALIPAY_BUYER_LOGON_ID, tempbody.getString(ALIPAY_BUYER_LOGON_ID));
        //交易订单金额
        responseResult.put(ALIPAY_TOTAL_AMOUNT, tempbody.getBigDecimal(ALIPAY_TOTAL_AMOUNT));
        //退款总金额
        responseResult.put(ALIPAY_REFUND_FEE, tempbody.getBigDecimal(ALIPAY_REFUND_FEE));
        //本次退款是否发生了资金变化
        responseResult.put(ALIPAY_FUND_CHANGE, tempbody.getString(ALIPAY_FUND_CHANGE));
        //退款支付时间
        responseResult.put(ALIPAY_GMT_REFUND_PAY, tempbody.getString(ALIPAY_GMT_REFUND_PAY));
        //付款码交易支付时间
        responseResult.put(ALIPAY_GMT_PAYMENT, tempbody.getString(ALIPAY_GMT_PAYMENT));
        //商户流水
        responseResult.put(ALIPAY_OUT_TRADE_NO, tempbody.getString(ALIPAY_OUT_TRADE_NO));
        //二维码路径
        responseResult.put(ALIPAY_QR_CODE, tempbody.getString(ALIPAY_QR_CODE));
        //本次交易打款给卖家的时间
        responseResult.put(ALIPAY_SEND_PAY_DATE, tempbody.getString(ALIPAY_SEND_PAY_DATE));
        //发起退款时，传入的退款原因
        responseResult.put(ALIPAY_REFUND_REASON, tempbody.getString(ALIPAY_REFUND_REASON));
        //商户门店编号
        responseResult.put(ALIPAY_STORE_ID, tempbody.getString(ALIPAY_STORE_ID));
        //本次退款请求，对应的退款金额
        responseResult.put(ALIPAY_REFUND_AMOUNT, tempbody.getBigDecimal(ALIPAY_REFUND_AMOUNT));
        //本笔退款对应的退款请求号
        responseResult.put(ALIPAY_OUT_REQUEST_NO, tempbody.getString(ALIPAY_OUT_REQUEST_NO));
        //是否需要重试
        responseResult.put(ALIPAY_RETRY_FLAG, tempbody.getString(ALIPAY_RETRY_FLAG));
        //本次撤销触发的交易动作
        responseResult.put(ALIPAY_ACTION, tempbody.getString(ALIPAY_ACTION));
        //账单下载地址链接，获取连接后30秒后未下载，链接地址失效。
        responseResult.put(ALIPAY_DOWNLOAD_URL, tempbody.getString(ALIPAY_DOWNLOAD_URL));
    }
}
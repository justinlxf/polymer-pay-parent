package org.pay.polymer.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.api.tpp.jdpay.JdPayment;
import org.pay.polymer.api.tpp.jdpay.Jdpay;
import org.pay.polymer.api.tpp.jdpay.JdpayModel;
import org.pay.polymer.common.util.BaseRquestAndResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.FIELD_TRADE_TYPE;
import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.*;
import static org.pay.polymer.common.util.DesktopEndRequest.requestMessage;
import static org.pay.polymer.common.util.DesktopEndResponse.reponseResult;
import static org.pay.polymer.common.util.PayMentConstants.*;
import static org.pay.polymer.consumer.config.FieldConstant.*;
import static org.pay.polymer.consumer.config.InitConfig.payCode;


/**
 * <p>
 * 京东支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@RestController
@RequestMapping(JDPAY)
public class JdpayConsumerController extends BaseRquestAndResponse {

    @Reference(group = JD_PAY_ONLINE)
    private JdPayment onlinePayMent;

    @Reference(group = JD_PAY_QRCODE)
    private JdPayment qrcodePayMent;


    @Reference(group = JD_PAY_STRIPE)
    private JdPayment stripePayMent;


    @Reference(group = JD_PAY_UNIFIED)
    private JdPayment unifiedPayMent;


    @Reference(group = JD_PAY_COMMON)
    private PayMentInterface jdpayInterface;

    /**
     * 下载对账单
     *
     * @return 返回对账单
     * @author Mr.Yang
     * @date 2019/3/8 0008
     */
    @PostMapping(DO_WNLOADBILL)
    public void downloadbill() {
        //下载对账单
        processPayMent(NUM_NINE);
    }

    /**
     * 退款查询
     *
     * @return 返回退款查询
     * @author Mr.Yang
     * @date 2019/3/8 0008
     */
    @PostMapping(DO_REFUND_QUERY_PAY)
    public void doRefundQuery() {
        //退款查询
        processPayMent(NUM_EIGHT);
    }


    /**
     * 支付查询
     *
     * @return 返回查询信息
     * @author Mr.Yang
     * @date 2019/3/8 0008
     */
    @PostMapping(DO_QUERY_PAY)
    public void doQueryPay() {
        //支付查询
        processPayMent(NUM_EIGHT);
    }

    /**
     * 支付退款
     *
     * @return 返回退款信息
     * @author Mr.Yang
     * @date 2019/3/8 0008
     */
    @PostMapping(DO_REFUND_PAY)
    public void doRefund() {
        //支付退款
        processPayMent(NUM_SEVEN);
    }

    /**
     * 取消支付
     *
     * @return 返回撤销信息
     * @author Mr.Yang
     * @date 2019/3/8 0008
     */
    @PostMapping(DO_CANCEL)
    public void doCancel() {
        //取消支付
        processPayMent(NUM_SIX);
    }

    /**
     * 付款码支付接口用于客户使用付款码支付时，
     * 商户使用扫码枪完成一键下单并支付功能。
     *
     * @return 返回支付信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_STRIPE_PAY)
    public void doStripePay() {
        //处理付款码支付
        processPayMent(NUM_FIVE);
    }

    /**
     * 商户二维码
     * 该接口用于自定义金额支付等场景，京东支付服务会根据请求数据验证商户身份，
     * 以及验证支付信息是否被篡改。验证通过后，京东支付服务会在当前页面弹出支付页面弹框。
     *
     * @return 返回支付html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_QR_CODE_PAY)
    public void doQrcodePay() {
        //处理商户二维码支付
        processPayMent(NUM_FOUR);
    }

    /**
     * 京东统一下单接口
     * 商户系统先调用该接口在京东支付服务后台生成交易单，再按扫码、APP等不同场景发起支付。
     *
     * @return 返回二维码路径等信息
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_PRECREATE_PAY)
    public void doPrecreatePay() {
        //处理统一下单支付
        processPayMent(NUM_THREE);
    }


    /**
     * 京东wa支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_WAP_PAY)
    public void doWapPay() {
        //处理wap支付
        processPayMent(NUM_ONE);
    }

    /**
     * 京东pc端支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_PAGE_PAY)
    public void doPagePay() {
        //处理pc支付
        processPayMent(NUM_TWO);
    }

    /**
     * 处理线上支付
     *
     * @param type 1 wap支付 2 pc支付 3统一下单 4商户二维码
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
        //初始化京东实例
        JdpayModel jdpayModel;
        switch (type) {
            case NUM_ONE:
                jdpayModel = new JdpayModel(params.getString(FIELD_APPID), FIELD_WAP);
                break;
            default:
                jdpayModel = new JdpayModel(params.getString(FIELD_APPID));
                break;
        }
        //填充业务参数
        Jdpay jdpay = new Jdpay();
        //商户流水号
        if (params.containsKey(FIELD_OUT_TRADE_NO)) {
            jdpay.setTradeNum(params.getString(FIELD_OUT_TRADE_NO));
        }
        //0：消费；1：退款
        if (params.containsKey(FIELD_TRADE_TYPE)) {
            jdpay.setTradeType(params.getString(FIELD_TRADE_TYPE));
        }
        //订单名称
        if (params.containsKey(FIELD_SUBJECT)) {
            jdpay.setTradeName(params.getString(FIELD_SUBJECT));
        }
        //订单金额
        if (params.containsKey(FIELD_TOTAL_AMOUNT)) {
            jdpay.setAmount(params.getString(FIELD_TOTAL_AMOUNT));
        }
        //同步页面
        if (params.containsKey(FIELD_RETURN_URL)) {
            jdpay.setCallbackUrl(params.getString(FIELD_RETURN_URL));
        }
        //同步页面
        if (params.containsKey(FIELD_O_TRADE_NUM)) {
            jdpay.setOTradeNum(params.getString(FIELD_O_TRADE_NUM));
        }
        //异步回调
        if (params.containsKey(FIELD_NOTIFY_URL)) {
            jdpay.setNotifyUrl(params.getString(FIELD_NOTIFY_URL));
        }
        //订单详情
        if (params.containsKey(FIELD_BODY)) {
            jdpay.setTradeDesc(params.getString(FIELD_BODY));
        }
        //付款码
        if (params.containsKey(FIELD_TOKEN)) {
            jdpay.setToken(params.getString(FIELD_TOKEN));
        }
        //门店号
        if (params.containsKey(FIELD_DEVICE)) {
            jdpay.setDevice(params.getString(FIELD_DEVICE));
        }
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
        if (params.containsKey(FIELD_OUT_REQUEST_NO)) {
            jdpay.setOTradeNum(params.getString(FIELD_OUT_REQUEST_NO));
        }
        jdpayModel.setBizContent(JSONObject.toJSONString(jdpay));
        //处理支付信息
        switch (type) {
            case NUM_THREE:
                //响应值
                packagePayResult(JSONObject.parseObject(unifiedPayMent.doPay(jdpayModel)));
                break;
            case NUM_FOUR:
                //响应值
                packageHtmlPayResult(JSONObject.parseObject(qrcodePayMent.doPay(jdpayModel)));
                break;
            case NUM_FIVE:
                //响应值
                packagePayResult(JSONObject.parseObject(stripePayMent.doPay(jdpayModel)));
                break;
            case NUM_SIX:
                //响应值
                packagePayResult(JSONObject.parseObject(jdpayInterface.doCancel(jdpayModel)));
                break;
            case NUM_SEVEN:
                //响应值
                packagePayResult(JSONObject.parseObject(jdpayInterface.doRefund(jdpayModel)));
                break;
            case NUM_EIGHT:
                //响应值
                packagePayResult(JSONObject.parseObject(jdpayInterface.doPayQuery(jdpayModel)));
                break;
            case NUM_NINE:
                //响应值
                packagePayResult(JSONObject.parseObject(jdpayInterface.downloadurl(jdpayModel)));
                break;
            default:
                //响应值
                packageHtmlPayResult(JSONObject.parseObject(onlinePayMent.doPay(jdpayModel)));
                break;
        }
    }


    /**
     * 包装京东pc wap返回结果信息
     *
     * @param parseResult 解析支付html
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packageHtmlPayResult(JSONObject parseResult) {
        reponseResult(getResponse(), parseResult);
    }

    /**
     * 包装京东返回结果信息
     *
     * @param parseResult 解析支付结果
     * @author Mr.Yang
     * @date 2019/3/13 0013
     */
    private void packagePayResult(JSONObject parseResult) {
        JSONObject result = new JSONObject();
        //获取错误信息
        JSONObject tempbody = parseResult.getJSONObject(FIELD_FORM).getJSONObject(RESULT);
        if (tempbody != null &&
                tempbody.getString(CODE).equals(NUM_ZEROS)) {
            //全局响应码
            globalResponseCode(parseResult, result);
            //组装数据
            handlerJdPayField(parseResult, result);
            reponseResult(getResponse(), result);
            return;
        }
        if (null == tempbody) {
            tempbody = parseResult.getJSONObject(FIELD_FORM);
        }
        //错误信息
        handlerJdPayWrongResult(parseResult, tempbody, result);
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
    private void handlerJdPayWrongResult(JSONObject form, JSONObject tempbody, JSONObject parseResult) {
        //全局响应码
        globalResponseCode(form, parseResult);
        //业务响应码
        parseResult.put(SUB_CODE, tempbody.getIntValue(CODE));
        parseResult.put(SUB_MSG, payCode.containsKey(tempbody.getString(CODE))
                ? payCode.get(tempbody.getString(CODE)) :
                tempbody.containsKey(DESC) ? tempbody.getString(DESC) :
                        tempbody.getString(MSG));
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
    private void handlerJdPayField(JSONObject tempbody, JSONObject responseResult) {
        tempbody = tempbody.getJSONObject(FIELD_FORM);
        //交易状态
        responseResult.put(JD_TRADE_STATUS, tempbody.getIntValue(STATUS));
        //京东交易号
        responseResult.put(JD_TRADE_NO, tempbody.getString(ORDER_ID));
        //交易订单金额
        responseResult.put(JD_TOTAL_AMOUNT, new BigDecimal(tempbody.getString(AMOUNT))
                .divide(new BigDecimal(NUM_HUNDRED), NUM_TWO, RoundingMode.HALF_UP));
        //付款码交易支付时间
        responseResult.put(JD_GMT_PAYMENT, tempbody.getString(TRADE_TIME));
        //商户流水
        responseResult.put(JD_OUT_TRADE_NO, tempbody.getString(TRADE_NUM));
        //	门店号
        responseResult.put(JD_STORE_ID, tempbody.getString(DEVICE));
        //交易时间
        responseResult.put(JD_SEND_PAY_DATE, tempbody.getString(TRADE_TIME));
        //二维码路径
        responseResult.put(JD_QR_CODE, tempbody.getString(QR_CODE));
        //交易备注
        responseResult.put(JD_REFUND_REASON, tempbody.getString(NOTE));
        //原交易流水号
        responseResult.put(JD_OUT_REQUEST_NO, tempbody.getString(O_TRADE_NUM));
        switch (tempbody.getIntValue(TRADE_TYPE)) {
            case 1:
                //本次撤销触发的交易动作
                responseResult.put(JD_ACTION, "refund");
                break;
            case 2:
                //本次撤销触发的交易动作
                responseResult.put(JD_ACTION, "cancel");
                break;
            default:
                break;
        }
    }

}
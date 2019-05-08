package org.pay.polymer.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.pay.polymer.api.PayMentInterface;
import org.pay.polymer.api.tpp.wechat.WechatPayment;
import org.pay.polymer.api.tpp.wechat.WechatpayModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static org.pay.polymer.common.util.BaseRquestAndResponse.getRequest;
import static org.pay.polymer.common.util.BaseRquestAndResponse.getResponse;
import static org.pay.polymer.common.util.DesktopEndRequest.requestMessage;
import static org.pay.polymer.common.util.DesktopEndResponse.reponseResult;
import static org.pay.polymer.common.util.PayMentConstants.*;
import static org.pay.polymer.consumer.config.FieldConstant.*;


/**
 * <p>
 * 微信支付
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
@RestController
@RequestMapping(WECHAT)
public class WechatpayConsumerController {

    @Reference(group = WECHAT_PAY_APP)
    private WechatPayment appPayMent;

    @Reference(group = WECHAT_PAY_JSAPI)
    private WechatPayment jsapiPayMent;


    @Reference(group = WECHAT_PAY_MICROPAY)
    private WechatPayment micropayPayMent;


    @Reference(group = WECHAT_PAY_NATIVE)
    private WechatPayment nativePayMent;

    @Reference(group = WECHAT_PAY_MWEB)
    private WechatPayment mwebPayMent;


    @Reference(group = WECHAT_PAY_COMMON)
    private PayMentInterface wechatpayInterface;

    /**
     * 微信app支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_APP_PAY)
    public void doAppPay() {
        //微信app支付
        processPayMent(NUM_FIVE);
    }

    /**
     * 微信公众号支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_JSAPI_PAY)
    public void doJsapiPay() {
        //微信公众号支付
        processPayMent(NUM_FOUR);
    }

    /**
     * 微信商户二维码支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_PRECREATE_PAY)
    public void doPrecreatePay() {
        //微信商户二维码支付
        processPayMent(NUM_THREE);
    }

    /**
     * 微信付款码支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_STRIPE_PAY)
    public void doStripePay() {
        //微信付款码支付
        processPayMent(NUM_TWO);
    }

    /**
     * 微信h5支付
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_WAP_PAY)
    public void doWapPay() {
        //微信wap支付
        processPayMent(NUM_ONE);
    }

    /**
     * 微信退款查询
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_REFUND_QUERY_PAY)
    public void doRefundQuery() {
        //微信退款查询
        processPayMent(NUM_TEN);
    }

    /**
     * 微信支付查询
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_QUERY_PAY)
    public void doQueryPay() {
        //微信支付查询
        processPayMent(NUM_NINE);
    }

    /**
     * 微信退款
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_REFUND_PAY)
    public void doRefund() {
        //微信退款
        processPayMent(NUM_EIGHT);
    }

    /**
     * 微信取消订单
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_CANCEL)
    public void doCancel() {
        //微信取消支付
        processPayMent(NUM_SEVEN);
    }

    /**
     * 微信关闭订单
     *
     * @return 返回html代码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    @PostMapping(DO_CLOSE)
    public void doClose() {
        //微信关闭支付
        processPayMent(NUM_SIX);
    }

    /**
     * 处理线上支付
     *
     * @param type 1 wap支付 2 微信付款码支付 3微信商户二维码
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    private void processPayMent(int type) {
        //获取请求参数
        JSONObject params = requestMessage(getRequest(), FIELD_TOTAL_AMOUNT, FIELD_APPID,
                FIELD_SUBJECT, FIELD_OUT_TRADE_NO);
        //是否存在空值，如果存在直接返回
        if (Objects.requireNonNull(params).getBoolean(FIELD_CHECK_STATUS)) {
            reponseResult(getResponse(), params);
            return;
        }
        //获取请求参数
        WechatpayModel wechatpay = new WechatpayModel(params.getString(FIELD_APPID));
        //移除appid
        params.remove(FIELD_APPID);
        wechatpay.setBizContent(params.toString());
        //处理支付信息
        switch (type) {
            case NUM_ONE:
                //响应值
                reponseResult(getResponse(), JSONObject.parseObject(mwebPayMent.doPay(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_TWO:
                reponseResult(getResponse(), JSONObject.parseObject(micropayPayMent.doPay(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_THREE:
                reponseResult(getResponse(), JSONObject.parseObject(nativePayMent.doPay(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_FOUR:
                reponseResult(getResponse(), JSONObject.parseObject(jsapiPayMent.doPay(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_FIVE:
                reponseResult(getResponse(), JSONObject.parseObject(appPayMent.doPay(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_SIX:
                reponseResult(getResponse(), JSONObject.parseObject(wechatpayInterface.doClose(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_SEVEN:
                reponseResult(getResponse(), JSONObject.parseObject(wechatpayInterface.doCancel(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_EIGHT:
                reponseResult(getResponse(), JSONObject.parseObject(wechatpayInterface.doRefund(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_NINE:
                reponseResult(getResponse(), JSONObject.parseObject(wechatpayInterface.doPayQuery(wechatpay)).getString(FIELD_FORM));
                break;
            case NUM_TEN:
                reponseResult(getResponse(), JSONObject.parseObject(wechatpayInterface.doRefundQuery(wechatpay)).getString(FIELD_FORM));
                break;
            default:
                break;
        }
    }
}
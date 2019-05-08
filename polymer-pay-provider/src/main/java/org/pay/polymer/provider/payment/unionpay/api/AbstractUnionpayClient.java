package org.pay.polymer.provider.payment.unionpay.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.pay.polymer.api.unionpay.Unionpay;
import org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.AcpService;
import org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.UnionpayHashMap;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import static org.pay.polymer.common.unionpay.UnionpayConstants.*;
import static org.pay.polymer.common.util.NumberEnum.*;
import static org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.SDKConstants.GATEWAY_FRONT_TRANS_REQ;
import static org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.UnionpayLogger.*;

/**
 * @program: polymer-pay-provider
 * @description: 银联客户端sdk执行
 * @author: Mr.Yang
 * @create: 2018-11-03 13:55
 **/
public abstract class AbstractUnionpayClient implements UnionpayClient {

    /**
     * 银联配置参数
     */
    private Unionpay unionpay;

    public AbstractUnionpayClient(Unionpay unionpay) {
        this.unionpay = unionpay;
    }

    @Override
    public <T extends UnionpayResponse> T execute(UnionpayRequest<T> request) throws UnionpayApiException {
        //开始时间
        long beginTime = System.currentTimeMillis();
        //获取参数
        UnionpayHashMap appParams = new UnionpayHashMap(request.getTextParams());
        //拼接参数
        splicingParameter(appParams);
        //进行参数签名
        Map<String, String> reqData = AcpService.signByCertInfo(appParams, this.unionpay.getAcpSign(), this.unionpay.getCerPwd(), CHARSET_UTF8);
        //调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
        Map<String, String> rspData = AcpService.post(reqData, request.getServerUrl(), CHARSET_UTF8);
        //应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
        T rsp;
        //反射响应类型
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new UnionpayApiException(e);
        }
        //如果返回值为空，则服务不可用
        if (rspData.isEmpty()) {
            rsp.setSubCode(ERROR_CODE.getCode());
            rsp.setSubMsg(ERROR_CODE.getName());
            return rsp;
        }
        //获取返回码
        String respCode = rspData.get("respCode");
        //结束时间
        long endTime = System.currentTimeMillis();
        rsp.setParams(appParams);
        //返回不成功
        if (!UNIONPAY_SUCCESS_CODE.getCode().equals(respCode)) {
            rsp.setCode(FAIL_CODE.getCode());
            logErrorScene(rspData.toString(), rsp, endTime - beginTime);
        } else {
            rsp.setCode(SUCCESS_CODE.getCode());
            logBizSummary(rspData.toString(), rsp, endTime - beginTime);
        }
        //添加到body中
        JSONObject result = JSONObject.parseObject(JSON.toJSONString(rspData));
        //返回码
        result.put(CODE, rsp.getCode());
        //返回信息
        result.put(MSG, result.getString(FIELD_RESP_MSG));
        //添加到body中
        rsp.setBody(result.toString());
        return rsp;
    }

    @Override
    public <T extends UnionpayResponse> T pageExecute(UnionpayRequest<T> request) throws UnionpayApiException {
        T rsp;
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logBizError(e);
            throw new UnionpayApiException(e);
        }
        assert rsp != null;
        rsp.setBody(getRequestHolderWithSign(request));
        return rsp;
    }


    /**
     * 包装并进行签名操作
     *
     * @param request 请求信息
     * @return 返回html表单
     * @author Mr.Yang
     * @date 2018/11/3
     */
    private String getRequestHolderWithSign(UnionpayRequest<?> request) {
        //获取参数
        UnionpayHashMap appParams = new UnionpayHashMap(request.getTextParams());
        //拼接参数
        splicingParameter(appParams);
        //业务类型，B2C网关支付，手机wap支付
        appParams.put(FIELD_BIZ_TYPE, BIZ_TYPE);
        //交易类型 ，01：消费
        appParams.put(FIELD_TXN_TYPE, TXN_TYPE);
        //交易子类型， 01：自助消费
        appParams.put(FIELD_TXN_SUB_TYPE, TXN_SUB_TYPE);
        //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
        if (!appParams.containsKey(FIELD_CHANNEL_TYPE)) {
            appParams.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_PC);
        }
        //交易币种（境内商户一般是156 人民币）
        appParams.put(FIELD_CURRENCY_CODE, CURRENCY_CODE);
        //交易金额，单位分，不要带小数点
        appParams.put(FIELD_TXN_AMT,
                new BigDecimal(appParams.get(FIELD_TXN_AMT)).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
        //进行参数签名
        Map<String, String> submitFromData = AcpService.signByCertInfo(appParams, this.unionpay.getAcpSign(), this.unionpay.getCerPwd(), CHARSET_UTF8);
        //生成自动跳转的Html表单
        return AcpService.createAutoFormHtml(GATEWAY_FRONT_TRANS_REQ, submitFromData, CHARSET_UTF8);
    }


    @Override
    public <T extends UnionpayResponse> T pageExecute(UnionpayRequest<T> request, String method) throws UnionpayApiException {
        return null;
    }

    /**
     * 拼接请求信息
     *
     * @param appParams 请求参数
     * @author Mr.Yang
     * @date 2018/11/24
     */
    private void splicingParameter(UnionpayHashMap appParams) {
        //版本号，全渠道默认值
        appParams.put(FIELD_VERSION, VERSION_NO);
        //字符集编码，可以使用UTF-8,GBK两种方式
        appParams.put(FIELD_ENCODING, CHARSET_UTF8);
        //签名方法
        appParams.put(FIELD_SIGN_METHOD, SIGN_METHOD);
        //商户接入参数
        //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
        appParams.put(FIELD_MERID, this.unionpay.getMerId());
        //接入类型，0：直连商户
        appParams.put(FIELD_ACCESS_TYPE, ACCESS_TYPE);
        //时间处理
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone(DATE_TIMEZONE));
        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        appParams.put(FIELD_TXN_TIME, df.format(new Date(System.currentTimeMillis())));
    }
}

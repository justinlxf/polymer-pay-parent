package org.pay.polymer.provider.payment.tpp.jdpay.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.jr.pay.gate.signature.util.BASE64;
import com.jd.jr.pay.gate.signature.util.JdPayUtil;
import com.jd.jr.pay.gate.signature.util.SignUtil;
import com.jd.jr.pay.gate.signature.util.ThreeDesUtil;
import org.pay.polymer.api.tpp.jdpay.Jdpay;
import org.pay.polymer.common.tpp.jdpay.JdpayConstants;
import org.pay.polymer.common.util.PayMentConstants;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.JdpayHashMap;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.RequestParametersHolder;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.WebUtils;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeOnlinePayMentRequest;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeQRCodePayMentRequest;
import org.pay.polymer.provider.payment.tpp.jdpay.api.response.JdpayXmlResponse;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.CODE;
import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.MSG;
import static org.pay.polymer.common.util.NumberEnum.*;
import static org.pay.polymer.provider.payment.tpp.jdpay.api.StringEscape.htmlSecurityEscape;
import static org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.JdpayLogger.*;


/**
 * <p>
 * 京东客户端sdk执行
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public abstract class AbstractJdpayClient implements JdpayClient {

    /**
     * 京东商户私钥
     */
    private String privateKey;

    /**
     * 京东公钥
     */
    private String jdpayPublicKey;

    /**
     * des密秘钥
     */
    private String desKey;

    /**
     * 支付接口
     */
    private String serverUrl;
    /**
     * 商户号
     */
    private String merchant;
    /**
     * 字符集
     */
    private String charset;


    static {
        //重新设置为空
        Security.setProperty("jdk.certpath.disabledAlgorithms", "");
    }


    public AbstractJdpayClient(String serverUrl, String merchant,
                               String charset, String privateKey, String desKey) {
        this.serverUrl = serverUrl;
        this.merchant = merchant;
        this.charset = charset;
        this.privateKey = privateKey;
        this.desKey = desKey;

    }


    public AbstractJdpayClient(String serverUrl, String merchant,
                               String charset, String privateKey, String jdpayPublicKey, String desKey) {
        this.serverUrl = serverUrl;
        this.merchant = merchant;
        this.charset = charset;
        this.privateKey = privateKey;
        this.jdpayPublicKey = jdpayPublicKey;
        this.desKey = desKey;
    }


    @Override
    public <T extends JdpayResponse> T execute(JdpayRequest<T> request) throws JdpayApiException {
        //开始时间
        long beginTime = System.currentTimeMillis();
        //获取参数
        JdpayHashMap appParams = new JdpayHashMap(request.getTextParams());
        appendParams(appParams);
        //返回值
        String resultXmlData;
        T rsp;
        //反射响应类型
        try {
            //TODO 后续考虑反射缓存
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new JdpayApiException(e);
        }
        //调用京东接口
        JdpayXmlResponse xmlResponse;
        try {
            //转换为实体（Map会出现报错，京东Bug）
            Jdpay jdpayJson = JSON.parseObject(JSONObject.toJSONString(appParams), Jdpay.class);
            //根据不同业务剥离字段内容
            deleteField(jdpayJson, serverUrl);
            //转xml格式
            String tradeXml = JdPayUtil.genReqXml(jdpayJson, privateKey, desKey);
            //调用接口
            resultXmlData = WebUtils.sendPostRequest(serverUrl, tradeXml, JdpayConstants.CONTENT_TYPE, charset);
            //判断是否为空.如果为空直接返回
            if (StringUtils.isEmpty(resultXmlData)) {
                rsp.setSubCode(ERROR_CODE.getCode());
                rsp.setSubMsg(ERROR_CODE.getName());
                return rsp;
            }
            //开始解密
            xmlResponse = JdPayUtil.parseResp
                    (jdpayPublicKey, desKey, resultXmlData, JdpayXmlResponse.class);
        } catch (Exception e) {
            logBizError(e);
            throw new JdpayApiException(e);
        }
        //设置参数
        rsp.setParams(appParams);
        //结束时间
        long endTime = System.currentTimeMillis();
        //返回不成功
        if (!JDPAY_SUCCESS_CODE.getCode().equals(xmlResponse.getResult().getCode())) {
            rsp.setCode(FAIL_CODE.getCode());
            logErrorScene(resultXmlData, rsp, endTime - beginTime);
        } else {
            rsp.setCode(SUCCESS_CODE.getCode());
            logBizSummary(resultXmlData, rsp, endTime - beginTime);
        }
        //添加到body中
        JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(xmlResponse));
        //返回码
        result.put(CODE, rsp.getCode());
        //返回信息
        result.put(MSG, result.getJSONObject(JdpayConstants.RESULT).get(JdpayConstants.DESC));
        //添加到body中
        rsp.setBody(result.toString());
        return rsp;
    }

    @Override
    public <T extends JdpayResponse> T pageExecute(JdpayRequest<T> request) throws JdpayApiException {
        return pageExecute(request, PayMentConstants.METHOD_POST);
    }

    @Override
    public <T extends JdpayResponse> T pageExecute(JdpayRequest<T> request, String httpMethod) throws JdpayApiException {
        //获取包装类
        RequestParametersHolder requestHolder = getRequestHolderWithSign(request);
        T rsp;
        try {
            Class<T> clazz = request.getResponseClass();
            //TODO 后续考虑反射缓存
            rsp = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logBizError(e);
            throw new JdpayApiException(e);
        }
        assert rsp != null;
        rsp.setBody(WebUtils.buildForm(serverUrl, requestHolder.getApplicationParams()));
        return rsp;
    }


    /**
     * 包装并进行签名操作
     *
     * @param request 请求信息
     * @return 返回请求包装类
     * @author Mr.Yang
     * @date 2018/11/3
     */
    private RequestParametersHolder getRequestHolderWithSign(JdpayRequest<?> request) {
        RequestParametersHolder requestHolder = new RequestParametersHolder();
        //获取参数
        JdpayHashMap appParams = new JdpayHashMap(request.getTextParams());
        appendParams(appParams);
        //判断统一下单或在线支付
        if (request instanceof JdpayTradeOnlinePayMentRequest || request instanceof JdpayTradeQRCodePayMentRequest) {
            List<String> unSignedKeyList = new ArrayList<>();
            unSignedKeyList.add(JdpayConstants.SIGN);
            //进行特殊字符处理
            appParams.forEach((k, v) -> appParams.put(k, doFilterCharProcess(v)));
            //进行签名
            appParams.put(JdpayConstants.SIGN, SignUtil.signRemoveSelectedKeys(appParams, privateKey, unSignedKeyList));
            //二进制key
            byte[] key = BASE64.decode(desKey);
            //进行加密
            appParams.forEach((k, v) -> {
                if (!JdpayConstants.MERCHANT.equals(k) && !JdpayConstants.VERSION.equals(k)
                        && !JdpayConstants.SIGN.equals(k)) {
                    appParams.put(k, ThreeDesUtil.encrypt2HexStr(key, v));
                }
            });
        }
        //设置到当前实例中
        requestHolder.setApplicationParams(appParams);
        return requestHolder;
    }

    /**
     * 对特殊字符进行处理
     *
     * @param value 值
     * @return 返回处理值
     * @author Mr.Yang
     * @date 2018/11/3
     */
    private String doFilterCharProcess(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        } else {
            return htmlSecurityEscape(value);
        }
    }

    /**
     * 拼接请求参数
     *
     * @param appParams 请求参数
     * @author Mr.Yang
     * @date 2019/3/28 0028
     */
    private void appendParams(JdpayHashMap appParams) {
        appParams.put(JdpayConstants.VERSION, JdpayConstants.VERSION_NO);
        //设置商户号
        appParams.put(JdpayConstants.MERCHANT, merchant);
        //设置时间
        DateFormat format = new SimpleDateFormat(JdpayConstants.DATE_TIME_FORMAT);
        format.setTimeZone(TimeZone.getTimeZone(JdpayConstants.DATE_TIMEZONE));
        appParams.put(JdpayConstants.TRADETIME, format.format(new Date()));
        //是否存在金额字段
        if (appParams.containsKey(JdpayConstants.AMOUNT)) {
            //元转分
            appParams.put(JdpayConstants.AMOUNT, new BigDecimal(appParams.get(JdpayConstants.AMOUNT)).multiply(new BigDecimal(100)).stripTrailingZeros());
        }
    }


    /**
     * 删除无用字段属性
     *
     * @param jdpay     京东实体
     * @param serverUrl 请求接口
     * @author Mr.Yang
     * @date 2019/3/28 0028
     */
    private void deleteField(Jdpay jdpay, String serverUrl) {
        if (serverUrl.endsWith(JdpayConstants.FIELD_QUERY)) {
            jdpay.setCurrency(null);
            jdpay.setTradeTime(null);
        } else if (serverUrl.endsWith(JdpayConstants.FIELD_REFUND)) {
            jdpay.setTradeType(null);
        }
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getJdpayPublicKey() {
        return jdpayPublicKey;
    }

    public void setJdpayPublicKey(String jdpayPublicKey) {
        this.jdpayPublicKey = jdpayPublicKey;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}

package org.pay.polymer.provider.payment.tpp.wechat.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.pay.polymer.common.tpp.wechat.WechatConstants.*;
import static org.pay.polymer.common.util.NumberEnum.SUCCESS;
import static org.pay.polymer.common.util.NumberEnum.*;
import static org.pay.polymer.provider.payment.tpp.wechat.api.internal.util.WechatpayHttp.requestWithCert;
import static org.pay.polymer.provider.payment.tpp.wechat.api.internal.util.WechatpayHttp.requestWithoutCert;
import static org.pay.polymer.provider.payment.tpp.wechat.api.internal.util.WechatpayLogger.*;
import static org.pay.polymer.provider.payment.tpp.wechat.api.internal.util.WechatpayUtil.generateSignedXml;
import static org.pay.polymer.provider.payment.tpp.wechat.api.internal.util.WechatpayUtil.xmlToMap;

 
/**
 * <p>
 * 微信客户端sdk执行
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public abstract class AbstractWechatpayClient implements WechatpayClient {

    private static Logger logger = LoggerFactory.getLogger(AbstractWechatpayClient.class);


    @Override
    public <T extends WechatpayResponse> T execute(WechatpayRequest<T> request) throws WechatpayApiException {
        //开始时间
        long beginTime = System.currentTimeMillis();
        //返回xml格式
        String requestXML = getRequestXmlWithSign(request);
        String respXml = null;
        //商户号
        String mchId = request.getTextParams().get(MCH_ID);
        T rsp;
        //反射响应类型
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new WechatpayApiException(e);
        }
        //处理需要证书的接口 (撤销和退款接口都需要证书)
        if (REVERSE_URL_SUFFIX.equals(request.getServerUrl()) ||
                REFUND_URL_SUFFIX.equals(request.getServerUrl())) {
            //处理证书
            try (InputStream is = new FileInputStream(request.getCertPath())) {
                //调用微信接口
                respXml = requestWithCert(request.getServerUrl(), mchId, requestXML, is);
            } catch (FileNotFoundException e) {
                logger.info("商户号：{}，证书路径找不到：{}", mchId, request.getCertPath());
            } catch (Exception e) {
                logErrorMsg(mchId, System.currentTimeMillis() - beginTime,
                        request.getTextParams(), request.getServerUrl(), "微信退款或撤销接口失败");
                throw new WechatpayApiException(e);
            }
        } else {
            try {
                //调用微信接口
                respXml = requestWithoutCert(request.getServerUrl(), mchId, requestXML);
            } catch (Exception e) {
                logErrorMsg(mchId, System.currentTimeMillis() - beginTime,
                        request.getTextParams(), request.getServerUrl(), "微信支付或查询接口失败");
                throw new WechatpayApiException(e);
            }
        }
        //判断是否为空.如果为空直接返回
        if (StringUtils.isEmpty(respXml)) {
            rsp.setSubCode(ERROR_CODE.getCode());
            rsp.setSubMsg(ERROR_CODE.getName());
            return rsp;
        }
        //设置参数
        rsp.setParams(request.getTextParams());
        //结束时间
        long endTime = System.currentTimeMillis();
        //返回不成功
        if (!respXml.contains(SUCCESS.getCode())) {
            rsp.setCode(FAIL_CODE.getCode());
            logErrorScene(respXml, rsp, endTime - beginTime);
        } else {
            rsp.setCode(SUCCESS_CODE.getCode());
            logBizSummary(respXml, rsp, endTime - beginTime);
        }
        //添加到body中
        JSONObject result = JSONObject.parseObject(JSON.toJSONString(xmlToMap(respXml)));
        //返回码
        result.put(CODE, rsp.getCode());
        //返回信息
        result.put(MSG, result.get(RETURN_MSG));
        rsp.setBody(result.toString());
        return rsp;
    }


    /**
     * 包装并进行签名操作
     *
     * @param request 请求信息
     * @return 返回请求xml格式
     * @author Mr.Yang
     * @date 2018/11/3
     */
    private String getRequestXmlWithSign(WechatpayRequest<?> request) {
        //进行签名
        try {
            Map<String, String> hashMap = request.getTextParams();
            if (hashMap.containsKey(TOTAL_FEE)) {
                //处理金额
                hashMap.put(TOTAL_FEE, new BigDecimal(hashMap.get(TOTAL_FEE))
                        .multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
            }
            //添加随机数
            hashMap.put(NONCE_STR, UUID.randomUUID().toString().substring(0, 30));
            return generateSignedXml(hashMap, request.getKey());
        } catch (Exception e) {
            logger.error("微信组装签名时异常", e);
        }
        return null;
    }
}

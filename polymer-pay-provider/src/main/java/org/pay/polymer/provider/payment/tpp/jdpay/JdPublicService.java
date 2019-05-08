package org.pay.polymer.provider.payment.tpp.jdpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.provider.payment.tpp.jdpay.api.DefaultJdpayClient;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayApiException;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayClient;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayResponse;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.BASE64;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.MD5;
import org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util.WebUtils;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeCancelRequest;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeQueryRequest;
import org.pay.polymer.provider.payment.tpp.jdpay.api.request.JdpayTradeRefundRequest;
import org.pay.polymer.provider.payment.tpp.jdpay.api.response.JdpayDataDataserviceBillDownloadurlQueryResponse;
import org.pay.polymer.provider.payment.tpp.jdpay.api.response.JdpayTradeCancelResponse;
import org.pay.polymer.provider.payment.tpp.jdpay.api.response.JdpayTradeQueryResponse;
import org.pay.polymer.provider.payment.tpp.jdpay.api.response.JdpayTradeRefundResponse;
import org.pay.polymer.provider.prepare.InitConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.*;
import static org.pay.polymer.common.util.NumberEnum.ERROR_CODE;
import static org.pay.polymer.common.util.NumberEnum.SUCCESS_CODE;
import static org.pay.polymer.common.util.PayUtils.responsePayCode;


/**
 * <p>
 * 京东公共服务类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class JdPublicService {

    private static Logger logger = LoggerFactory.getLogger(JdPublicService.class);

    private JdPublicService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 京东客户端实例与回调组合的map
     *
     * @param clientParams 初始化客户端请求
     * @return 返回京东客户端实例与回调组合的map
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static Map<String, String> spliceClientMap(JSONObject clientParams) {
        //获取已经初始化后的所有渠道实例
        Map<String, Map<String, String>> jdpayMap = InitConfigure.getJdpayMap();
        //获取指定某个实例
        return jdpayMap.get(clientParams.getString(MERCHANT));
    }


    /**
     * 获取客户端查询实例
     *
     * @param clientParams 初始化客户端请求
     * @param serviceUrl   接口地址
     * @return 返回京东客户端实例
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static JdpayClient spliceClient(JSONObject clientParams, String serviceUrl) {
        //获取已经初始化后的所有渠道实例
        Map<String, Map<String, String>> jdpayMap = InitConfigure.getJdpayMap();
        //获取指定某个实例参数
        JSONObject payParams = JSONObject.parseObject(jdpayMap
                .get(clientParams.getString(MERCHANT))
                .get(JD_PAY_SDK));
        //实例化
        return new DefaultJdpayClient(serviceUrl, payParams.getString(MERCHANT),
                payParams.getString(JDPAY_PUBLIC_KEY), payParams.getString(DES_KEY),
                payParams.getString(PRIVATE_KEY), payParams.getString(CHARSET));
    }

    /**
     * 支付查询信息
     *
     * @param payQuery 支付查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static JdpayTradeQueryResponse payQuery(PayMent payQuery) throws JdpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payQuery));
        //获取客户端实例
        JdpayClient jdpayClient = spliceClient(payJson, QUERY);
        //创建查询请求
        JdpayTradeQueryRequest request = new JdpayTradeQueryRequest();
        //封装业务信息
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return jdpayClient.execute(request);
    }

    /**
     * 支付退款操作
     *
     * @param payRefund 支付退款
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static JdpayTradeRefundResponse payRefund(PayMent payRefund) throws JdpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payRefund));
        //获取客户端实例
        JdpayClient jdpayClient = spliceClient(payJson, REFUND);
        //创建查询请求
        JdpayTradeRefundRequest request = new JdpayTradeRefundRequest();
        //填充值
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //货币种类
        if (!bizContent.containsKey(FIELD_CURRENCY_TYPE)) {
            bizContent.put(FIELD_CURRENCY_TYPE, CURRENCY_TYPE);
        }
        //封装业务信息
        request.setBizContent(bizContent.toString());
        //执行请求信息
        return jdpayClient.execute(request);
    }


    /**
     * 下载对账单
     *
     * @param filePath 下载路径
     * @param download 下载数据
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static JdpayDataDataserviceBillDownloadurlQueryResponse payDownloadurl(PayMent download, String filePath) throws JdpayApiException {
        JdpayDataDataserviceBillDownloadurlQueryResponse response = new JdpayDataDataserviceBillDownloadurlQueryResponse();
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(download));
        //获取客户端实例
        //获取指定某个实例参数
        Map<String, String> payParams = InitConfigure.getJdpayMap()
                .get(payJson.getString(MERCHANT));
        //业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        StringBuilder filename = new StringBuilder();
        filename.append(bizContent.getString(BILL_DATE));
        //具体格式请参考对账接口说明文档
        String path = "0001/0001";
        //1 根据订单创建时间生成的交易流水。 3根据订单结算时间生成的交易流水
        //0001 根据订单创建时间生成的交易流水。 0003 根据订单结算时间生成的交易流水。
        //如一天的交易金额未超过最低结算金额将不结算，当然此文件也会为空。金额累积到超过最低结算金额后生成结算文件。
        if (bizContent.getIntValue(BILL_TYPE) == NUMBER_ONE) {
            filename.append("ordercreate_");
        } else {
            filename.append("accountwater_");
            path = "0001/0003";
        }
        //对账下载文件名，格式：日期 + 对账类型_ + 二级商户号.zip
        filename.append(payJson.getString(MERCHANT)).append(".zip");
        //请求参数信息
        String data = "{'name':'" + filename + "','path':'" + path + "'}";
        try {
            //data进行BASE64
            data = BASE64.encode(data.getBytes());
            String md5 = MD5.md5(data + payParams.get(MD5_KEY), "");
            Map<String, String> params = new HashMap<>();
            params.put("md5", md5);
            params.put("data", data);
            //owner为 商户号（8位或9位）
            params.put("owner", MERCHANT);
            JSONObject result = JSONObject.parseObject(WebUtils.download(DOWNLOAD, params,
                    5000, 10000, filePath + File.separator + filename));
            //是否已经下载到本地
            if (SUCCESS_CODE.getCode().equals(result.getString(CODE))) {
                result.put(BILL_DOWNLOAD_URL, "http://...............");
            }
            //设置返回
            response.setBody(result.toJSONString());
            //返回
            return response;
        } catch (Exception e) {
            logger.error("下载对账单失败", e);
            JSONObject outcome = new JSONObject();
            outcome.put(CODE, ERROR_CODE.getCode());
            outcome.put(MSG, ERROR_CODE.getName());
            //设置返回
            response.setBody(outcome.toJSONString());
            //返回
            return response;
        }
    }


    /**
     * 交易撤销接口
     *
     * @param cancel 交易撤销数据
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static JdpayTradeCancelResponse payCancel(PayMent cancel) throws JdpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(cancel));
        //获取客户端实例
        JdpayClient jdpayClient = spliceClient(payJson, REVOKE);
        //创建查询请求
        JdpayTradeCancelRequest request = new JdpayTradeCancelRequest();
        //业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //货币种类
        if (!bizContent.containsKey(FIELD_CURRENCY_TYPE)) {
            bizContent.put(FIELD_CURRENCY_TYPE, CURRENCY_TYPE);
        }
        //封装业务信息
        request.setBizContent(bizContent.toString());
        //执行请求信息
        return jdpayClient.execute(request);
    }

    /**
     * 返回新的map
     *
     * @param map 请求参数
     * @return 返回重新的map
     * @author Mr.Yang
     * @date 2018/11/4
     */
    static Map<String, String> jsonToMap(Map<String, String> map) {
        return JSON.parseObject(
                map.get(JD_PAY_SDK), new TypeReference<Map<String, String>>() {
                });
    }

    /**
     * 处理响应数据
     *
     * @param response 响应请求
     * @return 返回
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    public static String processReponseResult(JdpayResponse response) {
        //处理返回信息
        return responsePayCode(response.getBody());
    }

}

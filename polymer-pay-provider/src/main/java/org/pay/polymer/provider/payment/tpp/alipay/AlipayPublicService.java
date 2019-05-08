package org.pay.polymer.provider.payment.tpp.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.provider.prepare.InitConfigure;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.pay.polymer.common.tpp.alipay.AlipayConstants.*;
import static org.pay.polymer.common.util.NumberEnum.ERROR_CODE;
import static org.pay.polymer.common.util.NumberEnum.SUCCESS_CODE;
import static org.pay.polymer.common.util.PayUtils.responsePayCode;


/**
 * <p>
 * 支付宝公共服务类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class AlipayPublicService {

    private AlipayPublicService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 支付宝客户端实例与回调组合的map
     *
     * @param clientParams 初始化客户端请求
     * @return 返回支付宝客户端实例与回调组合的map
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static Map<String, Object> spliceClientMap(JSONObject clientParams) {
        //获取已经初始化后的所有渠道实例
        Map<String, Map<String, Object>> alipayMap = InitConfigure.getAlipayMap();
        //获取指定某个实例
        return alipayMap.get(clientParams.getString(APP_ID));
    }


    /**
     * 获取客户端实例
     *
     * @param clientParams 初始化客户端请求
     * @return 返回支付宝客户端实例
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayClient spliceClient(JSONObject clientParams) {
        //获取已经初始化后的所有渠道实例
        Map<String, Map<String, Object>> alipayMap = InitConfigure.getAlipayMap();
        //获取指定某个实例
        return (DefaultAlipayClient) alipayMap
                .get(clientParams.getString(APP_ID))
                .get(ALI_PAY_SDK);
    }

    /**
     * 支付查询信息
     *
     * @param payQuery 支付查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayTradeQueryResponse payQuery(PayMent payQuery) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payQuery));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }

    /**
     * 支付退款操作
     *
     * @param payRefund 支付退款
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayTradeRefundResponse payRefund(PayMent payRefund) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payRefund));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }


    /**
     * 退款查询
     *
     * @param payRefundQuery 退款查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayTradeFastpayRefundQueryResponse payRefundQuery(PayMent payRefundQuery) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payRefundQuery));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }


    /**
     * 交易关闭
     *
     * @param payClose 交易关闭
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayTradeCloseResponse payClose(PayMent payClose) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payClose));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }


    /**
     * 下载账单信息
     *
     * @param download 下载账单信息
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayDataDataserviceBillDownloadurlQueryResponse payDownload(PayMent download) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(download));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }

    /**
     * 支付宝订单信息同步接口
     *
     * @param sync 同步数据
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayTradeOrderinfoSyncResponse paySync(PayMent sync) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(sync));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayTradeOrderinfoSyncRequest request = new AlipayTradeOrderinfoSyncRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }


    /**
     * 统一收单交易撤销接口
     *
     * @param cancel 交易撤销数据
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static AlipayTradeCancelResponse payCancel(PayMent cancel) throws AlipayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(cancel));
        //获取客户端实例
        AlipayClient alipayClient = spliceClient(payJson);
        //创建查询请求
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizContent(payJson.getString(BIZ_CONTENT_KEY));
        //执行请求信息
        return alipayClient.execute(request);
    }

    /**
     * 处理响应结果
     *
     * @param response 响应信息
     * @return 返回结果信息
     * @author Mr.Yang
     * @date 2019/3/6 0006
     */
    public static String processResponseResult(AlipayResponse response) {
        //处理返回信息
        return responsePayCode(response.getBody());
    }
}

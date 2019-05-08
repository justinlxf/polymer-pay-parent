package org.pay.polymer.provider.payment.tpp.wechat;

import com.alibaba.fastjson.JSONObject;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.tpp.wechat.Wechatpay;
import org.pay.polymer.provider.payment.tpp.wechat.api.DefaultWechatpayClient;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayApiException;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayClient;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayResponse;
import org.pay.polymer.provider.payment.tpp.wechat.api.request.*;
import org.pay.polymer.provider.payment.tpp.wechat.api.response.*;
import org.pay.polymer.provider.prepare.InitConfigure;

import java.util.Map;

import static org.pay.polymer.common.tpp.wechat.WechatConstants.*;
import static org.pay.polymer.common.util.PayUtils.responsePayCode;


/**
 * <p>
 * 微信公共服务类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class WechatPublicService {

    private WechatPublicService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 微信客户端实例与回调组合的map
     *
     * @param clientParams 初始化客户端请求
     * @return 返回微信配置实例化
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static Wechatpay spliceClient(JSONObject clientParams) {
        //获取已经初始化后的所有渠道实例
        Map<String, Wechatpay> wechatpayMap = InitConfigure.getWechatpayMap();
        //获取指定某个实例
        return wechatpayMap.get(clientParams.getString(WECHAT_APPID));
    }

    /**
     * 支付查询信息
     *
     * @param payQuery 支付查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static WechatpayTradeQueryResponse payQuery(PayMent payQuery) throws WechatpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payQuery));
        //获取客户端实例
        Wechatpay wechat = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //公众号id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户号
        bizContent.put(MCH_ID, wechat.getMch_id());
        //创建查询请求
        WechatpayTradeQueryRequest request = new WechatpayTradeQueryRequest(
                ORDERQUERY_URL_SUFFIX, wechat.getKey(), bizContent.toJSONString());
        WechatpayClient client = new DefaultWechatpayClient();
        try {
            //返回结果
            return client.execute(request);
        } catch (WechatpayApiException e) {
            throw new WechatpayApiException(e);
        }
    }

    /**
     * 支付退款操作
     *
     * @param payRefund 支付退款
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static WechatpayTradeRefundResponse payRefund(PayMent payRefund) throws WechatpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payRefund));
        //获取客户端实例
        Wechatpay wechat = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //公众号id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户号
        bizContent.put(MCH_ID, wechat.getMch_id());
        //创建退款请求
        WechatpayTradeRefundRequest request = new WechatpayTradeRefundRequest(
                REFUND_URL_SUFFIX, wechat.getKey(), bizContent.toJSONString(), wechat.getCertPath());
        WechatpayClient client = new DefaultWechatpayClient();
        try {
            //返回结果
            return client.execute(request);
        } catch (WechatpayApiException e) {
            throw new WechatpayApiException(e);
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
    static WechatpayTradeReverseResponse payReverse(PayMent cancel) throws WechatpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(cancel));
        //获取客户端实例
        Wechatpay wechat = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //公众号id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户号
        bizContent.put(MCH_ID, wechat.getMch_id());
        //创建撤销请求
        WechatpayTradeReverseRequest request = new WechatpayTradeReverseRequest(
                REVERSE_URL_SUFFIX, wechat.getKey(), bizContent.toJSONString(), wechat.getCertPath());
        WechatpayClient client = new DefaultWechatpayClient();
        try {
            //返回结果
            return client.execute(request);
        } catch (WechatpayApiException e) {
            throw new WechatpayApiException(e);
        }
    }

    /**
     * 退款查询
     *
     * @param query 退款查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static WechatpayTradeRefundQueryResponse payRefundQuery(PayMent query) throws WechatpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(query));
        //获取客户端实例
        Wechatpay wechat = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //公众号id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户号
        bizContent.put(MCH_ID, wechat.getMch_id());
        //创建关闭请求
        WechatpayTradeRefundQueryRequest request = new WechatpayTradeRefundQueryRequest(
                REFUNDQUERY_URL_SUFFIX, wechat.getKey(), bizContent.toJSONString());
        WechatpayClient client = new DefaultWechatpayClient();
        try {
            //返回结果
            return client.execute(request);
        } catch (WechatpayApiException e) {
            throw new WechatpayApiException(e);
        }
    }

    /**
     * 交易关单接口
     *
     * @param close 交易关单数据
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static WechatpayTradeCloseorderResponse payClose(PayMent close) throws WechatpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(close));
        //获取客户端实例
        Wechatpay wechat = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //公众号id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户号
        bizContent.put(MCH_ID, wechat.getMch_id());
        //创建关闭请求
        WechatpayTradeCloseorderRequest request = new WechatpayTradeCloseorderRequest(
                CLOSEORDER_URL_SUFFIX, wechat.getKey(), bizContent.toJSONString());
        WechatpayClient client = new DefaultWechatpayClient();
        try {
            //返回结果
            return client.execute(request);
        } catch (WechatpayApiException e) {
            throw new WechatpayApiException(e);
        }
    }

    /**
     * 下载对账单
     *
     * @param bill 下载对账单
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static WechatpayTradeDownloadbillResponse payDownloadBill(PayMent bill) throws WechatpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(bill));
        //获取客户端实例
        Wechatpay wechat = spliceClient(payJson);
        //获取业务填充
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //公众号id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户号
        bizContent.put(MCH_ID, wechat.getMch_id());
        //创建关闭请求
        WechatpayTradeDownloadbillRequest request = new WechatpayTradeDownloadbillRequest(
                REFUNDQUERY_URL_SUFFIX, wechat.getKey(), bizContent.toJSONString());
        WechatpayClient client = new DefaultWechatpayClient();
        try {
            //返回结果
            return client.execute(request);
        } catch (WechatpayApiException e) {
            throw new WechatpayApiException(e);
        }
    }

    /**
     * 处理响应结果
     *
     * @param response 响应信息
     * @return 返回结果信息
     * @author Mr.Yang
     * @date 2019/3/6 0006
     */
    public static String processResponseResult(WechatpayResponse response) {
        //处理返回信息
        return responsePayCode(response.getBody());
    }

    /**
     * 转换请求参数
     *
     * @param payJson 支付请求对象
     * @param wechat  微信实例化
     * @return 返回支付参数json
     * @author Mr.Yang
     * @date 2019/3/28 0028
     */
    public static JSONObject convertParams(JSONObject payJson, Wechatpay wechat) {
        //获取业务填充值
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //是否包含回调信息,如果不包含则使用配置的回调地址
        if (!bizContent.containsKey(NOTIFY_URL)) {
            bizContent.put(NOTIFY_URL, wechat.getNotify_url());
        }
        //商户id
        bizContent.put(WECHAT_APPID, wechat.getAppid());
        //商户id
        bizContent.put(MCH_ID, wechat.getMch_id());
        return bizContent;
    }
}

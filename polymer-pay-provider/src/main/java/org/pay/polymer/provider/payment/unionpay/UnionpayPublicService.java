package org.pay.polymer.provider.payment.unionpay;

import com.alibaba.fastjson.JSONObject;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.api.unionpay.Unionpay;
import org.pay.polymer.provider.payment.unionpay.api.DefaultUnionpayClient;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayApiException;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayClient;
import org.pay.polymer.provider.payment.unionpay.api.UnionpayResponse;
import org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.SDKConstants;
import org.pay.polymer.provider.payment.unionpay.api.request.UnionpayTradeCancelRequest;
import org.pay.polymer.provider.payment.unionpay.api.request.UnionpayTradeDownloadbillRequest;
import org.pay.polymer.provider.payment.unionpay.api.request.UnionpayTradeQueryRequest;
import org.pay.polymer.provider.payment.unionpay.api.request.UnionpayTradeRefundRequest;
import org.pay.polymer.provider.payment.unionpay.api.response.UnionpayTradeCancelResponse;
import org.pay.polymer.provider.payment.unionpay.api.response.UnionpayTradeDownloadbillResponse;
import org.pay.polymer.provider.payment.unionpay.api.response.UnionpayTradeQueryResponse;
import org.pay.polymer.provider.payment.unionpay.api.response.UnionpayTradeRefundResponse;
import org.pay.polymer.provider.prepare.InitConfigure;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

import static org.pay.polymer.common.unionpay.UnionpayConstants.*;
import static org.pay.polymer.common.util.NumberEnum.ERROR_CODE;
import static org.pay.polymer.common.util.NumberEnum.SUCCESS_CODE;


/**
 * <p>
 * 银联公共服务类
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public class UnionpayPublicService {

    private UnionpayPublicService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 银联客户端实例与回调组合的map
     *
     * @param clientParams 初始化客户端请求
     * @return 返回银联客户端实例
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static Unionpay spliceClient(JSONObject clientParams) {
        //获取已经初始化后的所有渠道实例
        Map<String, Unionpay> unionpay = InitConfigure.getUnionpayMap();
        //获取指定某个实例
        return unionpay.get(clientParams.getString(FIELD_MERID));
    }


    /**
     * 支付撤销
     *
     * @param payRefund 支付退款
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static UnionpayTradeCancelResponse payCancel(PayMent payRefund) throws UnionpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payRefund));
        //业务填充内容
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //请求参数改变
        splicingParameter(payJson.getString(FIELD_REFUND_TYPE), bizContent);
        //交易类型 31-撤销
        bizContent.put(FIELD_TXN_TYPE, TXN_TYPE_THIRTY_ONE);
        //创建退款请求
        UnionpayTradeCancelRequest request = new UnionpayTradeCancelRequest(SDKConstants.GATEWAY_BACK_TRANS_REQ
                , bizContent.toString());
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(spliceClient(payJson));
        //执行请求信息
        try {
            return client.execute(request);
        } catch (UnionpayApiException e) {
            throw new UnionpayApiException(e);
        }
    }


    /**
     * 冲正操作
     *
     * @param deacidize 冲正
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static UnionpayTradeRefundResponse payDeacidize(PayMent deacidize) throws UnionpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(deacidize));
        //业务填充内容
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //请求参数改变
        //渠道类型
        bizContent.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_WAP);
        //业务类型
        bizContent.put(FIELD_BIZ_TYPE, BIZ_TYPE_ZERO);
        //交易类型 99-冲正
        bizContent.put(FIELD_TXN_TYPE, TXN_TYPE_NINETY_NINTH);
        //交易子类型
        bizContent.put(FIELD_TXN_SUB_TYPE, TXN_SUB_TYPE_ONE);
        //创建退款请求
        UnionpayTradeRefundRequest request = new UnionpayTradeRefundRequest(SDKConstants.GATEWAY_BACK_TRANS_REQ
                , bizContent.toString());
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(spliceClient(payJson));
        //执行请求信息
        try {
            return client.execute(request);
        } catch (UnionpayApiException e) {
            throw new UnionpayApiException(e);
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
    static UnionpayTradeRefundResponse payRefund(PayMent payRefund) throws UnionpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payRefund));
        //业务填充内容
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //请求参数改变
        splicingParameter(payJson.getString(FIELD_REFUND_TYPE), bizContent);
        bizContent.put(FIELD_BIZ_TYPE,BIZ_TYPE_THREE);
        //创建退款请求
        UnionpayTradeRefundRequest request = new UnionpayTradeRefundRequest(SDKConstants.GATEWAY_BACK_TRANS_REQ
                , bizContent.toString());
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(spliceClient(payJson));
        //执行请求信息
        try {
            return client.execute(request);
        } catch (UnionpayApiException e) {
            throw new UnionpayApiException(e);
        }
    }

    /**
     * 根据不同类型进行参数改变
     *
     * @param type       page 电脑 wap手机网页 app手机 qrcode二维码
     * @param bizContent 业务填充
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public static void splicingParameter(String type, JSONObject bizContent) {
        //交易类型 04-退货
        bizContent.put(FIELD_TXN_TYPE, TXN_TYPE_FOUR);
        //交易子类型  默认00
        bizContent.put(FIELD_TXN_SUB_TYPE, TXN_SUB_TYPE_ZERO);
        //业务类型
        bizContent.put(FIELD_BIZ_TYPE, BIZ_TYPE);
        //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
        bizContent.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_PC);
        //退款类型 page 电脑 wap手机网页 app手机 qrcode二维码
        switch (type) {
            case UNIONPAY_TYPE_WAP_TYPE:
                break;
            case UNIONPAY_TYPE_APP_TYPE:
                bizContent.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_WAP);
                break;
            case UNIONPAY_TYPE_QR_CODE_TYPE:
                //渠道类型
                bizContent.put(FIELD_CHANNEL_TYPE, CHANNEL_TYPE_WAP);
                //业务类型
                bizContent.put(FIELD_BIZ_TYPE, BIZ_TYPE_ZERO);
                break;
            default:
                break;
        }
        //交易币种（境内商户一般是156 人民币）
        bizContent.put(FIELD_CURRENCY_CODE, CURRENCY_CODE);
        //交易金额，单位分，不要带小数点
        bizContent.put(FIELD_TXN_AMT,
                new BigDecimal(bizContent.getString(FIELD_TXN_AMT)).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
    }


    /**
     * 在线网关支付查询信息
     *
     * @param payQuery 支付查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static UnionpayTradeQueryResponse payOnlineQuery(PayMent payQuery) throws UnionpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payQuery));
        //业务填充内容
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //交易类型 00-默认
        bizContent.put(FIELD_TXN_TYPE, TXN_TYPE_ZERO);
        //交易子类型  默认00
        bizContent.put(FIELD_TXN_SUB_TYPE, TXN_SUB_TYPE_ZERO);
        //业务类型，B2C网关支付，手机wap支付
        bizContent.put(FIELD_BIZ_TYPE, BIZ_TYPE);
        //创建查询请求
        UnionpayTradeQueryRequest request = new UnionpayTradeQueryRequest(SDKConstants.GATEWAY_QUERY_TRANS
                , bizContent.toString());
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(spliceClient(payJson));
        //执行请求信息
        try {
            return client.execute(request);
        } catch (UnionpayApiException e) {
            throw new UnionpayApiException(e);
        }
    }


    /**
     * 银联对账单下载
     *
     * @param payQuery 支付查询
     * @return 返回响应数据
     * @author Mr.Yang
     * @date 2018/10/28
     */
    static UnionpayTradeDownloadbillResponse payDownloadbill(PayMent payQuery) throws UnionpayApiException {
        //转换为fastjson
        JSONObject payJson = JSONObject.parseObject(JSONObject.toJSONString(payQuery));
        //业务填充内容
        JSONObject bizContent = payJson.getJSONObject(BIZ_CONTENT_KEY);
        //交易类型  76-对账文件下载
        bizContent.put(FIELD_TXN_TYPE, TXN_TYPE_SEVENTY_SIX);
        //交易子类型 01-对账文件下载
        bizContent.put(FIELD_TXN_SUB_TYPE, TXN_SUB_TYPE_ONE);
        //业务类型，固定000000
        bizContent.put(FIELD_BIZ_TYPE, BIZ_TYPE_ZERO);
        //文件类型，一般商户填写00即可
        bizContent.put(FIELD_FILE_TYPE, TXN_SUB_TYPE_ZERO);
        //创建查询请求
        UnionpayTradeDownloadbillRequest request = new UnionpayTradeDownloadbillRequest(SDKConstants.FILE_DOWNLOAD
                , bizContent.toString());
        //客户端实例
        UnionpayClient client = new DefaultUnionpayClient(spliceClient(payJson));
        //执行请求信息
        try {
            return client.execute(request);
        } catch (UnionpayApiException e) {
            throw new UnionpayApiException(e);
        }
    }

    /**
     * 处理响应数据
     *
     * @param response 响应请求
     * @return 返回
     * @author Mr.Yang
     * @date 2019/3/7 0007
     */
    public static String processReponseResult(UnionpayResponse response) {
        JSONObject outcome = new JSONObject();
        outcome.put(FORM, response.getBody());
        //是否成功
        if (!StringUtils.isEmpty(outcome.getString(FORM))) {
            outcome.put(CODE, SUCCESS_CODE.getCode());
            outcome.put(MSG, SUCCESS_CODE.getName());
            return outcome.toJSONString();
        }
        outcome.put(CODE, ERROR_CODE.getCode());
        outcome.put(MSG, ERROR_CODE.getName());
        return outcome.toJSONString();
    }
}

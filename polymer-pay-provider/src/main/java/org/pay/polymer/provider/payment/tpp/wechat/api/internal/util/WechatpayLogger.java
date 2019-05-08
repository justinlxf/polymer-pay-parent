package org.pay.polymer.provider.payment.tpp.wechat.api.internal.util;

import org.pay.polymer.common.tpp.jdpay.JdpayConstants;
import org.pay.polymer.provider.payment.tpp.wechat.api.WechatpayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * 客户端日志
 * 通讯错误格式：time^_^api^_^app^_^ip^_^os^_^sdk^_^url^responseCode
 * 业务错误格式：time^_^response
 */
public class WechatpayLogger {

    private static Logger logger = LoggerFactory.getLogger(WechatpayLogger.class);
    /**
     * 处理操作
     *
     * @param mchid     商户号
     * @param time      处理时间
     * @param params    请求参数
     * @param serverUrl 接口地址
     * @param msg       异常信息 异常信息
     * @author Mr.Yang
     * @date 2018/11/17
     */
    public static void logErrorMsg(String mchid, long time, Map<String, String> params, String serverUrl, String msg) {
        //输出日志信息
        logger.info("{}^_^{}^_^{}^_^{}^_^{}", mchid, time, params, serverUrl, msg);
    }


    /**
     * 发生特别错误时记录完整错误现场
     */
    public static void logErrorScene(String resultXml, WechatpayResponse tRsp,
                                     long reqTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(JdpayConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorScene");
        sb.append("^_^");
        sb.append(tRsp.getCode());
        sb.append("^_^");
        sb.append(tRsp.getSubCode());
        sb.append("^_^");
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append("Body:");
        sb.append(resultXml);
        sb.append("^_^");
        sb.append("ApplicationParams:");
        appendLog((WechatpayHashMap) tRsp.getParams(), sb);
        sb.append("^_^");
        sb.append(reqTime);
        sb.append("ms");
        logger.error(sb.toString());
    }

    /**
     * 发生特别错误时记录完整错误现场
     *
     * @param resultXmlData 返回参数
     * @param tRsp          响应实体
     * @param reqTime       请求时间
     * @author Mr.Yang
     * @date 2018/11/17
     */
    public static void logBizSummary(String resultXmlData, WechatpayResponse tRsp,
                                     long reqTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("Summary");
        sb.append("^_^");
        sb.append(tRsp.getCode());
        sb.append("^_^");
        sb.append(tRsp.getSubCode());
        sb.append("^_^");
        sb.append("Body:");
        sb.append(resultXmlData);
        sb.append("^_^");
        sb.append("ApplicationParams:");
        appendLog((WechatpayHashMap) tRsp.getParams(), sb);
        sb.append("^_^");
        sb.append(reqTime);
        sb.append("ms,");
        logger.info("{}", sb.toString());
    }


    /**
     * 拼接
     *
     * @param map 请求参数
     * @param sb  buffer
     * @author Mr.Yang
     * @date 2018/11/17
     */
    private static void appendLog(WechatpayHashMap map, StringBuilder sb) {
        boolean first = true;
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            if (!first) {
                sb.append("&");
            } else {
                first = false;
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
    }

}

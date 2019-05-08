package org.pay.polymer.provider.annotation;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.pay.polymer.api.PayMent;
import org.pay.polymer.common.tpp.alipay.AlipayConstants;
import org.pay.polymer.common.tpp.jdpay.JdpayConstants;
import org.pay.polymer.common.tpp.wechat.WechatConstants;
import org.pay.polymer.common.unionpay.UnionpayConstants;
import org.pay.polymer.common.util.PayMentConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Objects;

import static org.pay.polymer.common.util.PaymentLogFile.logPayMentResult;

/**
 * @program: polymer-pay-provider
 * @description: 注解aop处理支付日志
 * @author: Mr.Yang
 * @create: 2018-10-28 14:04
 **/
@Aspect
@Component
@Order(-1)
public class PaymentLogAspect {

    private static Logger logger = LoggerFactory.getLogger(PaymentLogAspect.class);

    /**
     * 环境服务
     */
    @Autowired
    private Environment environment;

    private boolean isWrite = false;

    private String filePath;

    /**
     * 初始化
     *
     * @author Mr.Yang
     * @date 2018/11/17
     */
    @PostConstruct
    void init() {
        //获取是否可以写入标识
        isWrite = Boolean.valueOf(environment.getProperty(PayMentConstants.POLYMER_LOG_WRITE));
        //获取是否可以写入
        if (isWrite) {
            //文件路径
            filePath = Objects.requireNonNull(environment.getProperty(PayMentConstants.POLYMER_LOG_PAY));
            //获取路径
            File file = new File(filePath);
            if (!file.exists() && file.mkdirs()) {
                logger.info("支付网关统一日志路径创建成功");
            }
        }
    }

    /**
     * 前置日志处理
     *
     * @param paymentLogMnager 拦截注解
     * @param payMent          请求参数
     * @author Mr.Yang
     * @date 2018/11/17
     */
    @Before("@annotation(paymentLogMnager) && args(payMent)")
    public void beforeLog(PaymentLogMnager paymentLogMnager, PayMent payMent) {
        //日志转换
        String logStr = String.format("Polymer^_^%s^_^%s^_^%s^_^Start^_^%s", paymentLogMnager.group(), paymentLogMnager.type(), paymentLogMnager.module(), JSONObject.toJSONString(payMent));
        //输出日志
        logger.info(logStr);
        //写入日志
        writeLog(paymentLogMnager, logStr);
    }


    /**
     * 后置日志处理
     *
     * @param paymentLogMnager 拦截注解
     * @param result           返回信息
     * @author Mr.Yang
     * @date 2018/11/17
     */
    @AfterReturning(value = "@annotation(paymentLogMnager)", returning = "result")
    public void afterLog(PaymentLogMnager paymentLogMnager, Object result) {
        //日志转换
        String logStr = String.format("Polymer^_^%s^_^%s^_^%s^_^End^_^%s", paymentLogMnager.group(), paymentLogMnager.type(), paymentLogMnager.module(), result);
        //输出日志
        logger.info(logStr);
        //写入日志
        writeLog(paymentLogMnager, logStr);
    }

    /**
     * 日志写入
     *
     * @param paymentLogMnager 拦截注解
     * @param logStr           日志信息
     * @author Mr.Yang
     * @date 2018/11/17
     */
    private void writeLog(PaymentLogMnager paymentLogMnager, String logStr) {
        //支付日志名称
        String fileName = null;
        //支付日志平台分组
        switch (paymentLogMnager.group()) {
            //支付宝
            case AlipayConstants.ALI_PAY:
                fileName = "alipay_log";
                break;
            //微信支付
            case WechatConstants.WECHAT_PAY:
                fileName = "wechatpay_log";
                break;
            //银联支付
            case UnionpayConstants.UNION_PAY:
                fileName = "unionpay_log";
                break;
            //京东支付
            case JdpayConstants.JD_PAY:
                fileName = "jdpay_log";
                break;
            default:
                break;
        }

        if (isWrite) {
            logPayMentResult(filePath + fileName, logStr);
        }
    }
}

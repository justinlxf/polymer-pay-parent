package org.pay.polymer.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.time.LocalDate;

/**
 * @program: polymer-pay-parent
 * @description: 调用支付日志
 * @author: Mr.Yang
 * @create: 2018-11-17 11:59
 **/
public class PaymentLogFile {

    private static Logger logger = LoggerFactory.getLogger(PaymentLogFile.class);

    /**
     * 将调用信息写入文件中
     *
     * @param logFileNamePath 文件名及路径
     * @param content         内容
     * @author Mr.Yang
     * @date 2018/11/17
     */
    public static void logPayMentResult(String logFileNamePath, String content) {
        try (FileWriter writer = new FileWriter(logFileNamePath + "_" + LocalDate.now() + ".txt", true)) {
            writer.write("\n" + content.replaceAll("[\r\n]", ""));
        } catch (Exception e) {
            logger.error("支付日志写入异常", e);
        }
    }
}

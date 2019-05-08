package org.pay.polymer.common.util;

/**
 * @program: polymer-pay-provider
 * @description: 所有编号枚举
 * @author: Mr.Yang
 * @create: 2018-11-03 13:55
 **/
public enum NumberEnum {

    /**
     * 成功标记
     */
    SUCCESS("SUCCESS", "成功"),
    /**
     * 服务不可用
     */
    ERROR_CODE("20000", "业务不可用"),

    /**
     * 商户不存在
     */
    NULL_CODE("99999", "商户不存在"),

    /**
     * 验签出错, 未配置对应签名算法的公钥或者证书
     */
    SING_ERROR_CODE("40002", "验签出错, 未配置对应签名算法的公钥或者证书"),
    /**
     * 业务错误
     */
    FAIL_CODE("40004", "业务处理失败"),
    /**
     * 京东返回成功码
     */
    JDPAY_SUCCESS_CODE("000000", "成功"),
    /**
     * 银联返回成功码
     */
    UNIONPAY_SUCCESS_CODE("00", "成功"),
    /**
     * 系统自定义返回成功码
     */
    SUCCESS_CODE("10000", "成功"),

    /**
     * 系统自定义返回成功码
     */
    CUSTOM_SUCCESS_CODE("0000", "成功");


    /**
     * 状态编号
     */
    String code;

    /**
     * 状态名称
     */
    String name;

    /**
     * 构造方法
     *
     * @param code 编号
     * @param name 名称
     */
    NumberEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据编号返回内容
     *
     * @param code 编号
     * @return 返回名称
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public static String getName(String code) {
        for (NumberEnum evenEnum : NumberEnum.values()) {
            if (evenEnum.getCode().equals(code)) {
                return evenEnum.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
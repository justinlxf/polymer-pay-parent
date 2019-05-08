package org.pay.polymer.provider.payment.unionpay.api;

import java.util.Map;

/**
 * @program: polymer-pay-provider
 * @description: 请求接口
 * @author: Mr.Yang
 * @create: 2018-11-03 12:59
 **/
public interface UnionpayRequest<T extends UnionpayResponse> {


    /**
     * 获取所有的Key-Value形式的文本请求参数集合。其中：
     *
     * @return 文本请求参数集合
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public Map<String, String> getTextParams();


    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public Class<T> getResponseClass();

    /**
     * 返回接口地址
     *
     * @return 返回接口地址
     * @author Mr.Yang
     * @date 2018/11/24
     */
    public String getServerUrl();


}

package org.pay.polymer.provider.payment.tpp.jdpay.api;

import java.util.Map;


/**
 * <p>
 * 请求接口
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public interface JdpayRequest<T extends JdpayResponse> {

    /**
     * 获取所有的Key-Value形式的文本请求参数集合。其中：
     * <ul>
     * <li>Key: 请求参数名</li>
     * <li>Value: 请求参数值</li>
     * </ul>
     *
     * @return 文本请求参数集合
     */
    public Map<String, String> getTextParams();


    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    public Class<T> getResponseClass();


}

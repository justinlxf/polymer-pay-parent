package org.pay.polymer.provider.payment.tpp.wechat.api;


/**
 * <p>
 * 微信初始化sdk
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public interface WechatpayClient {


    /**
     * 调用方法(http)
     *
     * @param request<T> 请求参数
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    <T extends WechatpayResponse> T execute(WechatpayRequest<T> request) throws WechatpayApiException;
}

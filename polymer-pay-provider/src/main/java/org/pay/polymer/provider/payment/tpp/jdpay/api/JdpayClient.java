package org.pay.polymer.provider.payment.tpp.jdpay.api;


/**
 * <p>
 * 京东初始化sdk
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public interface JdpayClient {



    /**
     * 调用方法(http)
     *
     * @param request<T> 请求参数
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    <T extends JdpayResponse> T execute(JdpayRequest<T> request) throws JdpayApiException;


    /**
     * form表单提交(Post)
     *
     * @param request<T> 请求参数
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    <T extends JdpayResponse> T pageExecute(JdpayRequest<T> request) throws JdpayApiException;


    /**
     * form表单提交(POST/GET)
     *
     * @param request<T> 请求参数
     * @param method     POST/GET请求
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    public <T extends JdpayResponse> T pageExecute(JdpayRequest<T> request,
                                                   String method) throws JdpayApiException;
}

package org.pay.polymer.provider.payment.unionpay.api;


/**
 * @program:  polymer-pay-provider
 * @description: 银联初始化sdk
 * @author: Mr.Yang
 * @create: 2018-11-03 13:42
 **/
public interface UnionpayClient {



    /**
     * 调用方法(http)
     *
     * @param request<T> 请求参数
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    <T extends UnionpayResponse> T execute(UnionpayRequest<T> request) throws UnionpayApiException;


    /**
     * form表单提交(Post)
     *
     * @param request<T> 请求参数
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    <T extends UnionpayResponse> T pageExecute(UnionpayRequest<T> request) throws UnionpayApiException;


    /**
     * form表单提交(POST/GET)
     *
     * @param request<T> 请求参数
     * @param method     POST/GET请求
     * @return 返回响应
     * @author Mr.Yang
     * @date 2018/11/3
     */
    public <T extends UnionpayResponse> T pageExecute(UnionpayRequest<T> request,
                                                   String method) throws UnionpayApiException;
}

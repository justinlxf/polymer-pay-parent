package org.pay.polymer.api;

/**
 * <p>
 * 支付基类接口
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-05
 */
public interface PayMentInterface {


    /**
     * 支付查询方法
     *
     * @param payQuery 支付查询参数
     * @return 返回查询结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doPayQuery(PayMent payQuery);


    /**
     * 退款方法
     *
     * @param refund 退款参数
     * @return 返回退款结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doRefund(PayMent refund);


    /**
     * 查询退款方法
     *
     * @param refundQuery 查询退款参数
     * @return 返回查询退款结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doRefundQuery(PayMent refundQuery);


    /**
     * 交易关闭方法
     *
     * @param close 交易关闭参数
     * @return 返回交易关闭结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doClose(PayMent close);

    /**
     * 交易撤销方法
     *
     * @param cancel 交易撤销参数
     * @return 返回交易撤销结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doCancel(PayMent cancel);

    /**
     * 信息同步方法
     *
     * @param sync 信息同步参数
     * @return 返回信息同步结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String doSync(PayMent sync);

    /**
     * 查询对账单方法
     *
     * @param download 查询对账单参数
     * @return 返回查询对账单结果
     * @author Mr.Yang
     * @date 2018/10/28
     */
    String downloadurl(PayMent download);


}

package org.pay.polymer.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import static org.pay.polymer.common.tpp.wechat.WechatConstants.CODE;
import static org.pay.polymer.common.tpp.wechat.WechatConstants.MSG;
import static org.pay.polymer.common.util.NumberEnum.ERROR_CODE;
import static org.pay.polymer.common.util.NumberEnum.SUCCESS_CODE;
import static org.pay.polymer.common.util.PayMentConstants.FORM;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.Yang
 * @since 2019-03-20
 */
public class PayUtils {

    /**
     * 重新处理返回码
     *
     * @param body 支付返回信息
     * @return 返回信息
     * @author Mr.Yang
     * @date 2019/3/28 0028
     */
    public static String responsePayCode(String body) {
        JSONObject outcome = new JSONObject();
        outcome.put(FORM, body);
        //是否成功
        if (!StringUtils.isEmpty(outcome.getString(FORM))) {
            outcome.put(CODE, SUCCESS_CODE.getCode());
            outcome.put(MSG, SUCCESS_CODE.getName());
            return outcome.toJSONString();
        }
        outcome.put(CODE, ERROR_CODE.getCode());
        outcome.put(MSG, ERROR_CODE.getName());
        return outcome.toJSONString();
    }

}

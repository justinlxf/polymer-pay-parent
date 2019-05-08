package org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util;

import com.alipay.api.internal.util.StringUtils;
import org.pay.polymer.common.tpp.jdpay.JdpayConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @program: polymer-pay-provider
 * @description: 纯字符串字典结构
 * @author: Mr.Yang
 * @create: 2018-11-03 13:22
 **/
public class JdpayHashMap extends HashMap<String, String> {

    private static final long serialVersionUID = -1277791390393392630L;

    public JdpayHashMap() {
        super();
    }

    public JdpayHashMap(Map<? extends String, ? extends String> m) {
        super(m);
    }

    public String put(String key, Object value) {
        String strValue;

        if (value == null) {
            strValue = null;
        } else if (value instanceof String) {
            strValue = (String) value;
        } else if (value instanceof Integer) {
            strValue = ((Integer) value).toString();
        } else if (value instanceof Long) {
            strValue = ((Long) value).toString();
        } else if (value instanceof Float) {
            strValue = value.toString();
        } else if (value instanceof Double) {
            strValue = value.toString();
        } else if (value instanceof Boolean) {
            strValue = ((Boolean) value).toString();
        } else if (value instanceof Date) {
            DateFormat format = new SimpleDateFormat(JdpayConstants.DATE_TIME_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone(JdpayConstants.DATE_TIMEZONE));
            strValue = format.format((Date) value);
        } else {
            strValue = value.toString();
        }

        return this.put(key, strValue);
    }

    @Override
    public String put(String key, String value) {
        if (StringUtils.areNotEmpty(key, value)) {
            return super.put(key, value);
        } else {
            return null;
        }
    }
}

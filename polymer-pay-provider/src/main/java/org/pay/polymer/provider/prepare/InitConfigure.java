package org.pay.polymer.provider.prepare;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.DefaultAlipayClient;
import org.pay.polymer.api.tpp.jdpay.Jdpay;
import org.pay.polymer.api.tpp.wechat.Wechatpay;
import org.pay.polymer.api.unionpay.Unionpay;
import org.pay.polymer.common.tpp.alipay.AlipayConstants;
import org.pay.polymer.common.tpp.jdpay.JdpayConstants;
import org.pay.polymer.common.tpp.wechat.WechatConstants;
import org.pay.polymer.common.unionpay.UnionpayConstants;
import org.pay.polymer.provider.payment.tpp.alipay.Alipay;
import org.pay.polymer.provider.payment.tpp.jdpay.api.DefaultJdpayClient;
import org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk.SDKConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: polymer-pay-parent
 * @description: 初始化配置
 * @author: Mr.Yang
 * @create: 2018-10-31 21:44
 **/
@Component
public class InitConfigure {

    private static Logger logger = LoggerFactory.getLogger(InitConfigure.class);

    /**
     * 支付宝所有渠道初始化
     */
    private static Map<String, Map<String, Object>> alipayMap = new HashMap<>(12);

    /**
     * 京东所有渠道初始化
     */
    private static Map<String, Map<String, String>> jdpayMap = new HashMap<>(12);

    /**
     * 微信所有渠道初始化
     */
    private static Map<String, Wechatpay> wechatpayMap = new HashMap<>(12);

    /**
     * 银联所有渠道初始化
     */
    private static Map<String, Unionpay> unionpayMap = new HashMap<>(12);

    /**
     * 支付模块统一初始化
     *
     * @author Mr.Yang
     * @date 2018/10/31
     */
    @PostConstruct
    private void init() {
        //支付宝所有渠道信息初始化
        alipayInit();
        //京东所有渠道信息初始化
        jdpayInit();
        //微信所有渠道信息初始化
        wechatpayInit();
        //银联所有渠道信息初始化
        unionpayInit();
    }

    /**
     * 银联所有渠道信息初始化
     *
     * @author Mr.Yang
     * @date 2018/11/17
     */
    private void unionpayInit() {
        //从指定路径加载信息
        try {
            //从指定路径加载信息
            Properties properties =
                    PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource("init/unionpay.properties"), "UTF-8"));
            //处理微信配置多个商户
            JSONArray.parseArray(properties.getProperty(UnionpayConstants.UNION_PAY))
                    //包装并填充至map中
                    .toJavaList(Unionpay.class).forEach(unionpay -> {
                //获取当前项目class
                String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                if (System.getProperty("os.name").toLowerCase().contains(SDKConstants.WINDOWS)) {
                    //处理前置路径
                    String preposePath = classPath.substring(1);
                    //拼接整体路径
                    //商户签名证书
                    unionpay.setAcpSign((preposePath + unionpay.getAcpSign()).replaceAll("/", "\\\\"));
                    //敏感信息加密证书
                    unionpay.setAcpEnc((preposePath + unionpay.getAcpEnc()).replaceAll("/", "\\\\"));
                    //银联中级加密证书
                    unionpay.setAcpMiddle((preposePath + unionpay.getAcpMiddle()).replaceAll("/", "\\\\"));
                    //银行根证书
                    unionpay.setAcpRoot((preposePath + unionpay.getAcpRoot()).replaceAll("/", "\\\\"));
                } else {
                    //如果存在file开头，截取掉
                    if (classPath.startsWith("file:")) {
                        classPath = classPath.substring(5);
                    }
                    //拼接整体路径
                    //商户签名证书
                    unionpay.setAcpSign((classPath + unionpay.getAcpSign()));
                    //敏感信息加密证书
                    unionpay.setAcpEnc((classPath + unionpay.getAcpEnc()));
                    //银联中级加密证书
                    unionpay.setAcpMiddle((classPath + unionpay.getAcpMiddle()));
                    //银行根证书
                    unionpay.setAcpRoot((classPath + unionpay.getAcpRoot()));
                }
                //放入静态变量
                unionpayMap.put(unionpay.getMerId(), unionpay);
            });
        } catch (IOException e) {
            logger.error("Polymer^_^Init^_^Configure^_^unionpay^_^Error^_^{}", e);
        }


    }

    /**
     * 微信所有渠道信息初始化
     *
     * @author Mr.Yang
     * @date 2018/11/17
     */
    private void wechatpayInit() {
        //从指定路径加载信息
        try {
            //从指定路径加载信息
            Properties properties =
                    PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource("init/wechatpay.properties"), "UTF-8"));
            //处理微信配置多个商户
            JSONArray.parseArray(properties.getProperty(WechatConstants.WECHAT_PAY))
                    //包装并填充至map中
                    .toJavaList(Wechatpay.class).forEach(wechat -> {
                //放入静态变量
                wechatpayMap.put(wechat.getAppid(), wechat);
            });
        } catch (IOException e) {
            logger.error("Polymer^_^Init^_^Configure^_^wechatpay^_^Error^_^{}", e);
        }
    }

    /**
     * 京东所有渠道信息初始化
     *
     * @author Mr.Yang
     * @date 2018/10/31
     */
    private void jdpayInit() {
        //从指定路径加载信息
        try {
            //从指定路径加载信息
            Properties properties =
                    PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource("init/jdpay.properties"), "UTF-8"));
            //处理京东配置多个商户
            JSONArray.parseArray(properties.getProperty(JdpayConstants.JD_PAY))
                    //包装并填充至map中
                    .toJavaList(Jdpay.class).forEach(jdpay -> {
                //填充map
                Map<String, String> tmpMap = new HashMap<>(12);
                //京东客户端sdk初始化
                tmpMap.put(JdpayConstants.JD_PAY_SDK, JSONObject.toJSONString(new DefaultJdpayClient(null, jdpay.getMerchant()
                        , jdpay.getJdpayPublicKey(), jdpay.getDesKey(), jdpay.getPrivateKey(), jdpay.getCharset())));
                //填充回调地址
                //同步地址、异步地址
                tmpMap.put(JdpayConstants.NOTIFY_URL, jdpay.getNotifyUrl());
                tmpMap.put(JdpayConstants.CALLBACK_URL, jdpay.getCallbackUrl());
                tmpMap.put(JdpayConstants.MD5_KEY,jdpay.getMdkey());
                //放入静态变量
                jdpayMap.put(jdpay.getMerchant(), tmpMap);
            });
        } catch (IOException e) {
            logger.error("Polymer^_^Init^_^Configure^_^jdpay^_^Error^_^{}", e);
        }
    }

    /**
     * 支付宝所有渠道信息初始化
     *
     * @author Mr.Yang
     * @date 2018/10/31
     */
    private void alipayInit() {
        try {
            //从指定路径加载信息
            Properties properties =
                    PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource("init/aliapy.properties"), "UTF-8"));
            //处理支付宝配置多个商户
            JSONArray.parseArray(properties.getProperty(AlipayConstants.ALI_PAY))
                    //包装并填充至map中
                    .toJavaList(Alipay.class).forEach(alipay -> {
                //填充map
                Map<String, Object> tmpMap = new HashMap<>(12);
                //支付宝客户端sdk初始化
                tmpMap.put(AlipayConstants.ALI_PAY_SDK, new DefaultAlipayClient(alipay.getGatewayUrl(),
                        alipay.getAppId(), alipay.getPrivateKey(), alipay.getFormat(),
                        alipay.getCharset(), alipay.getAlipayPublicKey(), alipay.getSignType()));
                //填充回调地址
                //同步地址、异步地址
                tmpMap.put(AlipayConstants.NOTIFY_URL, alipay.getNotifyUrl());
                tmpMap.put(AlipayConstants.RETURN_URL, alipay.getReturnUrl());
                //放入静态变量
                alipayMap.put(alipay.getAppId(), tmpMap);
            });
        } catch (IOException e) {
            logger.error("Polymer^_^Init^_^Configure^_^alipay^_^Error^_^{}", e);
        }
    }

    /**
     * 获取支付宝sdk初始化
     *
     * @return 返回map信息
     * @author Mr.Yang
     * @date 2018/11/4
     */
    public static Map<String, Map<String, Object>> getAlipayMap() {
        return alipayMap;
    }

    /**
     * 获取京东sdk初始化
     *
     * @return 返回map信息
     * @author Mr.Yang
     * @date 2018/11/4
     */
    public static Map<String, Map<String, String>> getJdpayMap() {
        return jdpayMap;
    }

    /**
     * 获取微信sdk初始化
     *
     * @return 返回map信息
     * @author Mr.Yang
     * @date 2018/11/4
     */
    public static Map<String, Wechatpay> getWechatpayMap() {
        return wechatpayMap;
    }

    /**
     * 获取银联sdk初始化
     *
     * @return 返回map信息
     * @author Mr.Yang
     * @date 2018/11/4
     */
    public static Map<String, Unionpay> getUnionpayMap() {
        return unionpayMap;
    }
}

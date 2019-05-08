package org.pay.polymer.provider.payment.tpp.wechat.api.internal.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * @program: polymer-pay-parent
 * @description: 微信支付请求工具
 * @author: Mr.Yang
 * @create: 2018-11-17 14:02
 **/
public class WechatpayHttp {

    /**
     * 获取java与微信sdk拼接
     */
    private static final String USER_AGENT = "WXPaySDK/3.0.9" +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();


    /**
     * 请求，只请求一次，不做重试
     *
     * @param urlSuffix  请求地址
     * @param mchid      商户号
     * @param data       请求参数
     * @param certStream 证书流
     * @param useCert    是否使用证书，针对退款、撤销等操作
     * @return 返回请求结果
     * @author Mr.Yang
     * @date 2018/11/17
     */
    private static String requestOnce(String urlSuffix, String mchid, String data, InputStream certStream, boolean useCert) throws Exception {
        BasicHttpClientConnectionManager connManager;
        try {
            if (useCert) {
                // 证书
                char[] password = mchid.toCharArray();
                KeyStore ks = KeyStore.getInstance("PKCS12");
                ks.load(certStream, password);

                // 实例化密钥库 & 初始化密钥工厂
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(ks, password);
                String tls = "TLS";
                // 创建 SSLContext
                SSLContext sslContext = SSLContext.getInstance(tls);
                sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                        sslContext,
                        new String[]{"TLSv1"},
                        null,
                        new DefaultHostnameVerifier());

                connManager = new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", sslConnectionSocketFactory)
                                .build(),
                        null,
                        null,
                        null
                );
            } else {
                connManager = new BasicHttpClientConnectionManager(
                        RegistryBuilder.<ConnectionSocketFactory>create()
                                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                                .build(),
                        null,
                        null,
                        null
                );
            }
            HttpClient httpClient = HttpClientBuilder.create()
                    .setConnectionManager(connManager)
                    .build();
            String url = "https://" + urlSuffix;
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8 * 1000).setConnectTimeout(6 * 1000).build();
            httpPost.setConfig(requestConfig);
            StringEntity postEntity = new StringEntity(data, StandardCharsets.UTF_8);
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.addHeader("User-Agent", USER_AGENT + " " + mchid);
            httpPost.setEntity(postEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            return EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 第二次封装
     *
     * @param urlSuffix  请求地址
     * @param mchid      商户号
     * @param data       请求参数
     * @param certStream 证书流
     * @param useCert    是否启动双向
     * @return 返回请求结果
     * @author Mr.Yang
     * @date 2018/11/17
     */
    private static String request(String urlSuffix, String mchid, String data, InputStream certStream, boolean useCert) throws Exception {
        return requestOnce(urlSuffix, mchid, data, certStream, useCert);
    }


    /**
     * 可重试的，非双向认证的请求
     *
     * @param urlSuffix 请求地址
     * @param mchid     商户号
     * @param data      请求参数
     * @return 返回结果信息
     * @author Mr.Yang
     * @date 2018/11/17
     */
    public static String requestWithoutCert(String urlSuffix, String mchid, String data) throws Exception {
        return request(urlSuffix, mchid, data, null, false);
    }

    /**
     * 可重试的，双向认证的请求
     *
     * @param urlSuffix  请求地址
     * @param mchid      商户号
     * @param data       请求参数
     * @param certStream 证书流
     * @return 返回结果信息
     * @author Mr.Yang
     * @date 2018/11/17
     */
    public static String requestWithCert(String urlSuffix, String mchid, String data, InputStream certStream) throws Exception {
        return request(urlSuffix, mchid, data, certStream, true);
    }
}

package org.pay.polymer.provider.payment.tpp.jdpay.api.internal.util;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.pay.polymer.common.tpp.jdpay.JdpayConstants;
import org.pay.polymer.common.util.NumberEnum;
import org.pay.polymer.provider.payment.tpp.jdpay.api.JdpayApiException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.CODE;
import static org.pay.polymer.common.tpp.jdpay.JdpayConstants.MSG;

/**
 * @program: polymer-pay-provider
 * @description: 网络工具
 * @author: Mr.Yang
 * @create: 2018-11-03 13:22
 **/
public abstract class WebUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST = "POST";
    private static final int BUFFER_SIZE = 1024;

    private WebUtils() {
    }

    /**
     * 执行下载文件请求。
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param filename 存储路径（带文件名）
     * @throws IOException
     */
    public static String download(String url, Map<String, String> params, int connectTimeout,
                                  int readTimeout, String filename) throws IOException {
        return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout, filename);
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    private static String doPost(String url, Map<String, String> params, String charset,
                                 int connectTimeout, int readTimeout, String filename) throws IOException {
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, content, connectTimeout, readTimeout, filename);
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param urlstr  请求地址
     * @param content 请求字节数组
     * @return 响应字符串
     * @throws IOException
     */
    private static String doPost(String urlstr, byte[] content, int connectTimeout,
                                 int readTimeout, String filename) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        try {
            URL url = new URL(urlstr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_POST);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            out = conn.getOutputStream();
            out.write(content);
            return saveFile(conn, filename);
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

    }

    private static String saveFile(HttpURLConnection conn, String filename) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        //建立文件
        File file = new File(filename);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        //文件输出流
        FileOutputStream fos = new FileOutputStream(filename);
        //获取网络输入流
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        String returnCode = conn.getHeaderField("Return-Code");
        String returnMsg = conn.getHeaderField("Return-Message");
        if (returnMsg != null) {
            returnMsg = new String(returnMsg.getBytes("ISO8859-1"), DEFAULT_CHARSET);
        }
        JSONObject outcome = new JSONObject();
        outcome.put(CODE, NumberEnum.FAIL_CODE.getCode());
        outcome.put(MSG, returnMsg);
        if (!NumberEnum.CUSTOM_SUCCESS_CODE.getCode().equals(returnCode)) {
            return outcome.toString();
        }
        try {
            //保存文件
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.close();
            bis.close();
            //如果正确则返回
            outcome.put(CODE, NumberEnum.SUCCESS_CODE.getCode());
            return outcome.toString();
        } catch (Exception e) {
            outcome.put(CODE, NumberEnum.ERROR_CODE.getCode());
            outcome.put(MSG, NumberEnum.ERROR_CODE.getName());
            return outcome.toString();
        }
    }

    private static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        Set<Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.areNotEmpty(name, value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }
        return query.toString();
    }

    /**
     * 发送请求到指定地址
     *
     * @param url         请求地址
     * @param reqStr      字符串
     * @param contentType http协调
     * @param charset     字符集
     * @return 返回结果
     * @author Mr.Yang
     * @date 2018/11/3
     */
    public static String sendPostRequest(String url, String reqStr, String contentType, String charset) throws JdpayApiException {
        int timeout = 5000; // 超时时间
        long responseLength = 0; // 响应长度
        String strResult = "";
        HttpClient httpClient = new DefaultHttpClient();
        wrapClient(httpClient);
        try {
            HttpParams httpParams = httpClient.getParams();
            httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
            httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
            HttpPost httpPost = new HttpPost(url); // 创建HttpPost
            StringEntity se = new StringEntity(reqStr, charset);
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
            se.setContentEncoding(charset);
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost); // 执行POST请求

            // 若状态码为200 ok
            if (response.getStatusLine().getStatusCode() == 200) {
                // 取出回应字串
                strResult = EntityUtils.toString(response.getEntity(), charset);
            }
        } catch (Exception e) {
            JdpayLogger.logBizError(e);
            throw new JdpayApiException("调用接口超时");
        } finally {
            httpClient.getConnectionManager().shutdown();// 关闭连接,释放资源
        }
        return strResult;
    }

    /**
     * https 不验证证书
     *
     * @param httpClient
     */
    public static void wrapClient(HttpClient httpClient) {
        try {
            X509TrustManager xtm = new X509TrustManager() { // 创建TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            // TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
            SSLContext ctx = SSLContext.getInstance("TLS");
            // 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
            ctx.init(null, new TrustManager[]{xtm}, null);
            // 创建SSLSocketFactory
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            // 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 构造form表单
     *
     * @param baseUrl    提交地址
     * @param parameters input信息
     * @return 返回form表单标签信息
     * @author Mr.Yang
     * @date 2018/11/3
     */
    public static String buildForm(String baseUrl, Map<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>\n<body onload=\"autosubmit()\">\n");
        sb.append("<form id=\"batchForm\" method=\"post\" action=\"");
        sb.append(baseUrl);
        sb.append("\">\n");
        sb.append(buildHiddenFields(parameters));
        sb.append("</form>\n");
        sb.append("<script>function autosubmit(){document.getElementById(\"batchForm\").submit();}</script>\n");
        sb.append("</body>\n</html>");
        return sb.toString();
    }

    /**
     * @param parameters input信息
     * @return input文本框
     * @author Mr.Yang
     * @date 2018/11/3
     */
    private static String buildHiddenFields(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Set<String> keys = parameters.keySet();
        for (String key : keys) {
            String value = parameters.get(key);
            // 除去参数中的空值
            if (key == null || value == null) {
                continue;
            }
            sb.append(buildHiddenField(key, value));
        }
        return sb.toString();
    }

    /**
     * 编辑input隐藏域
     *
     * @param key   名称
     * @param value 值
     * @return 返回input标签
     * @author Mr.Yang
     * @date 2018/11/3
     */
    private static String buildHiddenField(String key, String value) {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type=\"hidden\" name=\"");
        sb.append(key);
        sb.append("\" value=\"");
        //转义双引号
        String a = value.replace("\"", "&quot;");
        sb.append(a).append("\"/>\n");
        return sb.toString();
    }

}

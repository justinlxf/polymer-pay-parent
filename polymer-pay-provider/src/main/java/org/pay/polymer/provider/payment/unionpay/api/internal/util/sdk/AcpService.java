package org.pay.polymer.provider.payment.unionpay.api.internal.util.sdk;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @program: polymer-pay-provider
 * @description: acpsdk接口服务类
 * @author: Mr.Yang
 * @create: 2018-10-28 18:46
 **/
public class AcpService {

    /**
     * 请求报文签名(使用配置文件中配置的私钥证书或者对称密钥签名)
     * 功能：对请求报文进行签名,并计算赋值certid,signature字段并返回
     *
     * @param reqData  请求报文map
     * @param encoding 上送请求报文域encoding字段的值
     * @return 　签名后的map对象
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static Map<String, String> sign(Map<String, String> reqData, String encoding) {
        reqData = SDKUtil.filterBlank(reqData);
        SDKUtil.sign(reqData, encoding);
        return reqData;
    }

    /**
     * 多证书签名(通过传入私钥证书路径和密码签名）
     * 功能：如果有多个商户号接入银联,每个商户号对应不同的证书可以使用此方法
     *
     * @param reqData  请求参数
     * @param certPath 证书路径
     * @param certPwd  证书密码
     * @param encoding 上送请求报文域encoding字段的值
     * @return 签名后的map对象
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static Map<String, String> sign(Map<String, String> reqData, String certPath,
                                           String certPwd, String encoding) {
        reqData = SDKUtil.filterBlank(reqData);
        SDKUtil.signByCertInfo(reqData, certPath, certPwd, encoding);
        return reqData;
    }

    /**
     * 多证书签名(通过传入私钥证书路径和密码签名）
     * 功能：如果有多个商户号接入银联,每个商户号对应不同的证书可以使用此方法
     *
     * @param reqData  请求报文map
     * @param certPath 签名私钥文件（带路径）
     * @param certPwd  签名私钥密码
     * @param encoding 上送请求报文域encoding字段的值
     * @return 签名后的map对象
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static Map<String, String> signByCertInfo(Map<String, String> reqData, String certPath,
                                                     String certPwd, String encoding) {
        reqData = SDKUtil.filterBlank(reqData);
        SDKUtil.signByCertInfo(reqData, certPath, certPwd, encoding);
        return reqData;
    }

    /**
     * 多密钥签名(通过传入密钥签名)
     * 功能：如果有多个商户号接入银联,每个商户号对应不同的证书可以使用此方法
     *
     * @param reqData   请求报文map
     * @param secureKey 签名对称密钥
     * @param encoding  上送请求报文域encoding字段的值
     * @return 签名后的map对象
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static Map<String, String> signBySecureKey(Map<String, String> reqData, String secureKey, String encoding) {
        reqData = SDKUtil.filterBlank(reqData);
        SDKUtil.signBySecureKey(reqData, secureKey, encoding);
        return reqData;
    }

    /**
     * 验证签名(SHA-1摘要算法)
     *
     * @param rspData  报文数据
     * @param encoding 上送请求报文域encoding字段的值
     * @return true 通过 false 未通过
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static boolean validate(Map<String, String> rspData, String encoding) {
        return SDKUtil.validate(rspData, encoding);
    }

    /**
     * 多密钥验签(通过传入密钥签名)
     *
     * @param rspData  报文数据
     * @param encoding 上送请求报文域encoding字段的值
     * @return true 通过 false 未通过
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static boolean validateBySecureKey(Map<String, String> rspData, String secureKey, String encoding) {
        return SDKUtil.validateBySecureKey(rspData, secureKey, encoding);
    }

    /**
     * 功能：后台交易提交请求报文并接收同步应答报文
     *
     * @param reqData  请求报文
     * @param reqUrl   请求地址
     * @param encoding 上送请求报文域encoding字段的值
     * @return rspData 返回结果
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static Map<String, String> post(
            Map<String, String> reqData, String reqUrl, String encoding) {
        Map<String, String> rspData = new HashMap<>();
        //发送后台请求数据
        //连接超时时间，读超时时间（可自行判断，修改）
        HttpClient hc = new HttpClient(reqUrl, 30000, 30000);
        try {
            int status = hc.send(reqData, encoding);
            if (200 == status) {
                String resultString = hc.getResult();
                if (null != resultString && !"".equals(resultString)) {
                    // 将返回结果转换为map
                    Map<String, String> tmpRspData = SDKUtil.convertResultStringToMap(resultString);
                    rspData.putAll(tmpRspData);
                }
            } else {
                LogUtil.writeLog("返回http状态码[" + status + "]，请检查请求报文或者请求地址是否正确");
            }
        } catch (Exception e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return rspData;
    }

    /**
     * 功能：http Get方法
     *
     * @param reqUrl   请求地址
     * @param encoding 上送请求报文域encoding字段的值
     * @return 返回结果信息
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static String get(String reqUrl, String encoding) {
        //发送后台请求数据
        HttpClient hc = new HttpClient(reqUrl, 30000, 30000);
        try {
            int status = hc.sendGet(encoding);
            if (200 == status) {
                String resultString = hc.getResult();
                if (null != resultString && !"".equals(resultString)) {
                    return resultString;
                }
            } else {
                LogUtil.writeLog("返回http状态码[" + status + "]，请检查请求报文或者请求地址是否正确");
            }
        } catch (Exception e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 功能：前台交易构造HTTP POST自动提交表单
     *
     * @param reqUrl   表单提交地址
     * @param hiddens  以MAP形式存储的表单键值
     * @param encoding 上送请求报文域encoding字段的值
     * @return 构造好的HTTP POST交易表单
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static String createAutoFormHtml(String reqUrl, Map<String, String> hiddens, String encoding) {
        StringBuilder sf = new StringBuilder();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=").append(encoding).append("\"/></head><body>");
        sf.append("<form id = \"pay_form\" action=\"").append(reqUrl).append("\" method=\"post\">");
        if (null != hiddens && 0 != hiddens.size()) {
            Set<Entry<String, String>> set = hiddens.entrySet();
            for (Entry<String, String> ey : set) {
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"").append(key).append("\" id=\"").append(key).append("\" value=\"").append(value).append("\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }


    /**
     * 功能：将批量文件内容使用DEFLATE压缩算法压缩，Base64编码生成字符串并返回
     * 适用到的交易：批量代付，批量代收，批量退货
     *
     * @param filePath 批量文件-全路径文件名
     * @param encoding 上送请求报文域encoding字段的值
     * @return 返回压缩内容
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static String enCodeFileContent(String filePath, String encoding) {
        String baseFileContent = "";

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LogUtil.writeErrorLog(e.getMessage(), e);
            }
        }
        try (InputStream in = new FileInputStream(file)) {
            int fl = in.available();
            byte[] s = new byte[fl];
            in.read(s, 0, fl);
            // 压缩编码.
            baseFileContent = new String(SecureUtil.base64Encode(SDKUtil.deflater(s)), encoding);
        } catch (Exception e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return baseFileContent;
    }

    /**
     * 功能：解析交易返回的fileContent字符串并落地 （ 解base64，解DEFLATE压缩并落地）
     * 适用到的交易：对账文件下载，批量交易状态查询
     *
     * @param resData       返回报文map
     * @param fileDirectory 落地的文件目录（绝对路径）
     * @param encoding      上送请求报文域encoding字段的值
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static String deCodeFileContent(Map<String, String> resData, String fileDirectory, String encoding) {
        // 解析返回文件
        String filePath = null;
        String fileContent = resData.get(SDKConstants.PARAM_FILE_CONTENT);
        if (null != fileContent && !"".equals(fileContent)) {
            FileOutputStream out = null;
            try {
                byte[] fileArray = SDKUtil.inflater(SecureUtil
                        .base64Decode(fileContent.getBytes(encoding)));
                if (SDKUtil.isEmpty(resData.get("fileName"))) {
                    filePath = fileDirectory + File.separator + resData.get("merId")
                            + "_" + resData.get("batchNo") + "_"
                            + resData.get("txnTime") + ".txt";
                } else {
                    filePath = fileDirectory + File.separator + resData.get("fileName");
                }
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                out = new FileOutputStream(file);
                out.write(fileArray, 0, fileArray.length);
                out.flush();
            } catch (IOException e) {
                LogUtil.writeErrorLog(e.getMessage(), e);
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    LogUtil.writeErrorLog(e.getMessage(), e);
                }
            }
        }
        return filePath;
    }

    /**
     * 功能：将结果文件内容 转换成明文字符串：解base64,解压缩
     * 适用到的交易：批量交易状态查询
     *
     * @param fileContent 批量交易状态查询返回的文件内容
     * @param encoding    上送请求报文域encoding字段的值
     * @return 内容明文
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static String getFileContent(String fileContent, String encoding) {
        String fc = "";
        try {
            fc = new String(SDKUtil.inflater(SecureUtil.base64Decode(fileContent.getBytes())), encoding);
        } catch (IOException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return fc;
    }


    /**
     * 功能：持卡人信息域customerInfo构造
     * 说明：不勾选对敏感信息加密权限使用旧的构造customerInfo域方式，不对敏感信息进行加密（对 phoneNo，cvn2， expired不加密），但如果送pin的话则加密
     *
     * @param customerInfoMap 信息域请求参数 key送域名value送值,必送
     *                        例如：customerInfoMap.put("certifTp", "01");					//证件类型
     *                        customerInfoMap.put("certifId", "341126197709218366");	//证件号码
     *                        customerInfoMap.put("customerNm", "互联网");				//姓名
     *                        customerInfoMap.put("phoneNo", "13552535506");			//手机号
     *                        customerInfoMap.put("smsCode", "123456");					//短信验证码
     *                        customerInfoMap.put("pin", "111111");						//密码（加密）
     *                        customerInfoMap.put("cvn2", "123");           			//卡背面的cvn2三位数字（不加密）
     *                        customerInfoMap.put("expired", "1711");  				    //有效期 年在前月在后（不加密)
     * @param accNo           customerInfoMap送了密码那么卡号必送,如果customerInfoMap未送密码pin，此字段可以不送
     * @param encoding        上送请求报文域encoding字段的值
     * @return base64后的持卡人信息域字段
     */
    public static String getCustomerInfo(Map<String, String> customerInfoMap, String accNo, String encoding) {

        if (customerInfoMap.isEmpty())
            return "{}";
        StringBuffer sf = new StringBuffer("{");
        for (Iterator<String> it = customerInfoMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = customerInfoMap.get(key);
            if (key.equals("pin")) {
                if (null == accNo || "".equals(accNo.trim())) {
                    LogUtil.writeLog("送了密码（PIN），必须在getCustomerInfo参数中上传卡号");
                    throw new RuntimeException("加密PIN没送卡号无法后续处理");
                } else {
                    value = encryptPin(accNo, value, encoding);
                }
            }
            sf.append(key).append(SDKConstants.EQUAL).append(value);
            if (it.hasNext())
                sf.append(SDKConstants.AMPERSAND);
        }
        String customerInfo = sf.append("}").toString();
        LogUtil.writeLog("组装的customerInfo明文：" + customerInfo);
        try {
            return new String(SecureUtil.base64Encode(sf.toString().getBytes(
                    encoding)), encoding);
        } catch (UnsupportedEncodingException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return customerInfo;
    }

    /**
     * 功能：持卡人信息域customerInfo构造，勾选对敏感信息加密权限 适用新加密规范，对pin和phoneNo，cvn2，expired加密
     * 适用到的交易：
     *
     * @param customerInfoMap 信息域请求参数 key送域名value送值,必送
     *                        例如：customerInfoMap.put("certifTp", "01");					//证件类型
     *                        customerInfoMap.put("certifId", "341126197709218366");	//证件号码
     *                        customerInfoMap.put("customerNm", "互联网");				//姓名
     *                        customerInfoMap.put("smsCode", "123456");					//短信验证码
     *                        customerInfoMap.put("pin", "111111");						//密码（加密）
     *                        customerInfoMap.put("phoneNo", "13552535506");			//手机号（加密）
     *                        customerInfoMap.put("cvn2", "123");           			//卡背面的cvn2三位数字（加密）
     *                        customerInfoMap.put("expired", "1711");  				    //有效期 年在前月在后（加密）
     * @param accNo           customerInfoMap送了密码那么卡号必送,如果customerInfoMap未送密码PIN，此字段可以不送
     * @param encoding        上送请求报文域encoding字段的值
     * @return base64后的持卡人信息域字段
     */
    public static String getCustomerInfoWithEncrypt(Map<String, String> customerInfoMap, String accNo, String encoding) {
        if (customerInfoMap.isEmpty())
            return "{}";
        StringBuffer sf = new StringBuffer("{");
        //敏感信息加密域
        StringBuffer encryptedInfoSb = new StringBuffer("");

        for (Iterator<String> it = customerInfoMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = customerInfoMap.get(key);
            if (key.equals("phoneNo") || key.equals("cvn2") || key.equals("expired")) {
                encryptedInfoSb.append(key).append(SDKConstants.EQUAL).append(value).append(SDKConstants.AMPERSAND);
            } else {
                if (key.equals("pin")) {
                    if (null == accNo || "".equals(accNo.trim())) {
                        LogUtil.writeLog("送了密码（PIN），必须在getCustomerInfoWithEncrypt参数中上传卡号");
                        throw new RuntimeException("加密PIN没送卡号无法后续处理");
                    } else {
                        value = encryptPin(accNo, value, encoding);
                    }
                }
                sf.append(key).append(SDKConstants.EQUAL).append(value).append(SDKConstants.AMPERSAND);
            }
        }

        if (!encryptedInfoSb.toString().equals("")) {
            encryptedInfoSb.setLength(encryptedInfoSb.length() - 1);//去掉最后一个&符号
            LogUtil.writeLog("组装的customerInfo encryptedInfo明文：" + encryptedInfoSb.toString());
            sf.append("encryptedInfo").append(SDKConstants.EQUAL).append(encryptData(encryptedInfoSb.toString(), encoding));
        } else {
            sf.setLength(sf.length() - 1);
        }

        String customerInfo = sf.append("}").toString();
        LogUtil.writeLog("组装的customerInfo明文：" + customerInfo);
        try {
            return new String(SecureUtil.base64Encode(sf.toString().getBytes(encoding)), encoding);
        } catch (UnsupportedEncodingException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return customerInfo;
    }

    /**
     * 解析返回报文（后台通知）中的customerInfo域：
     * 解base64,如果带敏感信息加密 encryptedInfo 则将其解密并将 encryptedInfo中的域放到customerInfoMap返回
     *
     * @param customerInfo
     * @param encoding
     * @return
     */
    public static Map<String, String> parseCustomerInfo(String customerInfo, String encoding) {
        Map<String, String> customerInfoMap = null;
        try {
            byte[] b = SecureUtil.base64Decode(customerInfo.getBytes(encoding));
            String customerInfoNoBase64 = new String(b, encoding);
            customerInfoNoBase64 = customerInfoNoBase64.substring(1, customerInfoNoBase64.length() - 1);
            customerInfoMap = SDKUtil.parseQString(customerInfoNoBase64);
            if (customerInfoMap.containsKey("encryptedInfo")) {
                String encInfoStr = customerInfoMap.get("encryptedInfo");
                customerInfoMap.remove("encryptedInfo");
                String encryptedInfoStr = decryptData(encInfoStr, encoding);
                Map<String, String> encryptedInfoMap = SDKUtil.parseQString(encryptedInfoStr);
                customerInfoMap.putAll(encryptedInfoMap);
            }
        } catch (UnsupportedEncodingException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return customerInfoMap;
    }

    /**
     * 解析返回报文（后台通知）中的customerInfo域：
     * 解base64,如果带敏感信息加密 encryptedInfo 则将其解密并将 encryptedInfo中的域放到customerInfoMap返回
     *
     * @param customerInfo
     * @param encoding
     * @return
     */
    public static Map<String, String> parseCustomerInfo(String customerInfo, String certPath,
                                                        String certPwd, String encoding) {
        Map<String, String> customerInfoMap = null;
        try {
            byte[] b = SecureUtil.base64Decode(customerInfo.getBytes(encoding));
            String customerInfoNoBase64 = new String(b, encoding);
            customerInfoNoBase64 = customerInfoNoBase64.substring(1, customerInfoNoBase64.length() - 1);
            customerInfoMap = SDKUtil.parseQString(customerInfoNoBase64);
            if (customerInfoMap.containsKey("encryptedInfo")) {
                String encInfoStr = customerInfoMap.get("encryptedInfo");
                customerInfoMap.remove("encryptedInfo");
                String encryptedInfoStr = decryptData(encInfoStr, certPath, certPwd, encoding);
                Map<String, String> encryptedInfoMap = SDKUtil.parseQString(encryptedInfoStr);
                customerInfoMap.putAll(encryptedInfoMap);
            }
        } catch (UnsupportedEncodingException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        } catch (IOException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
        return customerInfoMap;
    }

    /**
     * 密码加密并做base64
     *
     * @param accNo    卡号
     * @param pin      密码
     * @param encoding
     * @return 加密的内容
     * @author Mr.Yang
     * @date 2018/11/18
     */
    public static String encryptPin(String accNo, String pin, String encoding) {
        return SecureUtil.encryptPin(accNo, pin, encoding, CertUtil
                .getEncryptCertPublicKey());
    }

    /**
     * 敏感信息加密并做base64(卡号，手机号，cvn2,有效期）
     *
     * @param data     送 phoneNo,cvn2,有效期
     * @param encoding
     * @return 加密的密文
     */
    public static String encryptData(String data, String encoding) {
        return SecureUtil.encryptData(data, encoding, CertUtil
                .getEncryptCertPublicKey());
    }

    /**
     * 敏感信息解密，使用配置文件acp_sdk.properties解密
     *
     * @param base64EncryptedInfo 加密信息
     * @param encoding
     * @return 解密后的明文
     */
    public static String decryptData(String base64EncryptedInfo, String encoding) {
        return SecureUtil.decryptData(base64EncryptedInfo, encoding, CertUtil
                .getSignCertPrivateKey());
    }

    /**
     * 敏感信息解密,通过传入的私钥解密
     *
     * @param base64EncryptedInfo 加密信息
     * @param certPath            私钥文件（带全路径）
     * @param certPwd             私钥密码
     * @param encoding
     * @return
     */
    public static String decryptData(String base64EncryptedInfo, String certPath,
                                     String certPwd, String encoding) {
        return SecureUtil.decryptData(base64EncryptedInfo, encoding, CertUtil
                .getSignCertPrivateKeyByStoreMap(certPath, certPwd));
    }

    /**
     * 5.0.0加密磁道信息，5.1.0接口请勿使用
     *
     * @param trackData 待加密磁道数据
     * @param encoding  编码格式
     * @return 加密的密文
     * @deprecated
     */
    public static String encryptTrack(String trackData, String encoding) {
        return SecureUtil.encryptData(trackData, encoding,
                CertUtil.getEncryptTrackPublicKey());
    }

    /**
     * 获取敏感信息加密证书的物理序列号
     *
     * @return
     */
    public static String getEncryptCertId() {
        return CertUtil.getEncryptCertId();
    }

    /**
     * 对字符串做base64
     *
     * @param rawStr
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String base64Encode(String rawStr, String encoding) throws IOException {
        byte[] rawByte = rawStr.getBytes(encoding);
        return new String(SecureUtil.base64Encode(rawByte), encoding);
    }

    /**
     * 对base64的字符串解base64
     *
     * @param base64Str
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String base64Decode(String base64Str, String encoding) throws IOException {
        byte[] rawByte = base64Str.getBytes(encoding);
        return new String(SecureUtil.base64Decode(rawByte), encoding);
    }


    /**
     * 有卡交易信息域(cardTransData)构造
     * 所有子域需用“{}”包含，子域间以“&”符号链接。格式如下：{子域名1=值&子域名2=值&子域名3=值}
     * 说明：本示例仅供参考，开发时请根据接口文档中的报文要素组装
     *
     * @param cardTransDataMap cardTransData的数据
     * @param requestData      必须包含merId、orderId、txnTime、txnAmt，磁道加密时需要使用
     * @param encoding         编码
     * @return
     */
    public static String getCardTransData(Map<String, String> cardTransDataMap,
                                          Map<String, String> requestData,
                                          String encoding) {
        {

            StringBuffer cardTransDataBuffer = new StringBuffer();

            if (cardTransDataMap.containsKey("track2Data")) {
                StringBuffer track2Buffer = new StringBuffer();
                track2Buffer.append(requestData.get("merId"))
                        .append(SDKConstants.COLON).append(requestData.get("orderId"))
                        .append(SDKConstants.COLON).append(requestData.get("txnTime"))
                        .append(SDKConstants.COLON).append(requestData.get("txnAmt") == null ? 0 : requestData.get("txnAmt"))
                        .append(SDKConstants.COLON).append(cardTransDataMap.get("track2Data"));
                cardTransDataMap.put("track2Data",
                        AcpService.encryptData(track2Buffer.toString(), encoding));
            }

            if (cardTransDataMap.containsKey("track3Data")) {
                StringBuffer track3Buffer = new StringBuffer();
                track3Buffer.append(requestData.get("merId"))
                        .append(SDKConstants.COLON).append(requestData.get("orderId"))
                        .append(SDKConstants.COLON).append(requestData.get("txnTime"))
                        .append(SDKConstants.COLON).append(requestData.get("txnAmt") == null ? 0 : requestData.get("txnAmt"))
                        .append(SDKConstants.COLON).append(cardTransDataMap.get("track3Data"));
                cardTransDataMap.put("track3Data",
                        AcpService.encryptData(track3Buffer.toString(), encoding));
            }

            return cardTransDataBuffer.append(SDKConstants.LEFT_BRACE)
                    .append(SDKUtil.coverMap2String(cardTransDataMap))
                    .append(SDKConstants.RIGHT_BRACE).toString();
        }

    }

    /**
     * 获取应答报文中的加密公钥证书,并存储到本地,备份原始证书,并自动替换证书
     * 更新成功则返回1，无更新返回0，失败异常返回-1
     *
     * @param resData  返回报文
     * @param encoding         编码
     * @return
     */
    public static int updateEncryptCert(Map<String, String> resData,
                                        String encoding) {
        return SDKUtil.getEncryptCert(resData, encoding);
    }

    /**
     * 获取
     *
     * @param number
     * @return
     */
    public static int genLuhn(String number) {
        return SecureUtil.genLuhn(number);
    }
}

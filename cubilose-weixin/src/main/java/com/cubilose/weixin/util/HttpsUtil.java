package com.cubilose.weixin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/19.
 * <p>
 * Https工具类
 */

public class HttpsUtil {

    private static final Logger logger
            = LoggerFactory.getLogger(HttpsUtil.class);

    /**
     * 建立连接等待时间3秒钟
     */
    private final static int SocketTimeout = 3000;

    /**
     * 连接超时时间3秒钟
     */
    private final static int ConnectTimeout = 3000;

    private final static Boolean SetTimeOut = true;

    private static CloseableHttpClient getHttpClient() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);
        // 指定信任密钥存储对象和连接套接字工厂
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // 信任任何链接
            TrustStrategy anyTrustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            registryBuilder.register("https", sslSF);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        //设置连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        //构建客户端
        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     * @throws IOException
     */
    public static JSONObject get(String url, Map<String, String> queries) throws IOException {
        String responseBody = "";
        // 支持https
        CloseableHttpClient httpClient = getHttpClient();

        StringBuilder sb = new StringBuilder(url);

        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }

        HttpGet httpGet = new HttpGet(sb.toString());
        if (SetTimeOut) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(SocketTimeout).build();
            httpGet.setConfig(requestConfig);
        }
        try {
            logger.info("Executing request " + httpGet.getRequestLine());
            // 请求数据
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);
            } else {
                logger.info("http return status error:" + status);
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.close();
        }
        return JSONObject.parseObject(responseBody);
    }

    /**
     * post
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @param param   post form 提交的参数
     * @return
     * @throws IOException
     */
    public static JSONObject post(String url, Map<String, String> queries, String param) throws IOException {

        String responseBody = "";
        CloseableHttpClient httpClient = getHttpClient();

        StringBuilder sb = new StringBuilder(url);

        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }

        // 指定url,和http方式
        HttpPost httpPost = new HttpPost(sb.toString());
        if (SetTimeOut) {
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(SocketTimeout)
                    .setConnectTimeout(ConnectTimeout).build();
            httpPost.setConfig(requestConfig);
        }

        StringEntity resqEntity = new StringEntity(param, "utf-8");
        resqEntity.setContentType("application/json");
        httpPost.setEntity(resqEntity);

        // 添加参数
        // List<NameValuePair> nvps = new ArrayList<>();
        // if (params != null && params.keySet().size() > 0) {
        //     Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        //     while (iterator.hasNext()) {
        //         Map.Entry<String, String> entry = iterator.next();
        //         nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        //     }
        // }
        // httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

        // 请求数据
        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            System.out.println(response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);
            } else {
                System.out.println("请求失败: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return JSON.parseObject(responseBody);
    }


    public static void main(String[] args) {
        try {
            JSONObject result = get("https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID", null);
            System.out.println(result.toJSONString());
            System.out.println(result.getString("errcode"));
            System.out.println(result.getString("errmsg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

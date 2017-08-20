package com.cubilose.weixin.wx;

/**
 * Created by jianxin.wang on 2017/8/19.
 *
 * 微信配置类
 */
public class WeixinConfiguration {

    public final static String TOKEN = "YJYW2017";

    public final static String APPID = "wx08df5dfab045b72c";

    public final static String APPSECRET = "bd4fd384c3fd874f8fc63aa235f2a752";

    private static String ACCESSTOKEN = "";

    /**
     * token过期时间
     */
    private static long expiresTime = System.currentTimeMillis();

    public static String getAccessToken() {
        return ACCESSTOKEN;
    }

    public static void setAccessToken(String AccessToken) {
        WeixinConfiguration.ACCESSTOKEN = AccessToken;
    }

    public static long getExpiresTime() {
        return expiresTime;
    }

    public static void setExpiresTime(long expiresTime) {
        WeixinConfiguration.expiresTime = expiresTime;
    }

    /**
     * 判断token是否过期
     *
     * @return  当前时间
     */
    public static boolean isTokenInValid() {
        return System.currentTimeMillis() >= expiresTime - 10;
    }
}

package com.cubilose.weixin.wx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cubilose.weixin.util.HttpsUtil;
import com.cubilose.weixin.web.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by jianxin.wang on 2017/8/19.
 *
 * 微信公共服务
 */

public class WeixinService {

    private final static Logger logger
            = LoggerFactory.getLogger(UserController.class);

    /**
     * 更新微信token
     */
    public static void updateToken() {
        // 获取token的地址
        String url = WeixinUrlConstants.GET_TOKEN_URL
                .replace("APPID", WeixinConfiguration.APPID)
                .replace("APPSECRET", WeixinConfiguration.APPSECRET);

        logger.info("更新token url: " + url);

        try {
            JSONObject resultJson = HttpsUtil.get(url, null);
            // 取出access_token
            String accessToken = resultJson.getString("access_token");

            logger.info("access_token: " + accessToken);

            WeixinConfiguration.setAccessToken(accessToken);
            // 过期时间为 当前时间+有效期
            Long expiresIn = resultJson.getLong("expires_in");
            WeixinConfiguration.setExpiresTime(expiresIn * 1000 + System.currentTimeMillis());
        } catch (IOException e) {
            logger.error("获取token失败！！" + e.getMessage());
        }
    }

    /**
     * 拉取关注用户列表
     *
     * @return
     */
    public synchronized static Set<String> pullUserList() {
        if (WeixinConfiguration.isTokenInValid()) {
            updateToken();
        }

        String url = WeixinUrlConstants.GET_USERLIST_URL
                .replace("ACCESS_TOKEN", WeixinConfiguration.getAccessToken())
                .replace("NEXT_OPENID", "");

        Set<String> openIds = new HashSet<>();
        try {
            JSONObject resultJson = HttpsUtil.get(url, null);

            Long total = resultJson.getLong("total");
            String nextOpenid = resultJson.getString("next_openid");

            logger.info("---------- 拉取用户列表 ----------");
            logger.info("total: " + total);

            JSONObject data = resultJson.getJSONObject("data");
            JSONArray openid = data.getJSONArray("openid");
            Iterator<Object> iterator = openid.iterator();
            while (iterator.hasNext()) {
                openIds.add((String) iterator.next());
            }

            while (nextOpenid != null && !nextOpenid.isEmpty()) {
                if (WeixinConfiguration.isTokenInValid()) {
                    updateToken();
                }

                String url2 = WeixinUrlConstants.GET_USERLIST_URL
                        .replace("ACCESS_TOKEN", WeixinConfiguration.getAccessToken())
                        .replace("NEXT_OPENID", nextOpenid);

                JSONObject resultJson2 = HttpsUtil.get(url2, null);
                JSONObject data2 = resultJson2.getJSONObject("data");
                Integer count = resultJson2.getInteger("count");
                if (data2 != null && count > 0) {
                    JSONArray openid2 = data2.getJSONArray("openid");
                    Iterator<Object> iterator2 = openid2.iterator();
                    while (iterator.hasNext()) {
                        openIds.add((String) iterator2.next());
                    }
                    nextOpenid = resultJson2.getString("next_openid");
                } else {
                    nextOpenid = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("拉取用户列表失败！！" + e.getMessage());
        }

        return openIds;
    }

    /**
     * 批量获取用户信息
     *
     * @param openIds   用户微信openid
     * @return
     */
    public synchronized static JSONObject pullUserInfoList(Set<String> openIds) {
        if (WeixinConfiguration.isTokenInValid()) {
            updateToken();
        }

        String url = WeixinUrlConstants.LIST_USERINFO_URL
                .replace("ACCESS_TOKEN", WeixinConfiguration.getAccessToken());

        JSONObject param = new JSONObject();
        JSONArray userList = new JSONArray();

        openIds.forEach(id -> {
            Map<String, Object> paramPair = new HashMap<>();
            paramPair.put("openid", id);
            paramPair.put("lang", "zh_CN");
            userList.add(new JSONObject(paramPair));
        });

        param.put("user_list", userList);

        logger.info("批量获取用户信息参数：" + param.toJSONString());

        try {
            return HttpsUtil.post(url, null, param.toJSONString());
        } catch (IOException e) {
            logger.error("拉取用户列表失败！！" + e.getMessage());
        }
        return null;
    }
}

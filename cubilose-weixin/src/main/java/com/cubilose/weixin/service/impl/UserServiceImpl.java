package com.cubilose.weixin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.mapper.UserMapper;
import com.cubilose.weixin.service.UserService;
import com.cubilose.weixin.util.DateUtils;
import com.cubilose.weixin.wx.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户服务实现类
 */

@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger
            = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public int batchSave(List<User> users) {
        return userMapper.batchInsert(users);
    }

    @Override
    public List<User> list(int pageNum, int pageSize) {
        int startIndex = (pageNum - 1) * pageSize;
        return userMapper.list(startIndex, pageSize);
    }

    @Override
    public User queryById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public User queryByWId(String wId) {
        return userMapper.getByWId(wId);
    }

    @Override
    public List<User> query(String keyword, int pageNum, int pageSize) {
        String params = "%" + keyword + "%";
        int startIndex = (pageNum - 1) * pageSize;
        return userMapper.listByNameOrId(params, startIndex, pageSize);
    }

    @Override
    public void pullUserList() {
        // 所有关注用户的微信ID
        Set<String> openIds = WeixinService.pullUserList();

        // 已保存用户的微信ID
        List<User> userList = this.list(1, Integer.MAX_VALUE);
        // userList.forEach(user -> {
        //     String wId = user.getWId();
        //     // 如果所有关注用户的微信ID包括中已关注用户的微信ID，则移除
        //     if (openIds.contains(wId)) {
        //         openIds.remove(wId);
        //     }
        // });

        for (User user : userList) {
            String wId = user.getWId();
            // 如果所有关注用户的微信ID包括中已关注用户的微信ID，则移除
            if (openIds.contains(wId)) {
                openIds.remove(wId);
            }
        }

        // 获取最新关注用户的信息
        JSONObject result = WeixinService.pullUserInfoList(openIds);
        // 请求成功时，没有errcode字段
        // if (result != null && result.getInteger("errcode") == 0) {
        if (result != null) {
            Integer errcode = result.getInteger("errcode");
            if (errcode == null) {
                JSONArray userInfoList = result.getJSONArray("user_info_list");

                if (userInfoList != null) {
                    logger.debug(userInfoList.toJSONString());

                    List<User> users = new ArrayList<>();
                    Iterator<Object> iterator = userInfoList.iterator();
                    while (iterator.hasNext()) {
                        JSONObject item = (JSONObject) iterator.next();
                        User user = User.builder()
                                .wId(item.getString("openid"))
                                .wName(item.getString("nickname"))
                                .sex(item.getInteger("sex"))
                                .wImg(item.getString("headimgurl"))
                                .subscribeTime(DateUtils.format(new Date(item.getLong("subscribe_time") * 1000)))
                                .couponSize(0)
                                .build();
                        users.add(user);
                    }

                    batchSave(users);
                }
            } else {
                logger.error("批量获取用户信息失败，errcode: " + errcode);
            }
        }
    }

    @Override
    public int increUserCouponSize(Long userId) {
        return userMapper.increUserCouponSize(userId);
    }

}

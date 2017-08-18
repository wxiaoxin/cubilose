package com.cubilose.weixin.service;

import com.cubilose.weixin.entity.User;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

public interface UserService {

    List<User> list(int start, int size);

    User queryById(Long id);

    List<User> queryByName(String wName);

    List<User> queryByLogisticsNumber(String logisticsNumber);

}

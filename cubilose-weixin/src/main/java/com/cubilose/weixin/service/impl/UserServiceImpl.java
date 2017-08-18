package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.mapper.UserMapper;
import com.cubilose.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户服务实现类
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list(int start, int size) {
        return userMapper.list(start, size);
    }

    @Override
    public User queryById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> queryByName(String wName) {
        String params = "%" + wName + "%";
        return userMapper.listByName(params);
    }

    @Override
    public List<User> queryByLogisticsNumber(String logisticsNumber) {
        String params = "%" + logisticsNumber + "%";
        return userMapper.listByLogisticsNumber(params);
    }
}

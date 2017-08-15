package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.mapper.UserMapper;
import com.cubilose.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}

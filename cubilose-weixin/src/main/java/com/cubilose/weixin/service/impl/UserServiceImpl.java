package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.mapper.UserMapper;
import com.cubilose.weixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> list(int pageNum, int pageSize) {
        int startIndex = (pageNum - 1) * pageSize;
        return userMapper.list(startIndex, pageSize);
    }

    @Override
    public User queryById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> query(String keyword, int pageNum, int pageSize) {
        String params = "%" + keyword + "%";
        int startIndex = (pageNum - 1) * pageSize;
        return userMapper.listByNameOrId(params, startIndex, pageSize);
    }

}

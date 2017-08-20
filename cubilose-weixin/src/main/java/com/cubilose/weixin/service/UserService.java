package com.cubilose.weixin.service;

import com.cubilose.weixin.entity.User;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

public interface UserService {

    int batchSave(List<User> users);

    List<User> list(int start, int size);

    User queryById(Long id);

    List<User> query(String keyword, int pageNum, int pageSize);

    void pullUserList();

}

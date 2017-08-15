package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

@Mapper
public interface UserMapper {

    List<User> list();

}

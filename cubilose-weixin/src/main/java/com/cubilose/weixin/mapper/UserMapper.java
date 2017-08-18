package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户服务接口
 */

@Mapper
public interface UserMapper {

    List<User> list(int start, int size);

    User getById(Long id);

    List<User> listByName(String wName);

    List<User> listByLogisticsNumber(String logisticsNumber);

}

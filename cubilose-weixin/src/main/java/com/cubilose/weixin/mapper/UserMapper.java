package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户服务接口
 */

@Mapper
public interface UserMapper {

    /**
     * 批量插入用户数据
     *
     * @param users
     * @return
     */
    int batchInsert(List<User> users);

    List<User> list(@Param("start") int start, @Param("pageSize") int pageSize);

    User getById(Long id);

    List<User> listByName(String wName);

    /**
     * 查询
     *
     * @param keyword       关键字，微信昵称或ID
     * @param start         起始
     * @param pageSize      分页大小
     * @return
     */
    List<User> listByNameOrId(@Param("keyword") String keyword,
                              @Param("start") int start,
                              @Param("pageSize") int pageSize);

}

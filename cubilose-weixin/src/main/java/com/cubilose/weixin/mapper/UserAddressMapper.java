package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.UserAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by jianxin.wang on 2017/8/18.
 *
 * 用户收货地址
 */

@Mapper
public interface UserAddressMapper {

    long insert(UserAddress userAddress);

    UserAddress getById(Long id);

}

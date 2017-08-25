package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.UserAddress;
import com.cubilose.weixin.mapper.UserAddressMapper;
import com.cubilose.weixin.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jianxin.wang on 26/08/2017.
 *
 * 用户地址服务
 */

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public long save(Long userId, String address, Long phoneNumber) {
        UserAddress userAddress = UserAddress.builder()
                .userId(userId)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
        long row = userAddressMapper.insert(userAddress);
        return userAddress.getId();
        // if (row > 0) {
        // } else {
        //     return -1;
        // }
    }
}

package com.cubilose.weixin.service;

/**
 * Created by jianxin.wang on 26/08/2017.
 *
 * 用户地址服务
 */
public interface UserAddressService {

    long save(Long userId, String address, Long phoneNumber);

}

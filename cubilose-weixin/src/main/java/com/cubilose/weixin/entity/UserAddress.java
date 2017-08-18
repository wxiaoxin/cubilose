package com.cubilose.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jianxin.wang on 2017/8/18.
 *
 * 用户收货地址信息
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddress {

    private long id;

    /**
     * 用户主键ID
     */
    private long userId;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货手机号码
     */
    private long phoneNumber;

}

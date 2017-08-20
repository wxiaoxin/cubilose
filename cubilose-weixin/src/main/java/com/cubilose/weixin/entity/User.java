package com.cubilose.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户类
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 微信ID
     */
    private String wId;

    /**
     * 性别
     */
    private int sex;

    /**
     * 微信昵称
     */
    private String wName;

    /**
     * 微信头像地址
     */
    private String wImg;

    /**
     * 用户关注时间
     */
    private String subscribeTime;

    /**
     * 优惠券数量
     */
    private int couponSize;

    /**
     * 优惠券列表
     */
    private List<Coupon> coupons;

}

package com.cubilose.weixin.service;

import com.cubilose.weixin.entity.UserCoupon;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/18.
 */
public interface UserCouponService {

    /**
     * 用户兑换优惠券
     *
     * @param userId        用户主键ID
     * @param couponId      优惠券主键ID
     * @return
     */
    int save(long userId, Long couponId);

    /**
     * 设置物流单号
     *
     * @param id
     * @param logisticsNumber
     * @return
     */
    int upLogisticsNumber(long id, String logisticsNumber);

    List<UserCoupon> listAll(int start, int size);

    List<UserCoupon> queryByUserId(String userId);

    List<UserCoupon> queryByCouponId(String couponId);

    List<UserCoupon> queryByLogisticsNumber(String logisticsNumber);
}

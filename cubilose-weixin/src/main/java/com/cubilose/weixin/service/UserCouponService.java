package com.cubilose.weixin.service;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/18.
 */
public interface UserCouponService {

    /**
     * 发货
     *
     * @param id
     * @param logisticsNumber
     * @return
     */
    int deliver(long id, String logisticsNumber);

    /**
     * 用户兑换优惠券
     *
     * @param userId        用户主键ID
     * @param couponId      优惠券主键ID
     * @return
     */
    long save(long userId, Long couponId);

    List<Map> listAll(String keyword, int start, int size);

    List<Map> queryByUserId(Long userId);

    int updateUserAddress(Long userCouponId, Long userAddressId);
}

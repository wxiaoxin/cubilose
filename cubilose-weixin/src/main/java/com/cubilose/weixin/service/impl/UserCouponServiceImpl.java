package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.UserCoupon;
import com.cubilose.weixin.mapper.UserCouponMapper;
import com.cubilose.weixin.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/18.
 */

@Service
public class UserCouponServiceImpl implements UserCouponService {

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Override
    public int save(long userId, Long couponId) {
        return 0;
    }

    @Override
    public int upLogisticsNumber(long id, String logisticsNumber) {
        return 0;
    }

    @Override
    public List<UserCoupon> listAll(int start, int size) {
        return userCouponMapper.list(start, size);
    }

    @Override
    public List<UserCoupon> queryByUserId(String userId) {
        String params = "%" + userId + "%";
        return userCouponMapper.listByUserId(params);
    }

    @Override
    public List<UserCoupon> queryByCouponId(String couponId) {
        String params = "%" + couponId + "%";
        return userCouponMapper.listByCouponId(params);
    }

    @Override
    public List<UserCoupon> queryByLogisticsNumber(String logisticsNumber) {
        String params = "%" + logisticsNumber + "%";
        return userCouponMapper.listByLogisticsNumber(params);
    }
}

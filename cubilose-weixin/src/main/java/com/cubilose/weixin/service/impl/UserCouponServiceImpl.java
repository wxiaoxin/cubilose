package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.UserCoupon;
import com.cubilose.weixin.mapper.UserCouponMapper;
import com.cubilose.weixin.service.UserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/18.
 */

@Service
public class UserCouponServiceImpl implements UserCouponService {

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Override
    public int deliver(long id, String logisticsNumber) {
        return userCouponMapper.update(id, logisticsNumber);
    }

    @Override
    public long save(long userId, Long couponId) {
        UserCoupon coupon = UserCoupon.builder()
                .userId(userId)
                .couponId(couponId)
                .build();
        int row = userCouponMapper.insert(coupon);
        if (row > 0) {
            return coupon.getId();
        } else {
            return -1;
        }
    }

    @Override
    public List<Map> listAll(int searchType, String keyword, int pageNum, int pageSize) {
        int startIndex = (pageNum - 1) * pageSize;
        String param = "%" + keyword + "%";
        return userCouponMapper.list(searchType, param, startIndex, pageSize);
    }

    @Override
    public List<Map> queryByUserId(Long userId) {
        // String params = "%" + userId + "%";
        return userCouponMapper.listByUserId(userId);
    }

    @Override
    public UserCoupon queryByCouponId(Long couponId) {
        return userCouponMapper.getByCouponId(couponId);
    }

    /**
     * 更新关联的用户地址
     *
     * @param userCouponId
     * @param userAddressId
     * @return
     */
    @Override
    public int updateUserAddress(Long userCouponId, Long userAddressId) {
        return userCouponMapper.updateUserAddressId(userCouponId, userAddressId);
    }
}

package com.cubilose.weixin.service;

import com.cubilose.weixin.entity.Coupon;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 优惠券服务接口
 */

public interface CouponService {

    void save(Coupon coupon);

    void batchSave(List<Coupon> coupons);

    void delete(Long id);

    List<Coupon> list();

    Coupon queryById(Long id);

    Coupon queryByCode(String code);

    List<Coupon> query(String keywords);
}

package com.cubilose.weixin.service;

import com.cubilose.weixin.entity.Coupon;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 优惠券服务接口
 */

public interface CouponService {

    /**
     * 生成随机优惠券码
     *
     * @param length    优惠券码的长度
     * @param size      优惠券数量
     */
    int generateRandomCode(int length, int size, int price, String time);

    void save(Coupon coupon);

    int batchSave(List<Coupon> coupons);

    void delete(Long id);

    int enable(Long id);

    List<Coupon> list(int pageNum, int pageSize);

    Coupon queryById(Long id);

    Coupon queryByCode(String code);

    List<Coupon> query(String keywords);

    String exportCodes();
}

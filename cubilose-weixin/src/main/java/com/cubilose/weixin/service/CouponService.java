package com.cubilose.weixin.service;

import com.cubilose.weixin.entity.Coupon;

import java.util.List;
import java.util.Map;

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

    /**
     * 更新指定优惠券状态，可以删除或者指定已使用
     *
     * @param id                优惠券主键ID
     * @param status            优惠券状态枚举
     * @return
     */
    int updateStatus(Long id, Coupon.Status status);

    List<Coupon> list(int pageNum, int pageSize);

    /**
     * 按优惠券状态统计
     *
     * @return
     */
    List<Map<Integer, Integer>> countByStatus();

    Coupon queryById(Long id);

    /**
     * 根据优惠券码查询优惠券信息
     *
     * @param code  优惠券码
     * @return
     */
    Coupon queryByCode(String code);

    List<Coupon> query(String keywords);

    String exportCodes();
}

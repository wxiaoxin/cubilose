package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.Coupon;
import com.cubilose.weixin.mapper.CouponMapper;
import com.cubilose.weixin.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 优惠券服务实现类
 */

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public void save(Coupon coupon) {
        couponMapper.insert(coupon);
    }

    @Override
    public void batchSave(List<Coupon> coupons) {
        couponMapper.batchInsert(coupons);
    }

    @Override
    public void delete(Long id) {
        couponMapper.delete(id);
    }

    @Override
    public List<Coupon> list() {
        return couponMapper.list();
    }

    @Override
    public Coupon queryById(Long id) {
        return couponMapper.getById(id);
    }

    @Override
    public Coupon queryByCode(String code) {
        return couponMapper.getByCode(code);
    }

    @Override
    public List<Coupon> query(String keywords) {
        String params = "%" + keywords + "%";
        return couponMapper.listByIdOrCode(params);
    }
}

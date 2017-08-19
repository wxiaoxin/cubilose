package com.cubilose.weixin.service.impl;

import com.cubilose.weixin.entity.Coupon;
import com.cubilose.weixin.mapper.CouponMapper;
import com.cubilose.weixin.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 优惠券服务实现类
 */

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    /**
     * 随机优惠券码生成种子
     */
    private final static char[] SEEDS = new char[] {
            '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z',
    };

    @Override
    public int generateRandomCode(int length, int size, int price, String endTime) {
        // 生成的优惠券码集合
        Set<String> codes = new HashSet<>();
        StringBuilder sbf = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int next;
            for (int j = 0; j < length; j++) {
                next = random.nextInt(SEEDS.length);
                sbf.append(SEEDS[next]);
            }
            codes.add(sbf.toString());
            // 清空
            sbf.delete(0, sbf.length());
        }

        // 获取已有的优惠券
        List<Coupon> coupons = this.list(0, 10);
        coupons.forEach(coupon -> {
            String code = coupon.getCode();
            if (codes.contains(code)) {
                // 如果生成优惠券码集合中包含了
                // 历史优惠券码，则移除
                codes.remove(code);
            }
        });

        // 待新插入的优惠券
        List<Coupon> toSaveCoupons = new ArrayList<>();
        codes.forEach(code -> {
            Coupon coupon = Coupon.builder()
                    .code(code)
                    .price(price)
                    .endTime(endTime)
                    .status(1)
                    .build();
            toSaveCoupons.add(coupon);
        });

        // 批量插入
        return this.batchSave(toSaveCoupons);
    }


    @Override
    public void save(Coupon coupon) {
        couponMapper.insert(coupon);
    }

    @Override
    public int batchSave(List<Coupon> coupons) {
        return couponMapper.batchInsert(coupons);
    }

    @Override
    public void delete(Long id) {
        couponMapper.delete(id);
    }

    @Override
    public int enable(Long id) {
        return couponMapper.enable(id);
    }

    @Override
    public List<Coupon> list(int pageNum, int pageSize) {
        int startIndex = (pageNum - 1) * pageSize;
        return couponMapper.list(startIndex, pageSize);
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

    @Override
    public String exportCodes() {
        StringBuilder builder = new StringBuilder();
        List<Coupon> coupons = couponMapper.exportCodes();
        builder.append("code").append(",").append("price").append("\n");
        coupons.forEach(coupon -> {
            builder.append(coupon.getCode()).append(",").append(coupon.getPrice()).append("\n");
        });
        return builder.toString();
    }
}

package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.Coupon;
import com.cubilose.weixin.service.CouponService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 优惠券
 */

@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    private final static Logger logger
            = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @RequestMapping("/list")
    ResponseEntity list() {
        List<Coupon> coupons = couponService.list();
        coupons.forEach(coupon -> {
            logger.info(coupon.toString());
        });

        return success(coupons);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ResponseEntity save(@RequestBody Coupon coupon) {
        logger.info(coupon.toString());
        couponService.save(coupon);

        return success();
    }

    @RequestMapping(value = "/bsave", method = RequestMethod.POST)
    ResponseEntity batchSave(@RequestBody List<Coupon> coupons) {
        coupons.forEach(coupon -> {
            logger.info(coupon.toString());
        });
        couponService.batchSave(coupons);

        return success();
    }

    @RequestMapping(value = "/query")
    ResponseEntity query(@Param("keywords") String keywords) {
        logger.info(keywords);
        List<Coupon> coupons = couponService.query(keywords);
        coupons.forEach(coupon -> {
            logger.info(coupon.toString());
        });

        return success(coupons);
    }

    @RequestMapping("/del/{id}")
    ResponseEntity delete(@PathVariable Long id) {
        logger.info("删除优惠券：" + id);
        couponService.delete(id);

        return success();
    }
}

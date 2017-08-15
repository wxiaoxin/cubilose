package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.Coupon;
import com.cubilose.weixin.service.CouponService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 优惠券
 */

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final static Logger logger
            = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @RequestMapping("/list")
    ResponseEntity<String> list() {
        List<Coupon> coupons = couponService.list();
        coupons.forEach(coupon -> {
            logger.info(coupon.toString());
        });

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ResponseEntity<String> save(@RequestBody Coupon coupon) {
        logger.info(coupon.toString());
        couponService.save(coupon);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/bsave", method = RequestMethod.POST)
    ResponseEntity<String> batchSave(@RequestBody List<Coupon> coupons) {
        coupons.forEach(coupon -> {
            logger.info(coupon.toString());
        });
        couponService.batchSave(coupons);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/query")
    ResponseEntity<String> query(@Param("keywords") String keywords) {
        logger.info(keywords);
        List<Coupon> coupons = couponService.query(keywords);
        coupons.forEach(coupon -> {
            logger.info(coupon.toString());
        });

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}

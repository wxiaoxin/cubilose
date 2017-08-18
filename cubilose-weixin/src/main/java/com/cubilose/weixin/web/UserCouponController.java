package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.UserCoupon;
import com.cubilose.weixin.service.UserCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/18.
 *
 * 用户-优惠券关联表
 */

@RestController
public class UserCouponController extends BaseController {

    private final static Logger logger
            = LoggerFactory.getLogger(UserCouponController.class);

    @Autowired
    private UserCouponService userCouponService;

    /**
     *
     * @param size
     * @param size
     * @return
     */
    @RequestMapping("/list")
    ResponseEntity list(@RequestParam int start, @RequestParam int size) {
        List<UserCoupon> userCoupons = userCouponService.listAll(start, size);
        userCoupons.forEach(userCoupon -> {
            logger.info(userCoupon.toString());
        });

        return success(userCoupons);
    }


}

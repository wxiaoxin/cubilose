package com.cubilose.weixin.web;

import com.alibaba.fastjson.JSONObject;
import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.entity.UserAddress;
import com.cubilose.weixin.entity.UserCoupon;
import com.cubilose.weixin.service.UserAddressService;
import com.cubilose.weixin.service.UserCouponService;
import com.cubilose.weixin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/23.
 */

@Controller
@RequestMapping("/")
public class WelcomeController extends BaseController {

    private final static Logger logger
            = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("/index/{wId}")
    public ModelAndView welcome(@PathVariable String wId) {
        ModelAndView mv = new ModelAndView("welcome");

        User user = userService.queryByWId(wId);

        List<Map> userCoupons = userCouponService.queryByUserId(user.getId());

        mv.addObject("wId", 123);
        mv.addObject("message", "Hello JSP");

        mv.addObject("user", JSONObject.toJSONString(user));
        mv.addObject("coupons", JSONObject.toJSONString(userCoupons));
        return mv;
    }

    @RequestMapping("/status")
    public ModelAndView status(@RequestParam Long couponId) {
        ModelAndView mv = new ModelAndView("status");

        logger.info("去物流信息页：" + couponId);

        UserCoupon userCoupon = userCouponService.queryByCouponId(couponId);
        if (userCoupon != null) {
            logger.info(userCoupon.toString());

            long userAddressId = userCoupon.getUserAddressId();

            UserAddress userAddress = userAddressService.queryById(userAddressId);

            mv.addObject("userCoupon", JSONObject.toJSONString(userCoupon));
            mv.addObject("userAddress", JSONObject.toJSONString(userAddress));
        }

        return mv;
    }


    /**
     * 兑换优惠券页面
     *
     * @param wId
     * @return
     */
    @RequestMapping("/coupon/{wId}")
    public ModelAndView coupon(@PathVariable String wId) {
        ModelAndView mv = new ModelAndView("coupon");
        User user = userService.queryByWId(wId);
        mv.addObject("user", JSONObject.toJSONString(user));
        return mv;
    }

    /**
     * 成功页
     *
     * @param wId
     * @return
     */
    @RequestMapping("/success/{wId}")
    public ModelAndView success(@PathVariable String wId) {
        ModelAndView mv = new ModelAndView("success");
        User user = userService.queryByWId(wId);
        mv.addObject("user", JSONObject.toJSONString(user));
        return mv;
    }

}

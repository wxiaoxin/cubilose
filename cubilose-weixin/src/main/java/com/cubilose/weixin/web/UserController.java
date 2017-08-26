package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.Coupon;
import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.entity.UserAddress;
import com.cubilose.weixin.service.CouponService;
import com.cubilose.weixin.service.UserAddressService;
import com.cubilose.weixin.service.UserCouponService;
import com.cubilose.weixin.service.UserService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户请求处理
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final static Logger logger
            = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserCouponService userCouponService;

    /**
     * 用户信息列表页
     *
     * @return
     */
    @RequestMapping("/list")
    ResponseEntity list(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<User> users = userService.list(pageNum, pageSize);
        // users.forEach(user -> {
        //     logger.info(user.toString());
        // });

        return success(users);
    }

    /**
     * 根据主键ID查询
     *
     * @param id    主键ID
     * @return
     */
    @RequestMapping("/query/{id}")
    ResponseEntity queryById(@PathVariable Long id) {
        logger.info("queryById：" + id);
        User user = userService.queryById(id);

        return success(user);
    }

    /**
     * 根据微信ID查询用户
     *
     * @param wid   微信ID
     * @return
     */
    @RequestMapping("/query/w/{wid}")
    ResponseEntity queryByWId(@PathVariable String wid) {
        logger.info("queryByWId: " + wid);

        User user = userService.queryByWId(wid);

        return success(user);
    }

    /**
     * 根据用户微信昵称ID查询
     *
     * @param keyword   查询关键字，可以是微信昵称，微信ID
     * @return
     */
    @RequestMapping("/query")
    ResponseEntity query(@RequestParam String keyword,
                         @RequestParam int pageNum,
                         @RequestParam int pageSize) {
        logger.info("query：" + keyword);
        List<User> users = userService.query(keyword, pageNum, pageSize);
        // users.forEach(user -> {
        //     logger.info(user.toString());
        // });

        return success(users);
    }

    /**
     * 拉取最新微信用户列表
     *
     * @return
     */
    @RequestMapping("/pull_user_list")
    ResponseEntity pullUserLit() {
       userService.pullUserList();

       return success();
    }


    /**
     * 领取优惠券
     *
     * @param wId   用户的微信ID
     * @param couponCode 优惠券码
     */
    @RequestMapping("/receive")
    ResponseEntity receive(@RequestParam String wId, @RequestParam String couponCode) {
        Coupon coupon = couponService.queryByCode(couponCode);
        if (coupon != null) {
            if (coupon.getStatus() == Coupon.Status.DELETED.getValue()) {
                return error(-1, "该优惠券无效");
            } else if (coupon.getStatus() == Coupon.Status.USED.getValue()) {
                return error(-2, "该优惠券已使用");
            } else {
                User user = userService.queryByWId(wId);
                // 保存用户-优惠券
                long saveId = userCouponService.save(user.getId(), coupon.getId());
                if (saveId > 0) {
                    // 更新优惠券状态
                    couponService.updateStatus(coupon.getId(), Coupon.Status.USED);
                    // 更新用户优惠券数量
                    userService.increUserCouponSize(user.getId());
                } else {
                    return error(-3, "保存优惠券失败");
                }
                return success(saveId);
            }
        } else {
            return error(-4, "没有该优惠券");
        }

    }


    /**
     * 保存用户地址信息
     *
     * @param userId            用户主键ID
     * @param address           地址
     * @param phoneNumber       电话号码
     * @param userCouponId      用户优惠券
     * @return
     */
    @RequestMapping("/saa")
    ResponseEntity saveAddressInfo(@RequestParam Long userId,
                                   @RequestParam String address,
                                   @RequestParam Long phoneNumber,
                                   @RequestParam Long userCouponId) {
        long userAddressId = userAddressService.save(userId, address, phoneNumber);

        userCouponService.updateUserAddress(userCouponId, userAddressId);

        return success();
    }
}

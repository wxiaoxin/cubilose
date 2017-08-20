package com.cubilose.weixin.web;

import com.cubilose.weixin.service.UserCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by jianxin.wang on 2017/8/18.
 *
 * 用户-优惠券关联表
 */

@RestController
@RequestMapping("/uc")
public class UserCouponController extends BaseController {

    private final static Logger logger
            = LoggerFactory.getLogger(UserCouponController.class);

    @Autowired
    private UserCouponService userCouponService;

    /**
     * 列表页
     *
     * @param keyword       查询关键字
     * @param pageNum       页码
     * @param pageSize      分页大小
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity list(@RequestParam String keyword,
                        @RequestParam int pageNum,
                        @RequestParam int pageSize) {
        List<Map> result = userCouponService.listAll(keyword, pageNum, pageSize);
        result.forEach(item -> {
            logger.info(item.toString());
        });

        return success(result);
    }

    /**
     * 发货
     *
     * @param id                    主键ID
     * @param logisticsNumber       物流单号
     * @return
     */
    @RequestMapping("/deliver")
    ResponseEntity deliver(@RequestParam long id, @RequestParam String logisticsNumber) {
        int result = userCouponService.deliver(id, logisticsNumber);

        if (result > 0) {
            return success();
        } else {
            return error();
        }
    }

}

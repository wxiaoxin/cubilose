package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.Coupon;
import com.cubilose.weixin.service.CouponService;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    ResponseEntity list(@RequestParam int pageNum,
                        @RequestParam int pageSize) {
        List<Coupon> coupons = couponService.list(pageNum, pageSize);
        // coupons.forEach(coupon -> {
        //     logger.info(coupon.toString());
        // });

        return success(coupons);
    }

    @RequestMapping("/count")
    ResponseEntity count() {
        List<Map<Integer, Integer>> result = couponService.countByStatus();

        return success(result);
    }

    /**
     * 批量生成优惠券
     *
     * @param codeLength    优惠券码长度
     * @param codeSize      优惠券数量
     * @param price         价格
     * @param endTime       有效时间
     * @return
     */
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    ResponseEntity save(@RequestParam int codeLength,
                        @RequestParam int codeSize,
                        @RequestParam int price,
                        @RequestParam String endTime) {
        int size = couponService.generateRandomCode(codeLength, codeSize, price, endTime);

        return success(size);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ResponseEntity save(@RequestBody Coupon coupon) {
        logger.info(coupon.toString());
        couponService.save(coupon);

        return success();
    }

    @RequestMapping(value = "/bsave", method = RequestMethod.POST)
    ResponseEntity batchSave(@RequestBody List<Coupon> coupons) {
        // coupons.forEach(coupon -> {
        //     logger.info(coupon.toString());
        // });
        couponService.batchSave(coupons);

        return success();
    }

    @RequestMapping(value = "/query")
    ResponseEntity query(@Param("keywords") String keywords) {
        logger.info(keywords);
        List<Coupon> coupons = couponService.query(keywords);
        // coupons.forEach(coupon -> {
        //     logger.info(coupon.toString());
        // });

        return success(coupons);
    }

    /**
     * 禁用优惠券
     *
     * @param id
     * @return
     */
    @RequestMapping("/del/{id}")
    ResponseEntity delete(@PathVariable Long id) {
        logger.info("删除优惠券：" + id);
        couponService.delete(id);

        return success();
    }

    /**
     * 启用优惠券
     *
     * @param id
     * @return
     */
    @RequestMapping("/enable/{id}")
    ResponseEntity enable(@PathVariable Long id) {
        logger.info("启用优惠券：" + id);

        couponService.enable(id);

        return success();
    }

    /**
     * 导出优惠券码
     *
     * @return
     */
    @RequestMapping("/export")
    void export(HttpServletResponse response) throws UnsupportedEncodingException {
        logger.info("导出优惠券码");
        String codes = couponService.exportCodes();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/txt");
        String fileName = URLEncoder.encode("优惠券码.csv", "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            StringReader reader = new StringReader(codes);
            IOUtils.copy(reader, response.getOutputStream());

            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success(codes);
    }

}

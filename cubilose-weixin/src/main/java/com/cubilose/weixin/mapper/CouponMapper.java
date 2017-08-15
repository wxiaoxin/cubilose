package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/16.
 * <p>
 * 优惠券Mapper
 */

@Mapper
public interface CouponMapper {

    /**
     * 插入单张优惠券
     *
     * @param coupon 优惠券
     */
    void insert(Coupon coupon);

    /**
     * 批量插入优惠券
     *
     * @param coupons 优惠券列表
     */
    void batchInsert(List<Coupon> coupons);

    /**
     * 根据主键ID删除优惠券
     *
     * @param id 优惠券主键ID
     */
    void delete(Long id);

    /**
     * 获取优惠券列表
     *
     * @return  优惠券列表
     */
    List<Coupon> list();

    /**
     * 根据主键ID查询优惠券
     *
     * @param id    优惠券主键ID
     * @return      查询到优惠券则返回对应对象，
     *              否则返回null
     */
    Coupon getById(Long id);

    /**
     * 根据优惠券码查询优惠券
     *
     * @param code  优惠券码
     * @return      查询到优惠券则返回对应对应
     *              否则，返回null
     */
    Coupon getByCode(String code);

    /**
     * 根据ID或优惠券码查询优惠券列表
     *
     * @param params    查询参数
     * @return          优惠券列表
     */
    List<Coupon> listByIdOrCode(String params);

}

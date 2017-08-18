package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.UserCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/18.
 */

@Mapper
public interface UserCouponMapper {

    /**
     * 插入一条用户-优惠券记录
     *
     * @param userCoupon    用户-优惠券
     * @return
     */
    int insert(UserCoupon userCoupon);

    /**
     * 更新物流号码
     *
     * @param id                记录主键ID
     * @param logisticsNumber   物流号
     * @return
     */
    int update(int id, String logisticsNumber);

    /**
     * 列表
     *
     * @param start     分页起始页
     * @param size      分页大小
     * @return
     */
    List<UserCoupon> list(int start, int size);

    /**
     * 根据用户主键ID列表
     *
     * @param userId    用户主键ID参数
     * @return
     */
    List<UserCoupon> listByUserId(String userId);

    /**
     * 根据物流单号列表
     *
     * @param logisticsNumber   物流单号参数
     * @return
     */
    List<UserCoupon> listByLogisticsNumber(String logisticsNumber);

    /**
     * 根据优惠券主键ID列表
     *
     * @param couponId      优惠券主键ID参数
     * @return
     */
    List<UserCoupon> listByCouponId(String couponId);

}

package com.cubilose.weixin.mapper;

import com.cubilose.weixin.entity.UserCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    // int insert(UserCoupon userCoupon);

    /**
     * 插入数据
     *
     * @return
     */
    int insert(UserCoupon userCoupon);

    /**
     * 更新物流号码
     *
     * @param id              记录主键ID
     * @param logisticsNumber 物流号
     * @return
     */
    int update(@Param("id") long id, @Param("logisticsNumber") String logisticsNumber);

    /**
     * 列表
     *
     * @param start    分页起始页
     * @param pageSize 分页大小
     * @return
     */
    List<Map> list(@Param("keyword") String keyword,
                   @Param("startIndex") int start,
                   @Param("pageSize") int pageSize);

    /**
     * 根据用户主键ID列表
     *
     * @param userId 用户主键ID参数
     * @return
     */
    List<Map> listByUserId(Long userId);

    int updateUserAddressId(@Param("id") Long id, @Param("userAddressId") Long userAddressId);

    /**
     * 根据物流单号列表
     *
     * @param logisticsNumber 物流单号参数
     * @return
     */
    List<UserCoupon> listByLogisticsNumber(String logisticsNumber);

    /**
     * 根据优惠券主键ID列表
     *
     * @param couponId 优惠券主键ID参数
     * @return
     */
    List<UserCoupon> listByCouponId(String couponId);

    UserCoupon getByCouponId(Long couponId);
}

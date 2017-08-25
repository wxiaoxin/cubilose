package com.cubilose.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jianxin.wang on 2017/8/18.
 *
 * 用户-优惠券关联表
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCoupon {

    /**
     * 主键
     */
    private long id;

    /**
     * 用户主键ID
     */
    private long userId;

    /**
     * 优惠券主键ID
     */
    private long couponId;

    /**
     * 用户收货信息记录主键
     */
    private long userAddressId;

    /**
     * 物流单号
     */
    private String logisticsNumber;

    /**
     * 优惠券领取时间
     */
    private String getTime;

    /**
     * 发货时间
     */
    private String deliveryTime;

}

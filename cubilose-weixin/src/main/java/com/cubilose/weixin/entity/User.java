package com.cubilose.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by jianxin.wang on 2017/8/15.
 *
 * 用户类
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 微信ID
     */
    private String wId;

    /**
     * 微信昵称
     */
    private String wName;

    /**
     * 物流单号
     */
    private Long logisticsNumber;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 注册时间
     */
    private Date registerTime;

}

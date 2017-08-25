package com.cubilose.weixin.entity;

import com.cubilose.weixin.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jianxin.wang on 2017/8/15.
 * <p>
 * 优惠券类
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Coupon {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 优惠券码
     */
    private String code;

    /**
     * 有效期结束时间
     */
    private String endTime;

    /**
     * 价格，单位元
     */
    private int price;

    /**
     * 优惠券状态
     */
    private int status;

    /**
     * 判断当前优惠券是否过期
     *
     * @return  过期返回true，否则返回false
     */
    public boolean overdue() {
        return DateUtils.parse(endTime).getTime() <= System.currentTimeMillis();
    }

    /**
     * 优惠券状态枚举
     */
    public enum Status {
        DELETED(-1),    // 禁用
        VALID(1),       // 有效，即未使用
        USED(2);       // 已使用

        private int value;

        Status(int value) {
            this.value = value;
        }

        public Status valueOf(int value) {
            switch (value) {
                case -1:
                    return DELETED;
                case 1:
                    return VALID;
                case 2:
                    return USED;
                default:
                    return DELETED;
            }
        }

        public int getValue() {
            return value;
        }
    }

}

package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;

import com.example.hotel.vo.OrderVO;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
public interface CouponMatchStrategy {

    /**
     * 判断某个订单是否满足某种优惠策略
     * @param coupon
     * @param orderVO
     * @return
     */
    boolean isMatch(OrderVO orderVO, Coupon coupon);

}

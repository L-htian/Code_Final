package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

public interface CouponService {

    /**
     * 添加酒店生日优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelBirthdayCoupon(HotelBirthdayCouponVO couponVO);

    /**
     * 添加酒店间数优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelMultiplyRoomsCoupon(HotelMultiplyRoomsCouponVO couponVO);

    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO);

    /**
     * 添加酒店节日优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelFestivalCoupon(HotelFestivalCouponVO couponVO);

    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

}

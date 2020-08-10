package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelBirthdayCouponVO;
import com.example.hotel.vo.HotelMultiplyRoomsCouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.HotelFestivalCouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 接收前端API的调用，调用业务逻辑层的实现
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/hotelBirthday")
    public ResponseVO addHotelBirthdayCoupon(@RequestBody HotelBirthdayCouponVO hotelBirthdayCouponVO) {

        CouponVO couponVO = couponService.addHotelBirthdayCoupon(hotelBirthdayCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelMultiplyRooms")
    public ResponseVO addHotelMultiplyRoomsCoupon(@RequestBody HotelMultiplyRoomsCouponVO hotelMultiplyRoomsCouponVO) {

        CouponVO couponVO = couponService.addHotelMultiplyRoomsCoupon(hotelMultiplyRoomsCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelTargetMoney")
    public ResponseVO addHotelTargetMoneyCoupon(@RequestBody HotelTargetMoneyCouponVO hotelTargetMoneyCouponVO) {

        CouponVO couponVO = couponService.addHotelTargetMoneyCoupon(hotelTargetMoneyCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelFestival")
    public ResponseVO addHotelFestivalCoupon(@RequestBody HotelFestivalCouponVO hotelFestivalCouponVO) {

        CouponVO couponVO = couponService.addHotelFestivalCoupon(hotelFestivalCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }


}

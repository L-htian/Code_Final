package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.HotelFestivalCouponVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@Service
public class FestivalCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种节日打折优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon){
        if (coupon.getCouponType() != 4) {
            return false;
        }
        //如果type不匹配直接返回false
        String checkInDate = orderVO.getCheckInDate();
        String checkOutDate = orderVO.getCheckOutDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate timeIn = LocalDate.parse(checkInDate,df);
        LocalDate timeOut = LocalDate.parse(checkOutDate,df);
        boolean afterStart = timeIn.isAfter(coupon.getStartTime()) || timeIn.isEqual(coupon.getStartTime());
        boolean beforeEnd = timeOut.isBefore(coupon.getEndTime()) || timeOut.isEqual(coupon.getEndTime());

        if (afterStart && beforeEnd) {
            return true;
        }
        //符合条件返回true
        return false;
    }
}

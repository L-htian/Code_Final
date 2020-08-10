package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.HotelMultiplyRoomsCouponVO;
import org.springframework.stereotype.Service;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@Service
public class MutiplyRoomsCouponStrategyImpl implements CouponMatchStrategy{
    /**
     * 判断某个订单是否满足间数打折优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon){
        if (coupon.getCouponType() != 2) {
            return false;
        }
        //类型不匹配直接返回false
        if (orderVO.getRoomNum() >= coupon.getRoomNum()) {
            return true;
        }
        //符合条件返回true
        return false;
    }
}

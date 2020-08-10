package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@Service
public class TargetMoneyCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种满减金额优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getCouponType() != 3) {
            return false;
        }
        //类型不匹配直接返回false
        if (orderVO.getPrice() >= coupon.getTargetMoney()) {
            return true;
        }
        //符合条件返回true
        return false;
    }
}

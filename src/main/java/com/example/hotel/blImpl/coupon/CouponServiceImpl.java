package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */

@Service
public class CouponServiceImpl implements CouponService {

    private final BirthdayCouponStrategyImpl birthdayCouponStrategy;
    private final TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final FestivalCouponStrategyImpl festivalCouponStrategy;
    private final MutiplyRoomsCouponStrategyImpl mutiplyRoomsCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(BirthdayCouponStrategyImpl birthdayCouponStrategy,
                             TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             FestivalCouponStrategyImpl festivalCouponStrategy,
                             MutiplyRoomsCouponStrategyImpl mutiplyRoomsCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.birthdayCouponStrategy = birthdayCouponStrategy;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.festivalCouponStrategy = festivalCouponStrategy;
        this.mutiplyRoomsCouponStrategy = mutiplyRoomsCouponStrategy;
        strategyList.add(birthdayCouponStrategy);
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(festivalCouponStrategy);
        strategyList.add(mutiplyRoomsCouponStrategy);
        //类的初始化
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());
        List<Coupon> websiteCoupons = getHotelAllCoupon(-1);

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }
        //把酒店的符合的优惠加入优惠列表
        for (int i = 0; i < websiteCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, websiteCoupons.get(i))) {
                    availAbleCoupons.add(websiteCoupons.get(i));
                }
            }
        }
        //把网站的符合的优惠加入优惠列表
        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        //直接交给data层实现
        return hotelCoupons;
    }

    @Override
    public CouponVO addHotelBirthdayCoupon(HotelBirthdayCouponVO couponVO){
        Coupon coupon = new Coupon();
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setCouponType(1);
        coupon.setRoomNum(0);
        coupon.setStartTime(LocalDate.of(1900,1,1));
        coupon.setEndTime(LocalDate.of(2077,12,31));
        //填充优惠信息
        int result = couponMapper.insertCoupon(coupon);
        //交给data层解决
        couponVO.setId(result);
        return  couponVO;
    }

    @Override
    public CouponVO addHotelMultiplyRoomsCoupon(HotelMultiplyRoomsCouponVO couponVO){
        Coupon coupon = new Coupon();
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setRoomNum(couponVO.getRoomNum());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        coupon.setCouponType(2);
        coupon.setStartTime(LocalDate.of(1900,1,1));
        coupon.setEndTime(LocalDate.of(2077,12,31));
        //填充优惠信息
        int result = couponMapper.insertCoupon(coupon);
        //交给data层解决
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStatus(1);
        coupon.setCouponType(3);
        coupon.setRoomNum(0);
        coupon.setStartTime(LocalDate.of(1900,1,1));
        coupon.setEndTime(LocalDate.of(2077,12,31));
        //填充优惠信息
        int result = couponMapper.insertCoupon(coupon);
        //交给data层解决
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelFestivalCoupon(HotelFestivalCouponVO couponVO){
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        //这里要转换类型
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        coupon.setCouponType(4);
        coupon.setRoomNum(0);
        //填充优惠信息
        int result = couponMapper.insertCoupon(coupon);
        //交给data层解决
        couponVO.setId(result);
        return couponVO;
    }
}

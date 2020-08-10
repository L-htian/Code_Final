package com.example.hotel.bl.coupon;

import com.example.hotel.blImpl.coupon.TargetMoneyCouponStrategyImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceTest {
	@Autowired
	CouponService couponService;

	Coupon coupon1 = new Coupon();
	Coupon coupon2 = new Coupon();
	Coupon coupon3 = new Coupon();
	Coupon coupon4 = new Coupon();
	Coupon coupon5 = new Coupon();
	Coupon coupon6 = new Coupon();


	/**
	 * 设置优惠策略
	 */
	@Before
	public void setCoupons(){
		coupon1.setId(2);
		coupon1.setDescription("满500-100优惠");
		coupon1.setHotelId(2);
		coupon1.setCouponType(3);
		coupon1.setCouponName("满减优惠券");
		coupon1.setTargetMoney(500.0);
		coupon1.setDiscount(0);
		coupon1.setStatus(1);
		coupon1.setStartTime(null);
		coupon1.setEndTime(null);
		coupon1.setDiscountMoney(100);
		coupon1.setRoomNum(null);

		coupon2.setId(3);
		coupon2.setDescription("满1500000-5优惠");
		coupon2.setHotelId(2);
		coupon2.setCouponType(3);
		coupon2.setCouponName("满减优惠券");
		coupon2.setTargetMoney(1500000.0);
		coupon2.setDiscount(0);
		coupon2.setStatus(1);
		coupon2.setStartTime(null);
		coupon2.setEndTime(null);
		coupon2.setDiscountMoney(5);
		coupon2.setRoomNum(null);

		coupon3.setId(4);
		coupon3.setDescription("满2间打9折优惠");
		coupon3.setHotelId(2);
		coupon3.setCouponType(2);
		coupon3.setCouponName("间数优惠券");
		coupon3.setTargetMoney(0.0);
		coupon3.setDiscount(0.9);
		coupon3.setStatus(1);
		coupon3.setStartTime(null);
		coupon3.setEndTime(null);
		coupon3.setDiscountMoney(0);
		coupon3.setRoomNum(2);

		coupon4.setId(5);
		coupon4.setDescription("满200-20优惠");
		coupon4.setHotelId(-1);
		coupon4.setCouponType(3);
		coupon4.setCouponName("满减优惠券");
		coupon4.setTargetMoney(200.0);
		coupon4.setDiscount(0);
		coupon4.setStatus(1);
		coupon4.setStartTime(null);
		coupon4.setEndTime(null);
		coupon4.setDiscountMoney(20);
		coupon4.setRoomNum(null);

		coupon5.setId(7);
		coupon5.setDescription("满3间打85折优惠");
		coupon5.setHotelId(-1);
		coupon5.setCouponType(2);
		coupon5.setCouponName("间数优惠券");
		coupon5.setTargetMoney(0.0);
		coupon5.setDiscount(0.85);
		coupon5.setStatus(1);
		coupon5.setStartTime(null);
		coupon5.setEndTime(null);
		coupon5.setDiscountMoney(0);
		coupon5.setRoomNum(3);

		coupon6.setId(8);
		coupon6.setDescription("超级无敌至尊VIP客户生日减一块钱优惠");
		coupon6.setHotelId(-1);
		coupon6.setCouponType(1);
		coupon6.setCouponName("生日优惠券");
		coupon6.setTargetMoney(0.0);
		coupon6.setDiscount(0);
		coupon6.setStatus(1);
		coupon6.setStartTime(null);
		coupon6.setEndTime(null);
		coupon6.setDiscountMoney(1);
		coupon6.setRoomNum(null);
	}

	/**
	 * 测试addHotelBirthdayCoupon方法
	 */
	@Test
	public void addHotelBirthdayCoupon() {
		HotelBirthdayCouponVO couponVO = new HotelBirthdayCouponVO();
		couponVO.setDiscountMoney(100);
		couponVO.setDescription("生日减100");
		couponVO.setHotelId(1);
		couponVO.setName("生日优惠券");
		couponVO.setStatus(1);
		CouponVO couponVO1 = couponService.addHotelBirthdayCoupon(couponVO);

	}

	/**
	 * 测试addHotelMultiplyRoomsCoupon方法
	 */
	@Test
	public void addHotelMultiplyRoomsCoupon() {
		HotelMultiplyRoomsCouponVO couponVO = new HotelMultiplyRoomsCouponVO();
		couponVO.setDiscount(0.9);
		couponVO.setRoomNum(2);
		couponVO.setDescription("满2间打九折");
		couponVO.setHotelId(1);
		couponVO.setName("间数优惠券");
		couponVO.setStatus(1);
		CouponVO couponVO1 = couponService.addHotelMultiplyRoomsCoupon(couponVO);
	}

	/**
	 * 测试addHotelTargetMoneyCoupon方法
	 */
	@Test
	public void addHotelTargetMoneyCoupon() {
		HotelTargetMoneyCouponVO couponVO = new HotelTargetMoneyCouponVO();
		couponVO.setDiscountMoney(100);
		couponVO.setTargetMoney(500);
		couponVO.setDescription("满500-100");
		couponVO.setHotelId(1);
		couponVO.setName("满减优惠券");
		couponVO.setStatus(1);
		couponVO.setType(3);
		CouponVO couponVO1 = couponService.addHotelTargetMoneyCoupon(couponVO);
	}

	/**
	 * 测试addHotelFestivalCoupon方法
	 */
	@Test
	public void addHotelFestivalCoupon() {
		HotelFestivalCouponVO couponVO = new HotelFestivalCouponVO();
		couponVO.setDiscount(0.95);
		couponVO.setStartTime("2020-12-24");
		couponVO.setEndTime("2020-12-25");
		couponVO.setDescription("2020圣诞节打95折");
		couponVO.setHotelId(1);
		couponVO.setName("节日优惠券");
		couponVO.setStatus(1);
		couponVO.setType(4);
		CouponVO couponVO1 = couponService.addHotelFestivalCoupon(couponVO);
	}

	/**
	 * 测试getMatchOrderCoupon方法
	 */
	@Test
	public void getMatchOrderCoupon() {
		OrderVO orderVO = new OrderVO();
		orderVO.setId(1);
		orderVO.setUserId(4);
		orderVO.setHotelId(2);
		orderVO.setPrice(500.0);
		orderVO.setRoomNum(2);
		orderVO.setCheckInDate("2020-10-01");
		orderVO.setCheckOutDate("2020-10-07");
		List<Coupon> coupons = couponService.getMatchOrderCoupon(orderVO);
		List<Coupon> expect = new ArrayList<>();
		expect.add(coupon1);
		expect.add(coupon3);
		expect.add(coupon4);
		assertEquals(expect.get(0).getDescription(),coupons.get(0).getDescription());
		assertEquals(expect.get(1).getDescription(),coupons.get(1).getDescription());
		assertEquals(expect.get(2).getDescription(),coupons.get(2).getDescription());
	}

	/**
	 * 测试getHotelAllCoupon方法
	 */
	@Test
	public void getHotelAllCoupon() {
		List<Coupon> coupons1 = couponService.getHotelAllCoupon(2);
		List<Coupon> coupons2 = couponService.getHotelAllCoupon(-1);
		List<Coupon> coupons3 = new ArrayList<>();
		coupons3.add(coupon1);
		coupons3.add(coupon2);
		coupons3.add(coupon3);
		List<Coupon> coupons4 = new ArrayList<>();
		coupons4.add(coupon4);
		coupons4.add(coupon5);
		coupons4.add(coupon6);
		assertEquals(coupons3.get(0).getId(),coupons1.get(0).getId());
		assertEquals(coupons3.get(1).getId(),coupons1.get(1).getId());
		assertEquals(coupons3.get(2).getId(),coupons1.get(2).getId());
		assertEquals(coupons4.get(0).getId(),coupons2.get(0).getId());
		assertEquals(coupons4.get(1).getId(),coupons2.get(1).getId());
		assertEquals(coupons4.get(2).getId(),coupons2.get(2).getId());
	}
}
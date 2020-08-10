package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponMatchStrategyTest {
	@Qualifier("birthdayCouponStrategyImpl")
	@Autowired
	CouponMatchStrategy birthday;

	@Qualifier("festivalCouponStrategyImpl")
	@Autowired
	CouponMatchStrategy festival;

	@Qualifier("mutiplyRoomsCouponStrategyImpl")
	@Autowired
	CouponMatchStrategy mutiplyRooms;

	@Qualifier("targetMoneyCouponStrategyImpl")
	@Autowired
	CouponMatchStrategy targetMoney;

	OrderVO order1 = new OrderVO();
	OrderVO order2 = new OrderVO();

	/**
	 * 设置订单信息
	 */
	@Before
	public void setup(){
		order1.setId(1);
		order1.setUserId(4);
		order1.setHotelId(1);
		order1.setHotelName("随便啦");
		order1.setCheckInDate("2020-10-01");
		order1.setCheckOutDate("2020-10-07");
		order1.setRoomType("大床房");
		order1.setRoomNum(3);
		order1.setPeopleNum(3);
		order1.setHaveChild(false);
		order1.setCreateDate("2020-06-14");
		order1.setPrice(100.0);
		order1.setClientName("91MRLI");
		order1.setPhoneNumber("114514");
		order1.setOrderState("已预订");
		order1.setIsRating("true");
		order1.setRate(10);
		order1.setComment("彳亍");
		order2.setUserId(4);
		order2.setCheckInDate("2020-02-28");
		order2.setCheckOutDate("2020-10-07");
		order2.setRoomNum(1);
		order2.setPrice(50000.0);
	}

	/**
	 * 测试生日优惠订单匹配方法
	 */
	@Test
	public void birthdayTest() {
		Coupon coupon = new Coupon();
		coupon.setCouponType(1);
		coupon.setDiscountMoney(10);
		coupon.setStatus(1);
		assertFalse(birthday.isMatch(order1,coupon));
		assertTrue(birthday.isMatch(order2,coupon));
	}

	/**
	 * 测试多间优惠订单匹配方法
	 */
	@Test
	public void multiplyRoomTest(){
		Coupon coupon = new Coupon();
		coupon.setCouponType(2);
		coupon.setStatus(1);
		coupon.setRoomNum(3);
		assertTrue(mutiplyRooms.isMatch(order1,coupon));
		assertFalse(mutiplyRooms.isMatch(order2,coupon));
	}

	/**
	 * 测试满减优惠订单匹配方法
	 */
	@Test
	public void targetMoneyTest(){
		Coupon coupon = new Coupon();
		coupon.setCouponType(3);
		coupon.setStatus(1);
		coupon.setTargetMoney(500);
		assertTrue(targetMoney.isMatch(order2,coupon));
		assertFalse(targetMoney.isMatch(order1,coupon));
	}

	/**
	 * 测试节日优惠订单匹配方法
	 */
	@Test
	public void festivalTest(){
		Coupon coupon = new Coupon();
		coupon.setCouponType(4);
		coupon.setStatus(1);
		LocalDate starttime = LocalDate.of(2020,10,1);
		LocalDate endtime = LocalDate.of(2020,10,7);
		coupon.setStartTime(starttime);
		coupon.setEndTime(endtime);
		assertTrue(festival.isMatch(order1,coupon));
		assertFalse(festival.isMatch(order2,coupon));
	}
}
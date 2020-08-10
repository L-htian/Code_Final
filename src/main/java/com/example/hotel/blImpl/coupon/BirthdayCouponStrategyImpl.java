package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@Service
public class BirthdayCouponStrategyImpl implements CouponMatchStrategy {

	@Autowired
	AccountService accountService;

	/**
	 * 判断某个订单是否满足某种节日打折优惠策略
	 * @param orderVO
	 * @param coupon
	 * @return
	 */
	@Override
	public boolean isMatch(OrderVO orderVO, Coupon coupon) {
		if (coupon.getCouponType() != 1) {
			return false;
		}
		User user = accountService.getUserInfo(orderVO.getUserId());
		if(!user.getVip()){
			return false;
			//如果不是vip就直接返回false
		}
		LocalDate birthday = user.getBirthday();
		String checkInDate = orderVO.getCheckInDate();
		String checkOutDate = orderVO.getCheckOutDate();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate timeIn = LocalDate.parse(checkInDate,df);
		LocalDate timeOut = LocalDate.parse(checkOutDate,df);
		LocalDate thisYearBirthday = birthday.withYear(timeIn.getYear());
		boolean checkInBeforeBirthday = timeIn.isBefore(thisYearBirthday) || timeIn.isEqual(thisYearBirthday);
		boolean checkOutAfterBirthday = timeOut.isAfter(thisYearBirthday) || timeIn.isEqual(thisYearBirthday);
		if(checkInBeforeBirthday&&checkOutAfterBirthday){
			return true;
			//如果订单与生日相符就返回true
		}
		return false;
	}
}
package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.RateVO;
import com.example.hotel.vo.ResponseVO;
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
public class OrderServiceTest {
	@Autowired
	OrderService orderService;

	OrderVO orderVO = new OrderVO();

	/**
	 * 测试setup方法
	 */
	@Before
	public void setup(){
		orderVO.setUserId(4);
		orderVO.setHotelId(2);
		orderVO.setHotelName("儒家酒店");
		orderVO.setCheckInDate("2020-09-17");
		orderVO.setCheckOutDate("2020-11-19");
		orderVO.setRoomType("Family");
		orderVO.setRoomNum(2);
		orderVO.setPeopleNum(2);
		orderVO.setHaveChild(false);
		orderVO.setCreateDate("2020-06-19");
		orderVO.setPrice(1000.0);
		orderVO.setClientName("赛博朋克");//当然这并没有什么卵用
		orderVO.setPhoneNumber("2077");
		orderVO.setOrderState("已预订");
		orderVO.setIsRating(null);
}

	/**
	 * 测试addOrder方法
	 */
	@Test
	public void addOrder() {
		ResponseVO responseVO = orderService.addOrder(orderVO);
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
	}

	/**
	 * 测试getAllOrders方法
	 */
	@Test
	public void getAllOrders() {
		List<Order> list = orderService.getAllOrders();
		assertEquals("测试一号",list.get(0).getClientName());
	}

	/**
	 * 测试getUserOrders方法
	 */
	@Test
	public void getUserOrders() {
		List a = new ArrayList();
		assertEquals(a,orderService.getUserOrders(5));
		assertEquals("测试一号",orderService.getUserOrders(4).get(0).getClientName());
	}

	/**
	 * 测试annulOrder方法
	 */
	@Test
	public void annulOrder() {
		ResponseVO responseVO = orderService.annulOrder(1);
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
	}

	/**
	 * 测试getAllOrdersByManagerId方法
	 */
	@Test
	public void getAllOrdersByManagerId() {
		List<Order> list = orderService.getAllOrdersByManagerId(2);
		assertEquals("测试一号",list.get(0).getClientName());
	}

	/**
	 * 测试getCommentsByHotelId方法
	 */
	@Test
	public void getCommentsByHotelId() {
		List a = new ArrayList();
		assertEquals(a,orderService.getCommentsByHotelId(2));
	}

	/**
	 * 测试getuserHotelOrders方法
	 */
	@Test
	public void getuserHotelOrders() {
		List<Order> list = orderService.getuserHotelOrders(4,2);
		assertEquals("测试一号",list.get(0).getClientName());
	}

	/**
	 * 测试orderDetailbyId方法
	 */
	@Test
	public void orderDetailbyId() {
		Order order = orderService.orderDetailbyId(14);
		assertEquals("测试一号",order.getClientName());
	}

	/**
	 * 测试rateHotel方法
	 */
	@Test
	public void rateHotel() {
		RateVO rateVO = new RateVO();
		rateVO.setId(14);
		rateVO.setComment("hahaha");
		rateVO.setRate(5.0);
		ResponseVO responseVO = orderService.rateHotel(14,rateVO);
		ResponseVO excepted = ResponseVO.buildFailure("订单未完成");
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
	}
}
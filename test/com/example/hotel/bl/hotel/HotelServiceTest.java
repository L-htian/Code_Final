package com.example.hotel.bl.hotel;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.Hotel;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
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
public class HotelServiceTest {
	@Autowired
	HotelService hotelService;

	/**
	 * 测试addHotel方法
	 */
	@Test
	public void addHotel() throws ServiceException {
		HotelVO hotelVO = new HotelVO();
		hotelVO.setManagerId(6);
		hotelVO.setDescription("好");
		hotelVO.setAddress("河南");
		hotelVO.setName("国宝大酒店");
		hotelVO.setPhoneNum("181250065");
		hotelVO.setRate(5.0);
		hotelVO.setBizRegion("XinJieKou");
		hotelVO.setHotelStar("Five");
		List<String> hotelServices = new ArrayList<>();
		hotelServices.add("李先生的游戏畅玩");
		hotelVO.setHotelServiece(hotelServices);
		hotelService.addHotel(hotelVO);
	}

	/**
	 * 测试retrieveHotels方法
	 */
	@Test
	public void retrieveHotels() {
		List<HotelVO> list = hotelService.retrieveHotels();
		assertEquals("国宝大酒店",list.get(3).getName());
	}

	/**
	 * 测试retrieveHotelDetails方法
	 */
	@Test
	public void retrieveHotelDetails() {
		HotelVO hotelVO = hotelService.retrieveHotelDetails(4);
		assertEquals("国宝大酒店",hotelVO.getName());
	}

	/**
	 * 测试getRoomCurNum方法
	 */
	@Test
	public void getRoomCurNum() {
		assertEquals(30,hotelService.getRoomCurNum(1,"DoubleBed"));
	}

	/**
	 * 测试deleteHotel方法
	 */
	@Test
	public void deleteHotel() {
		ResponseVO responseVO = hotelService.deleteHotel(4);
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
	}

	/**
	 * 测试getHotelsByManagerId方法
	 */
	@Test
	public void getHotelsByManagerId() {
		List<HotelVO> list = hotelService.getHotelsByManagerId(6);
		assertEquals("国宝大酒店",list.get(1).getName());
		assertEquals("桂圆酒店",list.get(0).getName());
	}

	/**
	 * 测试addHotelNumber方法
	 */
	@Test
	public void addHotelNumber() {
		hotelService.addHotelNumber(4,100.0);
	}
}
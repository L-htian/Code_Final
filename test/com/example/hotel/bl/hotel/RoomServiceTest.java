package com.example.hotel.bl.hotel;

import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {
	@Autowired
	RoomService roomService;


	/**
	 * 测试insertRoomInfo方法
	 */
	@Test
	public void insertRoomInfo() {
		HotelRoom hotelRoom = new HotelRoom();
		hotelRoom.setCurNum(200);
		hotelRoom.setHotelId(4);
		hotelRoom.setHotelService("");
		hotelRoom.setPrice(114);
		hotelRoom.setRoomType(RoomType.BigBed);
		hotelRoom.setTotal(200);
		roomService.insertRoomInfo(hotelRoom);
	}
}
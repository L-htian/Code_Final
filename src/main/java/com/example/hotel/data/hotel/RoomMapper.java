package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {
    /**
     * 从database中更新酒店房间信息
     * @param hotelId
     * @param roomType
     * @param curNum
     * @return
     */
    int updateRoomInfo(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType,@Param("curNum") Integer curNum);
    /**
     * 从database增加新的房间列
     * @param hotelRoom
     * @return
     */
    int insertRoom(HotelRoom hotelRoom);
    /**
     * 从database查找对应酒店id的酒店
     * @param hotelId
     * @return List<HotelRoom>
     */
    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);
    /**
     * 从database查找对应酒店对应房间的房间数
     * @param hotelId
     * @param roomType
     */
    int getRoomCurNum(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType);
    /**
     * 从database删除对应房间列
     * @param roomId
     */
    int deleteroom(Integer roomId);
    /**
     * 从database删除所有属于参数酒店id的房间
     * @param hotelId
     */
    int deleteRoomByHotelId(Integer hotelId);
}

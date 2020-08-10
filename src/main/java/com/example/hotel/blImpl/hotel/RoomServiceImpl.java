package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
        //直接交给data层完成逻辑
    }

    @Override
    public void insertRoomInfo(HotelRoom hotelRoom) {
        List<HotelRoom> allroom=retrieveHotelRoomInfo(hotelRoom.getHotelId());
        for(int i=0;i<allroom.size();i++){
            if(allroom.get(i).getRoomType().equals(hotelRoom.getRoomType())){
                roomMapper.deleteroom(allroom.get(i).getId());
            }
        }
        //检查酒店是否已存在要插入的房间类型，如果有将原先的删去
        roomMapper.insertRoom(hotelRoom);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomMapper.updateRoomInfo(hotelId,roomType,rooms);
        //直接交给data层完成逻辑
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomMapper.getRoomCurNum(hotelId,roomType);
        //直接交给data层完成逻辑
    }

    @Override
    public void deleteRoomByHotelId(Integer hotelId) {
        roomMapper.deleteRoomByHotelId(hotelId);
        //直接交给data层完成逻辑
    }
}

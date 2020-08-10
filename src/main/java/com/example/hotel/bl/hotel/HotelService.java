package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RateVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    ResponseVO addHotel(HotelVO hotelVO) throws ServiceException;


    /**
     * 预订酒店修改剩余客房信息
     * @param hotelId
     * @param roomType
     * @param
     */
    void updateRoomInfo(Integer hotelId, String roomType,Integer rooms);

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店剩余某种房间数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId,String roomType);

    /**
     * 删除酒店
     * @param hotelid
     * @return
     */
    ResponseVO deleteHotel(Integer hotelid);

    /**
     * 获取某管理员管理的酒店信息
     * @param managerId
     * @return
     */
    List<HotelVO> getHotelsByManagerId(Integer managerId);

    /**
     * 更新酒店评分信息
     * @param hotelid
     * @param rate
     */
    void addHotelNumber(Integer hotelid,double rate);
}

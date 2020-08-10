package com.example.hotel.bl.hotel;

import com.example.hotel.po.Hotel_Service;

import java.util.List;
/**
 * @Author: liBangGuo
 * @Date: 2020-06-04
 */
public interface HotelServiceService {

    /**
     * 增加酒店服务信息
     * @param hotel_service
     * @return
     */
    public int addService(Hotel_Service hotel_service);

    /**
     * 删除酒店服务信息
     * @param hotelId
     * @return
     */
    public int deleteService(Integer hotelId);

    /**
     * 获取酒店服务信息
     * @param hotelId
     * @return
     */
    public List<Hotel_Service> getServiceByHotelId(Integer hotelId);
}

package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel_Service;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Author: liBangGuo
 * @Date: 2020-06-04
 */
@Mapper
@Repository
public interface ServiceMapper {
    /**
     * 从database增加酒店服务列
     * @param hotel_service
     */
    public int addService(Hotel_Service hotel_service);
    /**
     * 从database删除所有对应酒店的服务
     * @param hotelId
     */
    public int deleteService(Integer hotelId);
    /**
     * 从database查找属于对应酒店的服务
     * @param hotelId
     */
    public List<Hotel_Service> getServiceByHotelId(Integer hotelId);
}

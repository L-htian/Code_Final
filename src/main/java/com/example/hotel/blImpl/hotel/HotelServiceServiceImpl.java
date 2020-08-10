package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelServiceService;
import com.example.hotel.po.Hotel_Service;
import com.example.hotel.data.hotel.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * @Author: liBangGuo
 * @Date: 2020-06-04
 */
@org.springframework.stereotype.Service
public class HotelServiceServiceImpl implements HotelServiceService {
    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public int addService(Hotel_Service hotel_service) {
        return serviceMapper.addService(hotel_service);
        //直接交给data层完成逻辑
    }

    @Override
    public int deleteService(Integer hotelId) {
        return serviceMapper.deleteService(hotelId);
        //直接交给data层完成逻辑
    }

    @Override
    public List<Hotel_Service> getServiceByHotelId(Integer hotelId) {
        return serviceMapper.getServiceByHotelId(hotelId);
        //直接交给data层完成逻辑
    }
}

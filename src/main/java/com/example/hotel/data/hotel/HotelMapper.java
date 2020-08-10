package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HotelMapper {
    /**
     * 添加酒店进入database
     * @param hotel
     * @return
     */
    int insertHotel(Hotel hotel);
    /**
     * 从database中得到所有的酒店
     * @return List<HotelVO>
     */
    List<HotelVO> selectAllHotel();
    /**
     * 从database中获得对应酒店
     * @param id
     * @return
     */
    HotelVO selectById(@Param("id") Integer id);
    /**
     * 在database中删除对应酒店
     * @param hotel
     * @return
     */
    int deleteHotel(Integer hotel);
    /**
     * 从database中找到对应managerId的酒店
     * @param managerId
     * @return  List<HotelVO>
     */
    List<HotelVO> getHotelsByManagerId(Integer managerId);
    /**
     * 在database中更改酒店评分并增加酒店评分人数
     * @param hotelid
     */
    int addRateNumber(double rate,Integer hotelid);
    /**
     * 在
     * @param name
     * @param phoneNumber
     * @return HotelVO
     */
    HotelVO isHotelExist(String name,String phoneNumber);

}

package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.RateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {
    /**
     * 从database增加预定列
     * @param order
     */
    int addOrder(Order order);
    /**
     * 从database得到所有订单
     * @return List<Order>
     */
    List<Order> getAllOrders();
    /**
     * 从database得到所有属于对应用户的订单
     * @param userid
     * @return List<Order>
     */
    List<Order> getUserOrders(@Param("userid") int userid);
    /**
     * 从database改变对应订单的状态并记录时间
     * @param orderid
     * @param annulDate
     */
    int annulOrder(@Param("orderid") int orderid,String annulDate);
    /**
     * 从database得到对应订单id的订单信息
     * @param orderid
     * @return Order
     */
    Order getOrderById(@Param("orderid") int orderid);
    /**
     * 从database得到属于对应酒店的评论
     * @param hotelId
     * @return List<RateVO>
     */
    List<RateVO> getCommentsByHotelId(Integer hotelId);
    /**
     * 从database用户在对应酒店的订单
     * @param userId
     * @param hotelId
     * @return List<Order>
     */
    List<Order> getUserHotelOrders(Integer userId,Integer hotelId);
    /**
     * 从database得到对应酒店的订单
     * @param hotelId
     * @return List<Order>
     */
    List<Order> getHotelOrders(Integer hotelId);
    /**
     * 从database更改对应订单的评论
     * @param orderid
     * @param rate
     * @param comment
     */
    int rateHotel(Integer orderid,double rate,String comment);
    /**
     * 从database更新酒店状态
     * @param id
     */
    int updateOrder(Integer id);
}

package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.RateVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RateVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 查看酒店管理员的所有订单
     * @param managerId
     * @return
     */
    List<Order> getAllOrdersByManagerId(Integer managerId);

    /**
     * 获取酒店评论
     * @param hotelId
     * @return
     */
    List<RateVO> getCommentsByHotelId(Integer hotelId);

    /**
     * 获取某用户在某酒店的订单
     * @param userId
     * @param hotelId
     * @return
     */
    List<Order> getuserHotelOrders(Integer userId,Integer hotelId);

    /**
     * 获取订单详细信息
     * @param id
     * @return
     */
    Order orderDetailbyId(Integer id);

    /**
     * 更新订单评分信息
     * @param orderid
     * @param rateVO
     * @return
     */
    ResponseVO rateHotel(Integer orderid,RateVO rateVO);

    /**
     * 更新订单状态信息
     * @return
     */
    int updateOrder();
}

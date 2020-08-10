package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 接收前端API的调用，调用业务逻辑层的实现
 * @Author: chenyizong
 * @Date: 2020-03-04
 */

@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{data.userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable("data.userid") int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable("orderid") int orderid){
        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{managerId}/getAllOrdersByManagerId")
    public ResponseVO getAllOrdersByManagerId(@PathVariable("managerId") Integer managerId) {
        return ResponseVO.buildSuccess(orderService.getAllOrdersByManagerId(managerId));   
    }

    @GetMapping("/{hotelId}/getCommentsByHotelId")
    public ResponseVO getCommentsByHotelId(@PathVariable("hotelId") Integer hotelId){
        return ResponseVO.buildSuccess(orderService.getCommentsByHotelId(hotelId));
    }

    @GetMapping("/{data.userId}/getHotelOrders")
    public ResponseVO getHotelOrders( @RequestParam Integer hotelId, @PathVariable("data.userId") Integer userId){
        return ResponseVO.buildSuccess(orderService.getuserHotelOrders(userId,hotelId));
    }

    @GetMapping("/{params.id}/orderDetail")
    public ResponseVO orderDetailById(@PathVariable("params.id")Integer id){
        return ResponseVO.buildSuccess(orderService.orderDetailbyId(id));
    }

    @PostMapping("/{data.id}/rateHotel")
    public ResponseVO rateHotel(@PathVariable("data.id")Integer orderid,@RequestBody RateVO rateVO){
        return  orderService.rateHotel(orderid,rateVO);
    }

}

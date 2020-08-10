package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.RateVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = hotelService.getRoomCurNum(orderVO.getHotelId(),orderVO.getRoomType());
        if(accountService.getUserInfo(orderVO.getUserId()).getCredit()<0){
            return ResponseVO.buildFailure("信用值过低");
        }
        //信用值过低不能预定
        if(reserveRoomNum>curNum){
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        //房间不够就显示房间不够信息并且无法预定
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            order.setIsRating("NO");
            //填充订单信息
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(orderVO.getHotelId(),orderVO.getRoomType(),orderVO.getRoomNum());
            //预定后酒店当前房间数更新
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
       return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
        //直接交给data层完成逻辑
    }

    @Override
    public List<Order> getUserOrders(int userid) {
        return orderMapper.getUserOrders(userid);
        //直接交给data层完成逻辑
    }

    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        try{
            Order annul=orderMapper.getOrderById(orderid);
            Integer curNum=annul.getRoomNum();
            curNum=-1*curNum;
            Integer hotelId=annul.getHotelId();
            String roomType=annul.getRoomType();
            LocalDate checkintime=LocalDate.parse(annul.getCheckInDate());
            hotelService.updateRoomInfo(hotelId,roomType,curNum);
            LocalDate today=LocalDate.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateStr = today.format(fmt);
            orderMapper.annulOrder(orderid,dateStr);
            if(today.isBefore(checkintime.minusDays(-1))&&today.isAfter(checkintime.minusDays(2))){
                Integer minuscredit=(int)(-1*annul.getPrice()/2);
                String email=accountService.getUserInfo(annul.getUserId()).getEmail();
                accountService.creditdeposit(email,minuscredit);
                //取消订单在条件符合的情况（即在入住时间前一天）下会扣除信用值
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrdersByManagerId(Integer managerId) {
        List<HotelVO> hotels = hotelService.getHotelsByManagerId(managerId);
        Integer[] hotelids=new Integer[hotels.size()];
        for(int i=0;i<hotelids.length;i++){
            hotelids[i]=hotels.get(i).getId();
        }
        //得到酒店管理员所管理的酒店id
        List<Order> orders=new ArrayList<Order>();
        for(int i=0;i<hotelids.length;i++){
            orders.addAll(orderMapper.getHotelOrders(hotelids[i]));
        }
        //根据这些酒店id的得到订单
        return orders;
    }

    @Override
    public List<RateVO> getCommentsByHotelId(Integer hotelId) {
        return orderMapper.getCommentsByHotelId(hotelId);
        //直接交给data层完成逻辑
    }

    @Override
    public List<Order> getuserHotelOrders(Integer userId, Integer hotelId) {
        return orderMapper.getUserHotelOrders(userId,hotelId);
        //直接交给data层完成逻辑
    }

    @Override
    public Order orderDetailbyId(Integer id) {
        return orderMapper.getOrderById(id);
        //直接交给data层完成逻辑
    }

    @Override
    public ResponseVO rateHotel(Integer orderid, RateVO rateVO) {
        Order order=orderMapper.getOrderById(orderid);
        LocalDate checkouttime=LocalDate.parse(order.getCheckOutDate());
        if(checkouttime.isAfter(LocalDate.now())){
            return ResponseVO.buildFailure("订单未完成");
        }
        //订单未完成无法评论
        else if(order.getIsRating().equals("Yes")){
            return ResponseVO.buildFailure("订单已评论");
        }
        //订单一评论无法修改
        else {
            double rate_before=hotelService.retrieveHotelDetails(order.getHotelId()).getRate();
            int ratenumber=hotelService.retrieveHotelDetails(order.getHotelId()).getRateNumbers();
            double rate_after=(rate_before*ratenumber+rateVO.getRate())/(ratenumber+1);
            hotelService.addHotelNumber(order.getHotelId(),rate_after);
            orderMapper.rateHotel(orderid,rateVO.getRate(),rateVO.getComment());
            //更新酒店分数和评分人数
            return ResponseVO.buildSuccess();
        }
    }

    @Override
    public int updateOrder() {
        List<Order> allOrder=orderMapper.getAllOrders();
        LocalDate now=LocalDate.now();
        for(int i=0;i<allOrder.size();i++){
            LocalDate checkInTime=LocalDate.parse(allOrder.get(i).getCheckInDate());
            if(now.isAfter(checkInTime) && allOrder.get(i).getOrderState().equals("已预订")){
                orderMapper.updateOrder(allOrder.get(i).getId());
                //订单在达到入住时间后需要状态从已预定到已执行
            }
        }
        return 0;
    }

}

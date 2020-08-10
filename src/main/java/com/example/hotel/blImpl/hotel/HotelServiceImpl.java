package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.User;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import com.example.hotel.bl.hotel.HotelServiceService;
import com.example.hotel.po.Hotel_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelServiceService serviceService;
    @Override
    public ResponseVO addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || !manager.getUserType().equals(UserType.HotelManager)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        //检查管理员权限
        if(hotelMapper.isHotelExist(hotelVO.getName(),hotelVO.getPhoneNum())!=null){
            return ResponseVO.buildFailure("酒店已存在");
        }
        //检查酒店是否已存在
        System.out.println(hotelMapper.isHotelExist(hotelVO.getName(),hotelVO.getPhoneNum()));
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        List<String> hotelServices=hotelVO.getHotelService();
        hotel.setRateNumbers(0);
        //填充hotel的信息
        hotelMapper.insertHotel(hotel);
        //交给data层完成进一步逻辑
        for(int i=0;i<hotelServices.size();i++){
            String serviceName=hotelServices.get(i);
            Hotel_Service hotel_service=new Hotel_Service();
            hotel_service.setHotelId(hotel.getId());
            hotel_service.setServiceName(serviceName);
            serviceService.addService(hotel_service);
            //增加酒店拥有的酒店服务
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomService.updateRoomInfo(hotelId,roomType,rooms);
        //直接交给data层逻辑
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomService.getRoomCurNum(hotelId,roomType);
        //直接交给data层实现进一步逻辑
    }

    @Override
    public ResponseVO deleteHotel(Integer hotelid) {
        try {
            hotelMapper.deleteHotel(hotelid);
            serviceService.deleteService(hotelid);
            roomService.deleteRoomByHotelId(hotelid);
            //在三个表中都删除相关酒店信息
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<HotelVO> getHotelsByManagerId(Integer managerId) {
        List<HotelVO> hotels=hotelMapper.getHotelsByManagerId(managerId);
        //交给data层解决进一步逻辑，获得酒店信息
        for(int i=0;i<hotels.size();i++){
            Integer hotelId=hotels.get(i).getId();
            List<Hotel_Service> hotelServices=serviceService.getServiceByHotelId(hotelId);
            List<String> hotelServiceS= hotelServices.stream().map(r -> {
                String hotelService=new String();
                hotelService=r.getServiceName();
                return hotelService;
            }).collect(Collectors.toList());
            hotels.get(i).setHotelServiece(hotelServiceS);
        }
        //将酒店服务与酒店绑定传递
        return hotelMapper.getHotelsByManagerId(managerId);
    }

    @Override
    public void addHotelNumber(Integer hotelid, double rate) {
        hotelMapper.addRateNumber(rate,hotelid);
        //直接交给data层进行进一步逻辑
    }


    @Override
    public List<HotelVO> retrieveHotels() {
        return hotelMapper.selectAllHotel();
        //直接交给data层进行进一步逻辑
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            return roomVO;
        }).collect(Collectors.toList());
        //将酒店房间与酒店绑定
        hotelVO.setRooms(roomVOS);
        List<Hotel_Service> hotelServices=serviceService.getServiceByHotelId(hotelId);
        List<String> hotelServiceS=hotelServices.stream().map(r ->{
            String hotelService=new String();
            hotelService=r.getServiceName();
            return hotelService;
        }).collect(Collectors.toList());
        hotelVO.setHotelServiece(hotelServiceS);
        //将酒店服务与酒店绑定
        return hotelVO;
    }
}

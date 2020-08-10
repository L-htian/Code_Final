package com.example.hotel.vo;

public class HotelMultiplyRoomsCouponVO extends CouponVO {

    private Integer roomNum;

    private double discount;

    public Integer getRoomNum() { return roomNum; }

    public void setRoomNum(Integer roomNum) { this.roomNum = roomNum; }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

package com.example.hotel.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HotelFestivalCouponVO extends CouponVO {

    //YYYY-MM-DD
    private String startTime;
    //YYYY-MM-DD
    private String endTime;

    private double discount;

    public void setStartTime(String startTime) { this.startTime =startTime ; }

    public LocalDate getStartTime() { return LocalDate.parse(startTime,DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    public void setEndTime(String endTime) { this.endTime =endTime ; }

    public LocalDate getEndTime() { return LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

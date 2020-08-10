package com.example.hotel.enums;

public enum RoomType {
    BigBed("大床房"),
    DoubleBed("双床房"),
    Family("家庭房"),
    Business("商务房"),
    President("豪华总统套房"),
    Loft("阁楼房"),
    ESports("电竞房"),
    Hour("钟点房"),
    OceanView("海景房"),
    RiverView("江景房"),
    LakeView("湖景房"),
    Lovers("情侣房");
    private String value;

    RoomType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

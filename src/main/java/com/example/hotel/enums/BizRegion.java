package com.example.hotel.enums;

public enum BizRegion {
    XiDan("西单"),
    XinJieKou("新街口"),
    JieFangBei("解放碑");

    private String value;

    BizRegion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

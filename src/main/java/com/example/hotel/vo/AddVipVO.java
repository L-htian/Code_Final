package com.example.hotel.vo;

import java.time.LocalDate;

public class AddVipVO {
    private String userEmail;
    private LocalDate birthday;


    public LocalDate getBirthday() {
        return birthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

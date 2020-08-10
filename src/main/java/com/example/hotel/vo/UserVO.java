package com.example.hotel.vo;

import com.example.hotel.enums.UserType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author fjj
 * @date 2019/4/11 3:22 PM
 */
public class UserVO {
    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private double credit;
    private UserType userType;
    private boolean vip;
    private String birthday="2000-01-01";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) { this.credit = credit; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() { return userType; }

    public void setUserType(UserType userType) { this.userType = userType; }

    public boolean getVip() { return vip; }

    public void setVip(boolean vip) { this.vip = vip; }

    public LocalDate getBirthday() { return LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")); }

    public void setBirthday(String birthday) { this.birthday = birthday; }
}

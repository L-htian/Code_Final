package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AccountService accountService;

    @Override
    public ResponseVO addManager(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        if(accountService.isExist(userForm.getEmail())){
            return ResponseVO.buildFailure("邮箱已注册");
        }
        //检查账号是否已存在
        user.setUserType(UserType.HotelManager);
        try {
            adminMapper.addManager(user);
            //调用data层方法实现进一步逻辑
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllManagers() {
        return adminMapper.getAllManagers();
        //调用data层方法实现进一步逻辑
    }

    @Override
    public ResponseVO addUser(UserForm userForm) {
        if(accountService.isExist(userForm.getEmail())){
            return ResponseVO.buildFailure("邮箱已注册");
        }
        //检查账号是否已存在
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setCredit(userForm.getCredit());
        user.setPhoneNumber(userForm.getPhoneNumber());
        if(user.getPhoneNumber().length()!=11){
            return ResponseVO.buildFailure("手机号格式不正确");
        }
        user.setUserName(userForm.getUserName());
        user.setUserType(UserType.Client);
        try {
            adminMapper.addUser(user);
            //调用data层方法实现进一步逻辑
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllUsers() {
        return adminMapper.getAllUsers();
        //调用data层方法实现进一步逻辑
    }

    @Override
    public ResponseVO addMarketer(UserForm userForm) {
        if(accountService.isExist(userForm.getEmail())){
            return ResponseVO.buildFailure("邮箱已注册");
        }
        //检查账号是否已存在
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(UserType.Marketer);
        try {
            adminMapper.addMarketer(user);
            //调用data层方法实现进一步逻辑
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllMarketers() {
        return adminMapper.getAllMarketerts();
        //调用data层方法实现进一步逻辑
    }

    @Override
    public ResponseVO deleteUser(Integer userid) {
        try {
            adminMapper.deleteUser(userid);
            //调用data层方法实现进一步逻辑
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseVO.buildSuccess(true);
    }

}

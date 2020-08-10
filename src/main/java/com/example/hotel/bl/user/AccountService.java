package com.example.hotel.bl.user;

import com.example.hotel.po.User;
import com.example.hotel.vo.*;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    User login(UserForm userForm);

    /**
     * 获取用户个人信息
     * @param id
     * @return
     */
    User getUserInfo(int id);

    /**
     * 更新用户个人信息
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
    ResponseVO updateUserInfo(int id, String password,String username,String phonenumber);

    /**
     * 更新客户信用信息
     * @param email
     * @param deposit
     * @return
     */
    ResponseVO creditdeposit(String email,Integer deposit);

    /**
     * 判断用户是否存在
     * @param email
     * @return
     */
    boolean isExist(String email);

    /**
     * 设置用户VIP信息
     * @param addVipVO
     * @return
     */
    ResponseVO setVip(AddVipVO addVipVO);
}

package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface AdminService {

    /**
     * 添加酒店管理人员账号
     * @param userForm
     * @return
     */
    ResponseVO addManager(UserForm userForm);

    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    List<User> getAllManagers();

    /**
     * 添加客户账号
     * @param userForm
     * @return
     */
    ResponseVO addUser(UserForm userForm);

    /**
     * 获得所有客户信息
     * @return
     */
    List<User> getAllUsers();

    /**
     * 添加网站营销人员账号
     * @param userForm
     * @return
     */
    ResponseVO addMarketer(UserForm userForm);

    /**
     * 获得所有网站营销人员信息
     * @return
     */
    List<User> getAllMarketers();

    /**
     * 删除用户账号
     * @param userid
     * @return
     */
    ResponseVO deleteUser(Integer userid);
}

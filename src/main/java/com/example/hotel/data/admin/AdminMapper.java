package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     * 添加酒店管理人员账号
     * @param user
     * @return
     */
    int addManager(User user);
    /**
     * 得到所有酒店管理员账号
     * @return List<User>
     */
    List<User> getAllManagers();
    /**
     * 添加用户账号
     * @param user
     * @return
     */
    int addUser(User user);
    /**
     * 得到所有用户账号
     * @return List<User>
     */
    List<User> getAllUsers();
    /**
     * 添加网站营销人员账号
     * @param user
     * @return
     */
    int addMarketer(User user);
    /**
     * 得到所有酒店管理员账号
     * @return List<User>
     */
    List<User> getAllMarketerts();
    /**
     * 删除对应userid的账号
     * @param userid
     */
    int deleteUser(Integer userid);
}

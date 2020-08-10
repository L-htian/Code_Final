package com.example.hotel.controller.user;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import com.example.hotel.vo.CreditdepositVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 接收前端API的调用，调用业务逻辑层的实现
 * @Author: chenyizong
 * @Date: 2020-03-04
 */

@RestController()
@RequestMapping("/api/user")
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm) {
        orderService.updateOrder();
        User user = accountService.login(userForm);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);

    }

    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserVO userVO) {
        return accountService.registerAccount(userVO);
    }


    @GetMapping("/{id}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable int id) {
        User user = accountService.getUserInfo(id);
        if(user==null){
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    @PostMapping("/{id}/userInfo/update")
    public ResponseVO updateInfo(@RequestBody UserInfoVO userInfoVO,@PathVariable int id){
        return accountService.updateUserInfo(id,userInfoVO.getPassword(),userInfoVO.getUserName(),userInfoVO.getPhoneNumber());
    }

    @PostMapping("/creditDeposit")
    public ResponseVO creditDeposit(@RequestBody CreditdepositVO creditdepositVO){
        return accountService.creditdeposit(creditdepositVO.getEmail(),creditdepositVO.getDeposit());
    }

    @PostMapping("/setVip")
    public ResponseVO setVip(@RequestBody AddVipVO addVipVO){
        return accountService.setVip(addVipVO);
    }
}
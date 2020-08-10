package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;
import com.example.hotel.vo.AddVipVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        if(isExist(userVO.getEmail())){
            return ResponseVO.buildFailure("邮箱已注册");
        }
        //账号已注销无法继续注册
        if(user.getPhoneNumber().length()!=11){
            return ResponseVO.buildFailure("手机号格式不正确");
        }
        //手机号长度不正确无法继续注册
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
            //校验密码不正确则无法登入
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        //直接交给data层完成逻辑
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phonenumber) {
        try {
            accountMapper.updateAccount(id, password, username, phonenumber);
            //直接交给data层完成逻辑
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO creditdeposit(String email, Integer deposit) {
        String error="账号不存在";
        try {
            accountMapper.creditDeposit(email,deposit);
            //直接交给data层完成逻辑
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(error);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public boolean isExist(String email) {
        if(accountMapper.getAccountByName(email)==null){
            return false;
        }
        else {
            return true;
        }
        //如果账号已存在返回true，否则返回false
    }

    @Override
    public ResponseVO setVip(AddVipVO addVipVO){
        if(isExist(addVipVO.getUserEmail())){
            accountMapper.setVip(addVipVO.getUserEmail(),addVipVO.getBirthday());
            //直接交给data层完成逻辑
            return ResponseVO.buildSuccess(true);
        }
        else {
            return ResponseVO.buildFailure("账号不存在");
            //账号不存在就显示提示信息
        }

    }
}

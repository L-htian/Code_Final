package com.example.hotel.bl.user;

import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;
/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	@Autowired
	AccountService accountService;

	/**
	 * 测试registerAccount方法
	 */
	@Test
	public void registerAccount() {
		UserVO userVO = new UserVO();
		userVO.setUserName("八甲基环丁烷");
		userVO.setEmail("888888888@qq.com");
		userVO.setPassword("123456");
		userVO.setPhoneNumber("9418");
		userVO.setCredit(100);
		userVO.setVip(true);
		userVO.setBirthday("2000-08-17");
		userVO.setUserType(UserType.Client);
		ResponseVO responseVO = accountService.registerAccount(userVO);
		ResponseVO excepted = ResponseVO.buildSuccess();
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
	}

	/**
	 * 测试login方法
	 */
	@Test
	public void login() {
		UserForm userForm = new UserForm();
		userForm.setEmail("123");
		userForm.setPassword("456");
		assertNull(accountService.login(userForm));
		userForm.setEmail("1012681@qq.com");
		userForm.setPassword("123456");
		int ac = accountService.login(userForm).getId();
		assertEquals(4,ac);
	}

	/**
	 * 测试getUserInfo方法
	 */
	@Test
	public void getUserInfo() {
		User user = accountService.getUserInfo(4);
		User excepted = new User();
		excepted.setEmail("1012681@qq.com");
		assertEquals(excepted.getEmail(),user.getEmail());
	}

	/**
	 * 测试updateUserInfo方法
	 */
	@Test
	public void updateUserInfo() {
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		User user = accountService.getUserInfo(6);
		ResponseVO responseVO = accountService.updateUserInfo(6,"123","456","789");
		User now = accountService.getUserInfo(6);
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
		assertEquals("123",now.getPassword());
		assertEquals("456",now.getUserName());
		assertEquals("789",now.getPhoneNumber());
		accountService.updateUserInfo(6,user.getPassword(),user.getUserName(),user.getPhoneNumber());
	}

	/**
	 * 测试creditdeposit方法
	 */
	@Test
	public void creditdeposit() {
		User user = accountService.getUserInfo(5);
		double credit1 = user.getCredit();
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		ResponseVO responseVO = accountService.creditdeposit(user.getEmail(),50);
		assertEquals(excepted.getContent(),responseVO.getContent());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getMessage(),responseVO.getMessage());
		user = accountService.getUserInfo(5);
		double credit2 = user.getCredit();
		assertTrue(credit2-credit1<50.01);
		assertTrue(credit2-credit1>49.99);
		accountService.creditdeposit(user.getEmail(),-50);
	}
}
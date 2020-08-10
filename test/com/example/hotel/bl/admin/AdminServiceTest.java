package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: MiHao
 * @Date: 2020-06-04
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
	@Autowired
	private AdminService adminService;

	/**
	 * 测试addManager方法
	 */
	@Test
	public void addManager() {
		UserForm userForm = new UserForm();
		userForm.setUserName("91MRLI");
		userForm.setPhoneNumber("114514");
		userForm.setPassword("1919810");
		userForm.setEmail("74751@qq.com");
		userForm.setCredit(100.00);
		//设置要添加的用户信息

		ResponseVO responseVO = adminService.addManager(userForm);
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		assertEquals(excepted.getMessage(),responseVO.getMessage());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getContent(),responseVO.getContent());
	}

	/**
	 * 测试getAllManagers方法
	 */
	@Test
	public void getAllManagers() {
		List<User> managers = adminService.getAllManagers();
	}

	/**
	 * 测试addUser方法
	 */
	@Test
	public void addUser() {
		UserForm userForm = new UserForm();
		userForm.setUserName("91MRLI");
		userForm.setPhoneNumber("114514");
		userForm.setPassword("1919810");
		userForm.setEmail("74751@qq.com");
		userForm.setCredit(100.00);
		//设置要添加的用户信息

		ResponseVO responseVO = adminService.addUser(userForm);
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		assertEquals(excepted.getMessage(),responseVO.getMessage());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getContent(),responseVO.getContent());
	}

	/**
	 * 测试getAllUsers方法
	 */
	@Test
	public void getAllUsers() {
		List<User> managers = adminService.getAllUsers();
	}

	/**
	 * 测试addMarketer方法
	 */
	@Test
	public void addMarketer() {
		UserForm userForm = new UserForm();
		userForm.setUserName("91MRLI");
		userForm.setPhoneNumber("114514");
		userForm.setPassword("1919810");
		userForm.setEmail("74751@qq.com");
		userForm.setCredit(100.00);
		//设置要添加的用户信息

		ResponseVO responseVO = adminService.addMarketer(userForm);
		ResponseVO excepted = ResponseVO.buildSuccess(true);
		assertEquals(excepted.getMessage(),responseVO.getMessage());
		assertEquals(excepted.getSuccess(),responseVO.getSuccess());
		assertEquals(excepted.getContent(),responseVO.getContent());
	}

	/**
	 * 测试getAllMarketers方法
	 */
	@Test
	public void getAllMarketers() {
		List<User> managers = adminService.getAllMarketers();
	}

	/**
	 * 测试deleteUser方法
	 */
	@Test
	public void deleteUser() {
		adminService.deleteUser(8);
	}
}
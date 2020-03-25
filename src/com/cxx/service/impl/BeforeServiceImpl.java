package com.cxx.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxx.mapper.BeforeMapper;
import com.cxx.mapper.OrdersMapper;
import com.cxx.pojo.Menus;
import com.cxx.pojo.Notice;
import com.cxx.pojo.Orders;
import com.cxx.pojo.Sales;
import com.cxx.pojo.ShoppingCar;
import com.cxx.pojo.Users;
import com.cxx.service.BeforeService;

@Service
public class BeforeServiceImpl implements BeforeService{

	@Resource
	private BeforeMapper beforeMapper;
	
	@Override
	public List<Notice> selAllNotice() {
		return beforeMapper.selAllNotice();
	}
	
	@Override
	public Notice selNoticeByName(String noticeName) {
		return beforeMapper.selNoticeByName(noticeName);
	}

	@Override
	public List<Sales> selMaxSales() {
		return beforeMapper.selMaxSales();
	}

	@Override
	public Menus selByMenusName(String menusName) {
		return beforeMapper.selByMenusName(menusName);
	}

	@Override
	public Users login(Users users) {
		return beforeMapper.login(users);
	}

	@Override
	public Users selByUserName(String userName) {
		return beforeMapper.selByUserName(userName);
	}

	@Override
	public Integer insUser(Users users) {
		return beforeMapper.insUser(users);
	}


	@Override
	public List<Orders> selOrdersByUserName(String userName) {
		return beforeMapper.selOrdersByUserName(userName);
	}

	@Override
	public Integer UpdUser(Users users) {
		return beforeMapper.UpdUser(users);
	}

	@Override
	public List<Orders> selOrdersByDelivery(String userName, String delivery) {
		return beforeMapper.selOrdersByDelivery(userName, delivery);
	}

	@Override
	public List<Orders> selOrdersByMenusName(String userName, String menusName) {
		return beforeMapper.selOrdersByMenusName(userName, menusName);
	}

	@Override
	public List<Orders> selOrdersByDay(String userName, String day) {
		return beforeMapper.selOrdersByDay(userName, day);
	}

	@Override
	public Integer insOrders(int userId, int menusId, String orderMenusum) {
		return beforeMapper.insOrders(userId, menusId, orderMenusum);
	}

	
}

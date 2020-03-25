package com.cxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cxx.mapper.OrdersMapper;
import com.cxx.pojo.Orders;
import com.cxx.service.OrdersService;
import com.cxx.util.Page;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Resource
	private OrdersMapper ordersMapper;

	@Override
	public List<Orders> selOrdersByPage(Page page) {
		return ordersMapper.selOrdersByPage(page);
	}

	@Override
	public int selCount() {
		return ordersMapper.selCount();
	}

	@Override
	public Integer delOrder(int orderId) {
		return ordersMapper.delOrder(orderId);
	}

	@Override
	public Integer updOrderDeli(int orderId) {
		return ordersMapper.updOrderDeli(orderId);
	}

	@Override
	public List<Orders> selOrdersByday(String orderTimes,int pageStart,int pageNumber) {
		return ordersMapper.selDayByPage(orderTimes, pageStart,pageNumber);
	}

	@Override
	public int selDayCount(String orderTimes) {
		return ordersMapper.selDayCount(orderTimes);
	}

	@Override
	public List<Orders> selByMenusName(String menusName) {
		return ordersMapper.selByMenusName(menusName);
	}

	@Override
	public List<Orders> selByUserId(int userId) {
		return ordersMapper.selByUserId(userId);
	}

	@Override
	public List<Orders> selByOrderTimes(String orderTimes) {
		return ordersMapper.selByOrderTimes(orderTimes);
	}
	
	
	
}

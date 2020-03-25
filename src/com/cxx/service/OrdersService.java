package com.cxx.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.Orders;
import com.cxx.util.Page;

public interface OrdersService {
	/**
	 * 分页查询订单信息
	 * @param page
	 * @return
	 */
	List<Orders> selOrdersByPage(Page page);
	
	/**
	 * 查询数据库中有几条数据
	 * @return
	 */
	int selCount();
	
	/**
	 * 删除订单
	 * @return
	 */
	@Delete("delete from orders where orderId=#{orderId}")
	Integer delOrder(int orderId);
	
	/**
	 * 确认订单
	 * @param orderId
	 * @return
	 */
	@Update("update orders set orderDelivery=1 where orderId=#{orderId}")
	Integer updOrderDeli(int orderId);
	
	/**
	 * 分页查询某日销售量
	 * @param orderTimes
	 * @return
	 */
	List<Orders> selOrdersByday(String orderTimes,int pageStart,int pageNumber);
	
	
	/**
	 * 查询某天的订单数量
	 * @param orderTimes
	 * @return
	 */
	int selDayCount(String orderTimes);
	
	/**
	 * 根据菜品名字查询
	 * @param menusName
	 * @return
	 */
	List<Orders> selByMenusName(String menusName);
	
	/**
	 * 根据用户Id查询
	 * @param userId
	 * @return
	 */
	
	List<Orders> selByUserId(int userId);
	
	/**
	 * 根据时间查询订单信息
	 * @param 
	 * @return
	 */
	List<Orders> selByOrderTimes(String orderTimes);
}

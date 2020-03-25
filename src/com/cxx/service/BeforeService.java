package com.cxx.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.Menus;
import com.cxx.pojo.Notice;
import com.cxx.pojo.Orders;
import com.cxx.pojo.Sales;
import com.cxx.pojo.ShoppingCar;
import com.cxx.pojo.Users;
import com.cxx.util.Page;

public interface BeforeService {
	
	/**
	 * 查询所有通告信息
	 * @param page
	 * @return
	 */
	List<Notice> selAllNotice();
	
	
	/**
	 * 根据通告名字查询数据
	 * @param noticeName
	 * @return
	 */
	Notice selNoticeByName(String noticeName);
	
	
	/**
	 * 销售排行榜
	 * @return
	 */
	List<Sales> selMaxSales();
	
	
	/**
	 * 根据菜单名查询菜单信息
	 * @param menusName
	 * @return
	 */
	Menus selByMenusName(String menusName);
	
	/**
	 * 用户登录
	 * @param users
	 * @return
	 */
	Users login(Users users);
	
	/**
	 * 根据用户名查询账号
	 * @param userName
	 * @return
	 */
	Users selByUserName(String userName);
	
	/**
	 * 更新用户信息
	 * @param users
	 * @return
	 */
	Integer UpdUser(Users users);

	/**
	 * 注册，添加新用户
	 * @param users
	 * @return
	 */
	Integer insUser(Users users);
	
	
	
	
	/**
	 * 根据用户名查找订单
	 * @param menusName
	 * @return
	 */
	List<Orders> selOrdersByUserName(String userName);
	
	
	/**
	 * 根据用户名及配送状态查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	List<Orders> selOrdersByDelivery(String userName,String delivery);
	
	/**
	 * 根据用户名及菜单名称查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	List<Orders> selOrdersByMenusName(String userName,String menusName);
	
	
	/**
	 * 根据用户名及配送状态查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	List<Orders> selOrdersByDay(String userName,String day);
	
	
	/**
	 * 提交订单
	 * @return
	 */
	Integer insOrders(int userId,int menusId,String orderMenusum);
	
}

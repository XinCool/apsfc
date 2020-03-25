package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.annotation.Order;

import com.cxx.pojo.Orders;
import com.cxx.util.Page;

public interface OrdersMapper {
	/**
	 * 分页查询订单相关信息
	 * @param page
	 * @return
	 */
	@Select("select * from orders order by orderTimes desc limit #{pageStart},#{pageNumber}")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userId",property="users",one=@One(select="com.cxx.mapper.UsersMapper.selById")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selOrdersByPage(Page page);
	
	/**
	 * 查询数据库中有几条数据
	 * @return
	 */
	@Select("select count(*) from orders")
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
	
	//@Select("select m.menusName,o.orderMenusum,m.menusPricel,m.menusPricel*o.orderMenusum as sumMoney,o.orderTimes from orders as o,menus as m where o.menuId=m.menusId and o.orderTimes like #{orderTimes}  group by m.menusName order by o.orderMenusum desc")
	/**
	 * 分页查询某日销售量
	 * @param orderTimes
	 * @return
	 */
	@Select("select * from orders where orderTimes like #{0} order by orderTimes desc limit #{1},#{2} ")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userId",property="users",one=@One(select="com.cxx.mapper.UsersMapper.selById")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selDayByPage(String times,int pageStart,int pageNumber);
	
	/**
	 * 查询某天的订单数量
	 * @param orderTimes
	 * @return
	 */
	@Select("select count(*) from orders where orderTimes like #{0}")
	int selDayCount(String orderTimes);
	
	/**
	 * 根据菜品名字查询
	 * @param menusName
	 * @return
	 */
	@Select("select * from orders o join menus m on o.menusId=m.menusId where m.menusName=#{0} order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userId",property="users",one=@One(select="com.cxx.mapper.UsersMapper.selById")),
			@Result(column="menusName",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selByName"))
	})
	List<Orders> selByMenusName(String menusName);
	
	/**
	 * 根据用户Id查询
	 * @param userId
	 * @return
	 */
	@Select("select * from orders where userId=#{userId} order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userId",property="users",one=@One(select="com.cxx.mapper.UsersMapper.selById")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selByUserId(int userId);
	
	/**
	 * 根据时间查询订单信息
	 * @param 
	 * @return
	 */
	@Select("select * from orders where orderTimes like #{0} order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userId",property="users",one=@One(select="com.cxx.mapper.UsersMapper.selById")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selByOrderTimes(String orderTimes);
	
	
	
}

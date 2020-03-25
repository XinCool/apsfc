package com.cxx.mapper;

import java.util.List;

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

public interface BeforeMapper {
	
	/**
	 * 查询所有通告信息
	 * @param page
	 * @return
	 */
	@Select("select * from notice order by noticeTimes desc")
	List<Notice> selAllNotice();
	
	/**
	 * 根据通告名字查询数据
	 * @param noticeName
	 * @return
	 */
	@Select("select * from notice where noticeName=#{noticeName}")
	Notice selNoticeByName(String noticeName);
	
	/**
	 * 销售排行榜
	 * @return
	 */
	@Select("select count(orderMenusum) sumSales,m.menusName menusName from orders o join menus m on o.menusId=m.menusId group by m.menusName order by sumSales desc")
	List<Sales> selMaxSales();
	
	
	/**
	 * 根据菜单名查询菜单信息
	 * @param menusName
	 * @return
	 */
	@Select("select * from menus join types on menus.typeId=types.typeId where menusName=#{menusName}")
	Menus selByMenusName(String menusName);
	
	
	/**
	 * 用户登录
	 * @param users
	 * @return
	 */
	@Select("select * from users where userName=#{userName} and userPwd=#{userPwd}")
	Users login(Users users);
	
	/**
	 * 根据用户名查询账号
	 * @param userName
	 * @return
	 */
	@Select("select * from users where userName=#{0}")
	Users selByUserName(String userName);
	
	/**
	 * 注册，添加新用户
	 * @param users
	 * @return
	 */
	@Insert("insert into users values(default,#{userName},#{userPwd},#{userRealname},#{userSex},#{userAge},#{userCard},#{userAddress},#{userPhone},#{userEmail},#{userCode},default)")
	Integer insUser(Users users);
	
	
	/**
	 * 更新用户信息
	 * @param users
	 * @return
	 */
	@Update("update users set userPwd=#{userPwd},userRealname=#{userRealname},userSex=#{userSex},userAge=#{userAge},userCard=#{userCard},userAddress=#{userAddress},userPhone=#{userPhone},userEmail=#{userEmail},userCode=#{userCode} where userName=#{userName}")
	Integer UpdUser(Users users);
	
	
	/**
	 * 根据用户名查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	@Select("select *  from orders o join users u on o.userId=u.userId where u.userName=#{0} order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userName",property="users",one=@One(select="com.cxx.mapper.BeforeMapper.selByUserName")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selOrdersByUserName(String userName);
	
	/**
	 * 根据用户名及配送状态查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	@Select("select *  from orders o join users u on o.userId=u.userId where u.userName=#{0} and orderDelivery=#{1}  order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userName",property="users",one=@One(select="com.cxx.mapper.BeforeMapper.selByUserName")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selOrdersByDelivery(String userName,String delivery);
	
	
	/**
	 * 根据用户名及菜单名称查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	@Select("select *  from orders o join users u on o.userId=u.userId join menus m on o.menusId=m.menusId where u.userName=#{0} and menusName=#{1}  order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userName",property="users",one=@One(select="com.cxx.mapper.BeforeMapper.selByUserName")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	
	
	
	List<Orders> selOrdersByMenusName(String userName,String menusName);
	/**
	 * 根据用户名及配送状态查找订单
	 * @param menusName
	 * @return join menus m on m.menusId=o.menusId
	 */
	@Select("select *  from orders o join users u on o.userId=u.userId where u.userName=#{0} and orderTimes like #{1}  order by orderTimes desc")
	@Results(value= {
			@Result(column="orderId",property="orderId"),
			@Result(column="orderMenusum",property="orderMenusum"),
			@Result(column="orderTimes",property="orderTimes"),
			@Result(column="orderDelivery",property="orderDelivery"),
			@Result(column="userName",property="users",one=@One(select="com.cxx.mapper.BeforeMapper.selByUserName")),
			@Result(column="menusId",property="menus",one=@One(select="com.cxx.mapper.MenusMapper.selById"))
	})
	List<Orders> selOrdersByDay(String userName,String day);
	
	
	
	
	/**
	 * 提交订单
	 * @return
	 */
	@Insert("insert into orders values(default,#{0},#{1},#{2},SYSDATE(),'0')")
	Integer insOrders(int userId,int menusId,String orderMenusum);
	
}

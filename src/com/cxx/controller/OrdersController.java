package com.cxx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cxx.pojo.Orders;
import com.cxx.service.OrdersService;
import com.cxx.util.Page;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.jndi.toolkit.ctx.StringHeadTail;

@Controller
public class OrdersController {
	@Resource
	private OrdersService ordersServiceImpl;
	
	/**
	 * 分页查询
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/selOrdersByPage")
	public String selOrdersByPage(Page page,Model model) {
		int curPage=page.getCurPage();
		int pageNumber=page.getPageNumber();
		System.out.println(curPage);
		if (curPage==0) {
			page.setCurPage(1);
		}
		System.out.println(curPage);
		if (pageNumber==0) {
			page.setPageNumber(3);
		}
		
		page.setPageStart((curPage-1)*pageNumber);
		page.setRows(ordersServiceImpl.selCount());
		page.setTotalPage((page.getRows()-1)/pageNumber+1);
		
		List<Orders> ordersByPageDatas = ordersServiceImpl.selOrdersByPage(page);
		page.setData(ordersByPageDatas);
		
		// 合计多少钱
		
		
		model.addAttribute("currentPage", page);
		return "admin/order.jsp";
	}
	
	@RequestMapping("/delOrder")
	public void delOrder(Orders orders,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		Integer delOrder=ordersServiceImpl.delOrder(orders.getOrderId());
		if (delOrder==1) {
			System.err.println("取消订单成功！");
			out.write("<script>alert('取消订单成功！');window.location.href='selOrdersByPage'</script>");
		}else {
			System.err.println("取消订单失败！");
			out.write("<script>alert('取消订单失败！');window.location.href='selOrdersByPage'</script>");
		}
	}
	@RequestMapping("/updOrder")
	public void updOrder(Orders orders,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int updOrder=ordersServiceImpl.updOrderDeli(orders.getOrderId());
		if (updOrder==1) {
			System.err.println("确认订单成功");
			out.write("<script>alert('确认订单成功！');window.location.href='selOrdersByPage'</script>");
		}else {
			System.err.println("确认订单失败");
			out.write("<script>alert('确认订单失败！');window.location.href='selOrdersByPage'</script>");
		}
	}
	
	@RequestMapping("/selDayByPage")
	public String selDaySale(Page page,Model model) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String orderTimes = sdf.format(date)+"%";
		System.out.println("orderTimes:"+orderTimes+"---------------------------------------");
		
		int curPage=page.getCurPage();
		int pageNumber=page.getPageNumber();
		if (curPage==0) {
			page.setCurPage(1);
		}
		if (pageNumber==0) {
			page.setPageNumber(3);
		}
		
		page.setPageStart((curPage-1)*pageNumber);
		page.setRows(ordersServiceImpl.selDayCount(orderTimes));
		page.setTotalPage((page.getRows()-1)/pageNumber+1);
		
		List<Orders> ordersByPageDatas = ordersServiceImpl.selOrdersByday(orderTimes, page.getPageStart(),pageNumber);
		page.setData(ordersByPageDatas);
		
		//
		System.err.println("rows:"+page.getRows());
		
		model.addAttribute("currentPage", page);
		return "admin/order_statistic.jsp";
	}
	
	@RequestMapping("/selOrderById")
	public String selOrderById(String type,int userId,Model model) throws IOException {
		System.err.println("type:"+type);
		System.err.println("userId:"+userId);
		
		if ("userId".equals(type)) {
			System.err.println("id");
			List<Orders> orders = ordersServiceImpl.selByUserId(userId);
			if (orders!=null&&orders.size()!=0) {
				model.addAttribute("orders", orders);
			}else {
				model.addAttribute("orders", "nothing");
			}
		}
		System.err.println("..............................");
		return "admin/order_search.jsp";
		
	}
	@RequestMapping("/selOrderByName")
	public String selOrderByName(String type,String menusName,Model model) throws IOException {
		System.err.println("type:"+type);
		System.err.println("menusName:"+menusName);		
		if ("menusName".equals(type)) {
			System.err.println("menusName");
			List<Orders> orders = ordersServiceImpl.selByMenusName(menusName);
			System.out.println(orders);
			if (orders!=null&&orders.size()!=0) {
				model.addAttribute("orders", orders);
			}else {
				model.addAttribute("orders", "nothing");
			}
		}
		System.err.println("..............................");
		return "admin/order_search.jsp";
	}
	@RequestMapping("/selOrderByTime")
	public String selOrderByTime(String type,String orderTimes,Model model) throws IOException {
		System.err.println("type:"+type);
		System.err.println("orderTimes:"+orderTimes);
		if ("orderTimes".equals(type)) {
			System.err.println("orderTimes");
			List<Orders> orders = ordersServiceImpl.selByOrderTimes(orderTimes+"%");
			System.out.println(orders);
			if (orders!=null&&orders.size()!=0) {
				model.addAttribute("orders", orders);
			}else {
				model.addAttribute("orders", "nothing");
			}
		}
		System.err.println("..............................");
		return "admin/order_search.jsp";
	}
	
}

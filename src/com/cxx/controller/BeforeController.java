package com.cxx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxx.pojo.Menus;
import com.cxx.pojo.Notice;
import com.cxx.pojo.Orders;
import com.cxx.pojo.Sales;
import com.cxx.pojo.ShoppingCar;
import com.cxx.pojo.Users;
import com.cxx.service.BeforeService;
import com.cxx.service.MenusService;
import com.cxx.service.NoticeService;
import com.cxx.service.OrdersService;
import com.cxx.service.UsersService;
import com.cxx.util.Page;

@Controller
public class BeforeController {
	@Resource
	private OrdersService ordersServiceImpl;
	@Resource
	private MenusService menusServiceImpl;
	@Resource
	private NoticeService noticeServiceImpl;
	@Resource
	private UsersService usersServiceImpl;
	@Resource
	private BeforeService beforeServiceImpl;

	/**
	 * 分页查询菜单 前三销售量 最新公告
	 * 
	 * @param page
	 * @param model
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/initByPage")
	public String selMenusByPage(Page page, Model model) {
		System.err.println("page:" + page);
		int curPage = page.getCurPage();
		// 每页数据量
		int pageNumber = page.getPageNumber();
		if (curPage == 0) {
			System.out.println("curpage==0");
			page.setCurPage(1);
		}
		if (pageNumber == 0) {
			page.setPageNumber(3);
		}
		// 数据库数
		page.setRows(menusServiceImpl.getMenusCount());
		// 总页数
		page.setTotalPage((page.getRows() - 1) / page.getPageNumber() + 1);
		// 开始
		page.setPageStart((curPage - 1) * pageNumber);
		;

		List<Menus> menus = menusServiceImpl.getmenuByPage(page);
		page.setData(menus);
		System.err.println("currentPage:" + page);

		model.addAttribute("currentPage", page);

		model.addAttribute("notices", beforeServiceImpl.selAllNotice());
		List<Sales> sales = beforeServiceImpl.selMaxSales();
		System.err.println(sales);
		model.addAttribute("sales", sales);

		return "before/index.jsp";
	}

	/**
	 * 显示所有公告
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getNotice")
	public String getNotice(Model model) {
		model.addAttribute("notices", beforeServiceImpl.selAllNotice());
		return "before/notice.jsp";
	}

	/**
	 * 显示某个公告详情
	 * 
	 * @param notice
	 * @param model
	 * @return
	 */
	@RequestMapping("/showNoticeByName")
	public String showNoticeByName(Notice notice, Model model) {
		System.err.println(notice);
		model.addAttribute("notice", beforeServiceImpl.selNoticeByName(notice.getNoticeName()));
		System.err.println(notice);
		return "before/notice.jsp";
	}

	/**
	 * 显示菜单详细信息
	 * 
	 * @param menus
	 * @param model
	 * @return
	 */
	@RequestMapping("/showMenu")
	public String showMenu(Menus menus, Model model) {
		model.addAttribute("menus", beforeServiceImpl.selByMenusName(menus.getMenusName()));
		System.err.println(menus);
		return "before/show.jsp";
	}

	/**
	 * 登录
	 * 
	 * @param cUsers
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/loginUser")
	public void loginUser(Users cUsers, HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.err.println(cUsers);

		if (request.getSession().getAttribute("user") != null) {
			System.out.println("您已经登陆了");
			out.write("<script>alert('您已经登陆了');window.location.href='before/login.jsp'</script>");
		}

		Users ifExitUser = beforeServiceImpl.selByUserName(cUsers.getUserName());
		if (ifExitUser != null) {
			// 存在该账户
			Users users = beforeServiceImpl.login(cUsers);
			if (users != null) {
				// 登陆成功
				System.out.println("登陆成功");
				request.getSession().setAttribute("user", users);
				out.write("<script>window.location.href='index.jsp'</script>");
			} else {
				// 密码不正确
				System.out.println("密码不正确");
				out.write("<script>alert('密码不正确');window.location.href='before/login.jsp'</script>");
			}
		} else {
			// 数据库不存在该账户
			System.out.println("数据库不存在该账户");
			out.write("<script>alert('数据库不存在该账户');window.location.href='before/login.jsp'</script>");
		}

	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logoutUser")
	public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
			if (session.getAttribute("orders") != null) {
				session.removeAttribute("orders");
			}
			if (session.getAttribute("cars") != null) {
				session.removeAttribute("cars");
			}
			out.write("<script>window.location.href='index.jsp'</script>");
		} else {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		}
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/regUser")
	public void regUser(Users user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (beforeServiceImpl.selByUserName(user.getUserName()) == null) {
			// 不存在该账户
			Integer regUser = beforeServiceImpl.insUser(user);
			if (regUser == 1) {
				// 注册成功
				System.out.println("注册成功");
				out.write("<script>alert('注册成功！到登录页面登录！');window.location.href='before/login.jsp'</script>");
			} else {
				// 注册失败
				System.out.println("注册失败");
				out.write("<script>alert('注册失败');window.location.href='before/login.jsp'</script>");
			}
		} else {
			// 数据库存在该账户
			System.out.println("数据库存在该账户");
			out.write("<script>alert('数据库已存在该账户');window.location.href='before/reg.jsp'</script>");
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updUser")
	public void updUser(Users user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		Integer updUser = beforeServiceImpl.UpdUser(user);
		if (updUser == 1) {
			// 注册成功
			System.out.println("更新成功");
			user = beforeServiceImpl.selByUserName(user.getUserName());
			request.getSession().setAttribute("user", user);
			out.write("<script>window.location.href='before/center.jsp'</script>");
		} else {
			// 注册失败
			System.out.println("更新失败");
			out.write("<script>alert('更新失败');window.location.href='before/center.jsp'</script>");
		}
	}

	/**
	 * 进入用户中心之前 判断用户是否登录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/prepareCenter")
	public void prepareCenter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			out.write("<script>window.location.href='before/center.jsp'</script>");
		}

	}

	/**
	 * 某个指定用户的所有订单
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/myOrder")
	public void myOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			List<Orders> orders = beforeServiceImpl.selOrdersByUserName(user.getUserName());
			System.out.println(orders);
			if (session.getAttribute("orders") != null) {
				session.removeAttribute("orders");
			}
			session.setAttribute("orders", orders);
			out.write("<script>window.location.href='before/order.jsp'</script>");
		}
	}

	/**
	 * 根据某个指定用户及状态查询用户订单
	 * 
	 * @param delivery
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/finishedOrder")
	public String finishedOrder(String delivery, HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return "before/login.jsp";
		} else {
			List<Orders> orders = beforeServiceImpl.selOrdersByDelivery(user.getUserName(), delivery);
			System.out.println(orders);
			if (session.getAttribute("orders") != null) {
				session.removeAttribute("orders");
			}
			model.addAttribute("orders", orders);
			return "before/order.jsp";
		}

	}

	/**
	 * 根据某个指定用户及菜单名查询用户订单
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/byMenusName")
	public String byMenusName(String menusName, HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return "before/login.jsp";
		} else {
			List<Orders> orders = beforeServiceImpl.selOrdersByMenusName(user.getUserName(), menusName);
			System.out.println(orders);
			if (session.getAttribute("orders") != null) {
				session.removeAttribute("orders");
			}
			model.addAttribute("orders", orders);
			return "before/order.jsp";
		}

	}

	/**
	 * 根据某个指定用户及菜单名查询用户订单
	 * 
	 * @param day
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/byDay")
	public String byDay(String orderTimes, HttpServletRequest request, Model model) throws IOException {
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return "before/login.jsp";
		} else {
			List<Orders> orders = beforeServiceImpl.selOrdersByDay(user.getUserName(), orderTimes + "%");
			System.out.println(orders);
			if (session.getAttribute("orders") != null) {
				session.removeAttribute("orders");
			}
			model.addAttribute("orders", orders);
			return "before/order.jsp";
		}

	}

	/**
	 * 加入餐车
	 * 
	 * @param menusName
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/shoppingCar")
	public void shoppingCar(String menusName, float menusPricel, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(menusName);
		System.out.println(menusPricel);
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			// 已经登录
			List<ShoppingCar> cars = (List<ShoppingCar>) session.getAttribute("cars");
			ShoppingCar car = new ShoppingCar();
			if (cars != null && cars.size() != 0) {
				// 餐车内容不为空
				boolean ifSamename = true;
				for (ShoppingCar shoppingCar : cars) {
					// 餐车存在该菜品
					if (menusName.equals(shoppingCar.getName())) {
						System.out.println("餐车已经存在该菜品" + ifSamename);
						int count = shoppingCar.getCount() + 1;
						shoppingCar.setCount(count);
						shoppingCar.setSum(menusPricel * count);
						ifSamename = false;
						break;
					}

				}
				// test

				if (ifSamename) {
					System.out.println("餐车不存在该菜品" + ifSamename);
					car.setName(menusName);
					car.setPrice(menusPricel);
					car.setSum(menusPricel * car.getCount());
					cars.add(car);
				}
			} else {
				// 餐车为空
				cars = new ArrayList<>();
				car.setName(menusName);
				car.setPrice(menusPricel);
				car.setSum(car.getPrice() * car.getCount());
				System.out.println("新建餐车:" + car);
				cars.add(car);
			}
			// test
			session.setAttribute("cars", cars);

			// test
			System.out.println("餐车长度：" + cars.size());
			System.out.println("餐车内容");
			for (ShoppingCar shoppingCar : cars) {
				System.out.println(shoppingCar);
			}

			out.write("<script>window.location.href='index.jsp'</script>");
		}
	}

	/**
	 * 查看我的餐车
	 * 
	 * @param menusName
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/myCar")
	public void myCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			// 已经登录
			List<ShoppingCar> cars = (List<ShoppingCar>) session.getAttribute("cars");
			if (cars != null && cars.size() != 0) {
				// 存在餐车
				out.write("<script>window.location.href='before/shoppingcar.jsp'</script>");
			} else {
				// 没有餐车
				System.out.println("您餐车为空，请加入菜品");
				out.write("<script>alert('您餐车为空，请加入菜品');window.location.href='index.jsp'</script>");
			}

		}
	}

	/**
	 * 删除我的餐车菜品
	 * 
	 * @param menusName
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/delCar")
	public void delCar(String menusName, String type, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(menusName);
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			// 已经登录
			List<ShoppingCar> cars = (List<ShoppingCar>) session.getAttribute("cars");
			if (cars != null && cars.size() != 0) {
				// 餐车内容不为空
				for (ShoppingCar shoppingCar : cars) {
					// 餐车存在该菜品
					if (menusName.equals(shoppingCar.getName())) {
						cars.remove(shoppingCar);
						break;
					}

				}
				session.setAttribute("cars", cars);

				// test
				System.out.println("餐车长度：" + cars.size());
				System.out.println("餐车内容");
				for (ShoppingCar shoppingCar : cars) {
					System.out.println(shoppingCar);
				}
				if ("index".equals(type)) {
					out.write("<script>window.location.href='index.jsp'</script>");
				} else {
					out.write("<script>window.location.href='before/shoppingcar.jsp'</script>");
				}

			}

		}
	}

	/**
	 * 清空所有餐车内容
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/delAllCar")
	public void delAllCar(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			// 已经登录
			if (request.getSession().getAttribute("cars") != null) {
				request.getSession().removeAttribute("cars");
			}
			if ("index".equals(type)) {
				out.write("<script>window.location.href='index.jsp'</script>");
			} else {
				out.write("<script>window.location.href='before/shoppingcar.jsp'</script>");
			}
		}
	}

	@RequestMapping("/insCars")
	public void insCars(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			System.out.println("您还没登录，请登录");
			out.write("<script>alert('您还没登录，请登录');window.location.href='before/login.jsp'</script>");
		} else {
			// 已经登录
			@SuppressWarnings("unchecked")
			List<ShoppingCar> cars =  (List<ShoppingCar>) request.getSession().getAttribute("cars");
			if ( cars == null || cars.size()==0) {
				// 餐车为空
				if ("index".equals(type)) {
					out.write("<script>alert('您的餐车为空');window.location.href='index.jsp'</script>");
				} else {
					out.write("<script>alert('您的餐车为空');window.location.href='before/shoppingcar.jsp'</script>");
				}
			}
			// 餐车不为空
			int insOrders=0;
			Menus menus =null;
			for (ShoppingCar car : cars) {
				menus = beforeServiceImpl.selByMenusName(car.getName());
				// 插入
				insOrders =insOrders+ beforeServiceImpl.insOrders(user.getUserId(), menus.getMenusId(), car.getCount()+"");
			}
			
			if (insOrders == cars.size()) {
				// 提交成功
				cars.clear();
				if ("index".equals(type)) {
					out.write("<script>alert('提交成功');window.location.href='index.jsp'</script>");
				} else {
					out.write("<script>alert('提交成功');window.location.href='before/shoppingcar.jsp'</script>");
				}
			}else {
				// 提交失败
				if ("index".equals(type)) {
					out.write("<script>alert('提交失败');window.location.href='index.jsp'</script>");
				} else {
					out.write("<script>alert('提交失败');window.location.href='before/shoppingcar.jsp'</script>");
				}
			}
			
		}
	}
}

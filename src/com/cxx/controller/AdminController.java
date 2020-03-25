package com.cxx.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxx.pojo.Admin;
import com.cxx.service.AdminService;

@Controller
public class AdminController {
	@Resource
	private AdminService adminServiceImpl;
	
	/**
	 * 管理员登陆
	 * @param admin
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/adminLogin")
	public void adminLogin(Admin admin,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 该账户是否存在
		Admin admin1 = adminServiceImpl.selByAdminName(admin.getAdminName());
		System.out.println("admin:"+admin1);
		if (admin1 != null) {
			// 如果这个账户已经注册
			Admin admin2=adminServiceImpl.login(admin);
			
			if (admin2 != null) {
				// 而且账号和密码都正确
				session.setAttribute("admin", admin2);
				out.write("<script>window.location.href='admin/main.jsp'</script>");
			}else {
				// 账号存在，但是密码不正确
				out.write("<script>alert('密码错误！');window.location.href='admin/index.jsp'</script>");
			}
			}else {
				// 如果说这个账户还没有注册
				out.write("<script>alert('这个账户不存在！请注册');window.location.href='admin/index.jsp'</script>");
		}
	}
	
	@RequestMapping("/adminLogout")
	public void logout(Admin admin,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		Admin admin2 = (Admin) session.getAttribute("admin");
		if (admin2 != null) {
			session.removeAttribute("admin");
		}
		response.getWriter().write("<script>window.location.href='admin/index.jsp'</script>");
	}
	
	@RequestMapping("/adminUpdate")
	public void adminUpdate(Admin newAdmin,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Admin oldAdmin = (Admin) session.getAttribute("admin");
		if ((oldAdmin.getAdminName()).equals(newAdmin.getAdminName())&&(oldAdmin.getAdminPwd()).equals(newAdmin.getAdminPwd())) {
			// 如果没有修改名字也没有修改密码
			out.write("<script>alert('您没有修改个人信息');window.location.href='admin/main.jsp'</script>");
		}else {
			// 如果名字修改了
			Admin sameName = adminServiceImpl.selSameName(newAdmin);
			if (sameName!= null) {
				// 如果新的账户其他用户已经使用
				out.write("<script>alert('该名字已经存在');window.location.href='admin/main.jsp'</script>");
			}else {
				// 名字在数据库里面没有重复
				int update = adminServiceImpl.updateAdmin(newAdmin);
				if (update!=-1) {
					// 修改信息成功
					System.out.println("更新成功");
					Admin admin = adminServiceImpl.selByAdminName(newAdmin.getAdminName());
					session.setAttribute("admin", admin);
					out.write("<script>window.location.href='admin/main.jsp'</script>");
				}else {
					// 修改信息失败
					System.out.println("更新失败!");
					out.write("<script>alert('修改信息失败!');window.location.href='admin/main.jsp'</script>");
				}
			}
		}
	}
	
}

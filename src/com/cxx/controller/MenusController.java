package com.cxx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxx.pojo.Menus;
import com.cxx.pojo.Types;
import com.cxx.service.MenusService;
import com.cxx.service.TypesService;
import com.cxx.util.Page;

@Controller
public class MenusController {
	@Resource
	private MenusService menusServiceimpl;
	@Resource
	private TypesService typesServiceimpl;
	
	@RequestMapping("/addMenus")
	public void addMenus(Menus curmenus,HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		System.err.println("从jsp页面传过来的参数："+curmenus);
		
		Menus sqlMenus=menusServiceimpl.selByName(curmenus.getMenusName());
		System.err.println("数据库是否存在该菜品:"+sqlMenus);
		
		if (sqlMenus == null) {
			// 数据库没有存在该菜品
			curmenus.setMenusImgpath("img/"+curmenus.getMenusImgpath());
			Integer addMenus = menusServiceimpl.insMenus(curmenus);
			System.err.println(addMenus);
			if (addMenus == 1) {
				// 增加菜品成功
				System.err.println("添加菜品成功！");
				out.write("<script>alert('添加菜品成功！');window.location.href='selMenusByPage'</script>");
			}else {
				// 添加菜品失败！
				System.out.println("添加菜品失败！");
				out.write("<script>alert('添加菜品失败！');window.location.href='admin/menus_add.jsp'</script>");
			}
		}else {
			//数据库已经存在该菜品
			out.write("<script>alert('数据库已经存在该菜品！');window.location.href='admin/menus_add.jsp'</script>");
		}
		
	}

	@RequestMapping("/selMenusByPage")
	public String selMenusByPage(Page page,Model model,HttpServletResponse res) throws IOException {
		System.err.println("page:"+page);
		int curPage=page.getCurPage();
		// 每页数据量
		int pageNumber=page.getPageNumber();
		if (curPage==0) {
			System.out.println("curpage==0");
			page.setCurPage(1);
		}
		if (pageNumber==0) {
			page.setPageNumber(3);
		}
		// 数据库数
		page.setRows(menusServiceimpl.getMenusCount());
		// 总页数
		page.setTotalPage((page.getRows()-1)/page.getPageNumber()+1);
		// 开始
		page.setPageStart((curPage-1)*pageNumber);;
		
		List<Menus> menus = menusServiceimpl.getmenuByPage(page);
		page.setData(menus);
		System.err.println("currentPage:"+page);
		model.addAttribute("currentPage", page);
		
		return "admin/menus.jsp";
	}
	
	@RequestMapping("/delMenus")
	public void delMenus(Menus menus,HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=utf-8");
		Integer delMenus=menusServiceimpl.delMenus(menus);
		
		System.err.println("delMenus:"+delMenus);
		if (delMenus==1) {
			// 删除成功
			System.out.println("删除菜品成功");
			res.getWriter().write("<script>alert('删除成功');window.location.href='selMenusByPage';</script>");
		}else {
			res.getWriter().write("<script>alert('删除失败');window.location.href='selMenusByPage';</script>");
		}
	}
	
	@RequestMapping("/beforeUpdMenus")
	public String  beforeUpdMenus(Menus curMenus,Model model) {
		Menus menus = menusServiceimpl.selByName(curMenus.getMenusName());
		List<Types> types = typesServiceimpl.selAllTypes();
		model.addAttribute("types", types);
		model.addAttribute("menus", menus);
		return "admin/menus_update.jsp";
	}
	
	@RequestMapping("/updMenus")
	public void updMenus(Menus curMenus,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		System.err.println("updMenus");
		System.err.println("curMenus:"+curMenus);
		Menus sameNameMenus = menusServiceimpl.selSameName(curMenus);
		System.err.println("sameNameMenus:"+sameNameMenus);
		if (sameNameMenus!=null) {
			// 存在同名情况
			response.getWriter().write("<script>window.location.href='selMenusByPage';alert('数据库已存在该菜品名！');</script>");
		}else {
			// 更新菜品信息
			Integer updMenus=menusServiceimpl.updMenus(curMenus);
			if (updMenus==1) {
				response.getWriter().write("<script>window.location.href='selMenusByPage';alert('数据库更新成功！');</script>");
			}else {
				response.getWriter().write("<script>window.location.href='selMenusByPage';alert('数据库更新失败！');</script>");
			}
		}
	}
	
	
	
	
	
}

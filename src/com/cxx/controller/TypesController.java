package com.cxx.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxx.pojo.Menus;
import com.cxx.pojo.Types;
import com.cxx.service.TypesService;
import com.cxx.util.Page;

import aj.org.objectweb.asm.Type;

@Controller
public class TypesController {
	@Resource
	private TypesService typesServiceimpl;
	
	
	
	@RequestMapping("/selTypesByPage")
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
		page.setRows(typesServiceimpl.getTypesCount());
		// 总页数
		page.setTotalPage((page.getRows()-1)/page.getPageNumber()+1);
		// 开始
		page.setPageStart((curPage-1)*pageNumber);;
		
		List<Types> types = typesServiceimpl.getTypesByPage(page);
		page.setData(types);
		System.err.println("currentPage:"+page);
		model.addAttribute("currentPage", page);
		
		return "admin/type.jsp";
	}
	
	@RequestMapping("/addTypes")
	public void addTypes(Types curTypes,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		System.err.println("curTypes:"+curTypes);
		Types types=typesServiceimpl.selByName(curTypes.getTypeName());
		if (types==null) {
			//数据库没有这个菜品名
			// 添加
			Integer addType=typesServiceimpl.addType(curTypes);
			if (addType==1) {
				System.out.println("添加成功！");
				response.getWriter().write("<script>window.location.href='selTypesByPage';alert('添加成功！');</script>");
			}else {
				response.getWriter().write("<script>window.location.href='selTypesByPage';alert('添加失败！');</script>");
			}
		}else {
			//已经存在这个菜品类别
			response.getWriter().write("<script>window.location.href='admin/type_add.jsp';alert('已经存在这个菜品类别');</script>");
		}
	}
	
	@RequestMapping("/beforeUpd")
	public String beforeUpd(Types curTypes,Model model) {
		Types types = typesServiceimpl.selByName(curTypes.getTypeName());
		model.addAttribute("types", types);
		return "admin/type_update.jsp";
	}
	
	
	@RequestMapping("/updTypes")
	public void updTypes(Types curTypes,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		Types sameNameTypes=typesServiceimpl.selSameName(curTypes);
		if (sameNameTypes==null) {
			//数据库没有这个菜品名
			// 添加
			Integer updType=typesServiceimpl.updType(curTypes);
			if (updType==1) {
				System.out.println("更新成功！");
				response.getWriter().write("<script>window.location.href='selTypesByPage';alert('更新成功！');</script>");
			}else {
				System.out.println("更新失败！");
				response.getWriter().write("<script>window.location.href='selTypesByPage';alert('更新失败！');</script>");
			}
		}else {
			//已经存在这个菜品类别
			System.out.println("已经存在这个菜品类别！");
			response.getWriter().write("<script>window.location.href='selTypesByPage';alert('已经存在这个菜品类别');</script>");
		}
	}
	@RequestMapping("/delTypes")
	public void delTypes(Types curTypes,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		Integer delTypes=typesServiceimpl.delType(curTypes);
		if (delTypes==1) {
			System.out.println("删除成功！");
			response.getWriter().write("<script>window.location.href='selTypesByPage';alert('删除成功');</script>");
		}else {
			System.out.println("删除失败！");
			response.getWriter().write("<script>window.location.href='selTypesByPage';alert('删除失败');</script>");
		}
		
	}
}

package com.cxx.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxx.pojo.Notice;
import com.cxx.service.NoticeService;
import com.cxx.util.Page;

@Controller
public class NoticeController {
	@Resource
	private NoticeService noticeServiceImpl;
	
	@RequestMapping("/selNoticeByPage")
	public String selNoticeByPage(Page page,Model model) {
		int curPage=page.getCurPage();
		if (curPage==0) {
			page.setCurPage(1);
		}
		int pageNumber=page.getPageNumber();
		if (pageNumber==0) {
			page.setPageNumber(3);
		}
		page.setPageStart((curPage-1)*pageNumber);
		page.setRows(noticeServiceImpl.selNoticeCount());
		page.setTotalPage((page.getRows()-1)/pageNumber+1);
		page.setData(noticeServiceImpl.selNoticeByPage(page));
		model.addAttribute("currentPage", page);
		return "admin/notice.jsp";
	}
	
	@RequestMapping("/addNotice")
	public void addNotice(Notice curNotice,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Notice sameName=noticeServiceImpl.selByName(curNotice.getNoticeName());
		if (sameName==null) {
			// 不存在同名情况
			// 添加
			Integer addNotice=noticeServiceImpl.addNotice(curNotice);
			if (addNotice==1) {
				//添加成功
				System.err.println("添加成功");
				out.write("<script>alert('添加成功！');window.location.href='selNoticeByPage';</script>");
			}else {
				//添加失败
				System.err.println("添加失败");
				out.write("<script>alert('添加失败！');window.location.href='admin/notice_add.jsp';</script>");
			}
		}else {
			out.write("<script>alert('数据库已经存在该公告！');window.location.href='admin/notice_add.jsp';</script>");
		}
		
	}
	
	@RequestMapping("/delNotice")
	public void delNotice(Notice notice,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
			// 删除
		Integer delNotice=noticeServiceImpl.delNotice(notice);
		if (delNotice==1) {
			//添加成功
			System.err.println("删除成功");
			out.write("<script>alert('删除成功！');window.location.href='selNoticeByPage';</script>");
		}else {
			//添加失败
			System.err.println("删除失败");
			out.write("<script>alert('删除失败！');window.location.href='selNoticeByPage';</script>");
		}			
			
	}
	
}

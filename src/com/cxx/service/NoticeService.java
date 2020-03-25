package com.cxx.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Notice;
import com.cxx.util.Page;

public interface NoticeService {
	/**
	 * 根据通告名字查询数据
	 * @param noticeName
	 * @return
	 */
	Notice selByName(String noticeName);
	
	/**
	 * 添加通告
	 * @param notice
	 * @return
	 */
	Integer addNotice(Notice notice);
	
	/**
	 * 删除
	 * @param notice
	 * @return
	 */
	Integer delNotice(Notice notice);
	
	/**
	 * 分页查询通告信息
	 * @param page
	 * @return
	 */
	List<Notice> selNoticeByPage(Page page);
	
	/**
	 * 查询数据库条数
	 * @return
	 */
	int selNoticeCount();
}

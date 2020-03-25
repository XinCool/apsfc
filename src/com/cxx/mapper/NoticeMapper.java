package com.cxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Notice;
import com.cxx.util.Page;

public interface NoticeMapper {
	/**
	 * 根据通告名字查询数据
	 * @param noticeName
	 * @return
	 */
	@Select("select * from notice where noticeName=#{noticeName}")
	Notice selByName(String noticeName);
	
	/**
	 * 添加通告
	 * @param notice
	 * @return
	 */
	@Insert("insert into notice values(default,#{noticeName},#{noticeContent},SYSDATE())")
	Integer addNotice(Notice notice);
	
	/**
	 * 删除
	 * @param notice
	 * @return
	 */
	@Delete("delete from notice where noticeName=#{noticeName}")
	Integer delNotice(Notice notice);
	
	/**
	 * 分页查询通告信息
	 * @param page
	 * @return
	 */
	@Select("select * from notice order by noticeTimes desc limit #{pageStart},#{pageNumber}")
	List<Notice> selNoticeByPage(Page page);
	
	/**
	 * 查询数据库条数
	 * @return
	 */
	@Select("select count(*) from notice")
	int selNoticeCount();
}

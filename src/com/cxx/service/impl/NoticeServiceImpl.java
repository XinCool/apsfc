package com.cxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cxx.mapper.NoticeMapper;
import com.cxx.pojo.Notice;
import com.cxx.service.NoticeService;
import com.cxx.util.Page;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Resource
	private NoticeMapper noticeMapper;

	@Override
	public Notice selByName(String noticeName) {
		return noticeMapper.selByName(noticeName);
	}

	@Override
	public Integer addNotice(Notice notice) {
		return noticeMapper.addNotice(notice);
	}

	@Override
	public List<Notice> selNoticeByPage(Page page) {
		return noticeMapper.selNoticeByPage(page);
	}

	@Override
	public int selNoticeCount() {
		return noticeMapper.selNoticeCount();
	}

	@Override
	public Integer delNotice(Notice notice) {
		return noticeMapper.delNotice(notice);
	}
	
	
}

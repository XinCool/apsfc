package com.cxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cxx.mapper.MenusMapper;
import com.cxx.pojo.Menus;
import com.cxx.service.MenusService;
import com.cxx.util.Page;

@Service
public class MenusServiceImpl implements MenusService{
	@Resource
	private MenusMapper menusMapper;

	@Override
	public Menus selByName(String menusName) {
		return menusMapper.selByName(menusName);
	}

	@Override
	public Integer insMenus(Menus menus) {
		Integer insMenus = menusMapper.insMenus(menus);
		System.err.println(insMenus);
		return insMenus;
	}

	

	@Override
	public List<Menus> getmenuByPage(Page page) {
		return menusMapper.getmenuByPage(page);
	}

	@Override
	public int getMenusCount() {
		return menusMapper.getMenusCount();
	}

	@Override
	public Integer delMenus(Menus menus) {
		return menusMapper.delMenus(menus);
	}

	@Override
	public Integer updMenus(Menus menus) {
		return menusMapper.updMenus(menus);
	}

	@Override
	public Menus selSameName(Menus menus) {
		return menusMapper.selSameName(menus);
	}
	
	
	
}

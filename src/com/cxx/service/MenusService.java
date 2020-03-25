package com.cxx.service;

import java.util.List;

import com.cxx.pojo.Menus;
import com.cxx.util.Page;

public interface MenusService {
	
	/**
	 * 根据菜品名字查询信息
	 * @param menusName
	 * @return
	 */
	Menus selByName(String menusName);
	
	/**
	 * 添加菜品信息
	 * @param menus
	 * @return
	 */
	Integer insMenus(Menus menus);
	
	/**
	 * 分页查询菜品信息
	 * @param page
	 * @return
	 */
	List<Menus> getmenuByPage(Page page);
	
	/**
	 * 查询数据库菜品数据条数
	 * @return
	 */
	int getMenusCount();
	
	/**
	 * 删除菜品
	 * @param menus
	 * @return
	 */
	Integer delMenus(Menus menus);
	
	/**
	 * 更新菜品信息
	 * @param menus
	 * @return
	 */
	Integer updMenus(Menus menus);
	
	/**
	 * 查询数据库是否存在除本身以外同名的菜品
	 * @param menus
	 * @return
	 */
	Menus selSameName(Menus menus);
}

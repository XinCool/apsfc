package com.cxx.service;

import org.springframework.stereotype.Service;

import com.cxx.pojo.Admin;

public interface AdminService {
	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);
	
	/**
	 * 根据名字查询某一个账户是否存在
	 * @param adminName
	 * @return
	 */
	Admin selByAdminName(String adminName);
	
	/**
	 * 根据名字学号查询，数据库除本身外是否还有其他账户名字一样的
	 * @param admin
	 * @return
	 */
	Admin selSameName(Admin admin);
	
	/**
	 * 更新数据库信息
	 * @param admin
	 * @return
	 */
	int updateAdmin(Admin admin);
	
	
}

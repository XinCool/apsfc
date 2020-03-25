package com.cxx.mapper;


import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Admin;

public interface AdminMapper {
	
	/**
	 * 根据Admin查询Admin信息
	 * @param admin
	 * @return
	 */
	@Select("select * from admin where adminName=#{adminName} and adminPwd=#{adminPwd}")
	Admin selByAdmin(Admin admin);
	
	/**
	 * 通过admin的账户名称来查询该账户是否存在
	 * @param adminName
	 * @return
	 */
	@Select("select * from admin where adminName=#{adminName}")
	Admin selByAdminName(String adminName);
	
	/**
	 * 检验增加操作时，改名字时，判断数据库里面新名字除了本身是否还存在其他的名字一样的
	 * @param admin
	 * @return
	 */
	@Select("select * from admin where adminName=#{adminName} and adminId!=#{adminId}")
	Admin selSameName(Admin admin);
	
	/**
	 * 更新个人信息
	 * @param admin
	 * @return
	 */
	@Select("update from admin set adminName=#{adminName},adminPwd=#{adminPwd}")
	int updateAdmin(Admin admin);
}

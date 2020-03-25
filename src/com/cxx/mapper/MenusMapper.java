package com.cxx.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxx.pojo.Menus;
import com.cxx.util.Page;

public interface MenusMapper {
	
	/**
	 * 根据菜单号查询菜单信息
	 * @param menusName
	 * @return
	 */
	@Select("select * from menus where menusId=#{menusId}")
	Menus selById(int menusId);
	
	/**
	 * 根据菜单名查询菜单信息
	 * @param menusName
	 * @return
	 */
	@Select("select * from menus where menusName=#{menusName}")
	Menus selByName(String menusName);
	
	/**
	 * 添加菜单信息
	 * @param menus
	 * @return
	 */
	@Insert("insert into menus(menusName,typeId,menusBurden,menusBrief,menusPrice,menusPricel,menusImgpath) values(#{menusName},#{typeId},#{menusBurden},#{menusBrief},#{menusPrice},#{menusPricel},#{menusImgpath})")
	Integer insMenus(Menus menus);
	
	/**
	 * 查询所有菜品信息
	 * @return
	 */
	@Select("select menusId,menusName,menus.typeId typeId,menusBurden,menusBrief,menusPrice,menusPricel,menusSums,menusSumsl,types.typeName typeName,menusImgpath "
			+ " from menus join types on menus.typeId=types.typeId")
	List<Menus> selAllMenus();
	
	/**
	 * 分页查询菜单信息
	 * @param page
	 * @return
	 */
	/*@Select("select * from menus limit #{pageStart},#{pageNumber}")*/
	@Select("select menusId,menusName,menus.typeId typeId,menusBurden,menusBrief,menusPrice,menusPricel,menusSums,menusSumsl,types.typeName typeName,menusImgpath "
			+ " from menus join types on menus.typeId=types.typeId limit #{pageStart},#{pageNumber}")
	List<Menus> getmenuByPage(Page page);
	
	/**
	 * 查询数据库有几条数据
	 * @return
	 */
	@Select("select count(*) from menus")
	int getMenusCount();
	
	/**
	 * 删除菜品信息
	 * @param menus
	 * @return
	 */
	@Delete("delete from menus where menusName=#{menusName}")
	Integer delMenus(Menus menus);
	
	/**
	 * 更新菜品信息
	 * @param menus
	 * @return
	 */
	@Update("update menus set menusName=#{menusName},typeId=#{typeId},menusBurden=#{menusBurden},menusBrief=#{menusBrief},menusPrice=#{menusPrice},menusPricel=#{menusPricel} where menusId=#{menusId}")
	Integer updMenus(Menus menus);
	
	
	/**
	 * 查询数据库里面有没有存在与当前名称一样的数据，本身除外q
	 * @param menus
	 * @return
	 */
	@Select("select * from menus where menusName=#{menusName} and menusId<>#{menusId}")
	Menus selSameName(Menus menus);
	
}

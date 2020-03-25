package com.cxx.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Types;
import com.cxx.util.Page;

public interface TypesService {
	
	/**
	 * 查询所有菜品类别（更新菜品信息的时候用到）
	 * @return
	 */
	List<Types> selAllTypes();
	

	/**
	 * 分页查询类别信息
	 * @param page
	 * @return
	 */
	List<Types> getTypesByPage(Page page);
	
	
	/**
	 * 查询数据库菜品类别条数
	 * @return
	 */
	int getTypesCount();
	
	/**
	 * 更新菜品类别需要用到
	 * @param typesName
	 * @return
	 */
	Types selByName(String typesName);
	
	/**
	 * 添加菜品类别
	 * @param types
	 * @return
	 */
	Integer addType(Types types);
	
	/**
	 * 删除菜品类别
	 * @param types
	 * @return
	 */
	Integer delType(Types types);
	
	/**
	 * 根据菜品类别名称查询数据库中是否存在 与这个名称一样的菜品类别
	 * @param types
	 * @return
	 */
	Types selSameName(Types types);

	/**
	 * 更新菜品类别
	 * @param types
	 * @return
	 */
	@Delete("delete from types where typeName=#{typeName}")
	Integer updType(Types types);
}

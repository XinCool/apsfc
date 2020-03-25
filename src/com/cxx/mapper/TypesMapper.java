package com.cxx.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Menus;
import com.cxx.pojo.Types;
import com.cxx.util.Page;

import aj.org.objectweb.asm.Type;

public interface TypesMapper {
	/*@Results(value={ @Result(id=true,property="typeId",column="typeId"),
			@Result(property="typeName",column="typeName"), 
			@Result(property="list",column="typeId",many=@Many(select="com.cxx.mapper.TypesMapper.selById")) 
	}) */
	/**
	 * 查询所有菜品类别（更新菜品信息的时候用到）
	 * @return
	 */
	@Select("select * from types")
	List<Types> selAll();
	
	/**
	 * 分页查询类别信息
	 * @param page
	 * @return
	 */
	@Select("select * from types limit #{pageStart},#{pageNumber}")
	List<Types> getTypesByPage(Page page);
	
	/**
	 * 查询数据库菜品类别条数
	 * @return
	 */
	@Select("select count(*) from types")
	int getTypesCount();
	
	/**
	 * 更新菜品类别需要用到
	 * @param typesName
	 * @return
	 */
	@Select("select * from types where typeName=#{typeName}")
	Types selByName(String typesName);
	
	/**
	 * 添加菜品类别
	 * @param types
	 * @return
	 */
	@Insert("insert into types values(default,#{typeName})")
	Integer addType(Types types);
	
	/**
	 * 删除菜品类别
	 * @param types
	 * @return
	 */
	@Delete("delete from types where typeName=#{typeName}")
	Integer delType(Types types);
	
	/**
	 * 更新菜品类别
	 * @param types
	 * @return
	 */
	@Delete("update types set typeName=#{typeName} where typeId=#{typeId}")
	Integer updType(Types types);
	
	/**
	 * 根据菜品类别名称查询数据库中是否存在 与这个名称一样的菜品类别
	 * @param types
	 * @return
	 */
	@Select("select * from types where typeName=#{typeName} and typeId<>#{typeId}")
	Types selSameName(Types types);
}

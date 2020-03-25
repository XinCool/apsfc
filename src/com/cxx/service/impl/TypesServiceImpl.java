package com.cxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cxx.mapper.TypesMapper;
import com.cxx.pojo.Types;
import com.cxx.service.TypesService;
import com.cxx.util.Page;
@Service
public class TypesServiceImpl implements TypesService{
	@Resource
	private TypesMapper typesMapper;
	
	@Override
	public List<Types> selAllTypes() {
		return typesMapper.selAll();
	}

	@Override
	public List<Types> getTypesByPage(Page page) {
		return typesMapper.getTypesByPage(page);
	}

	@Override
	public int getTypesCount() {
		return typesMapper.getTypesCount();
	}

	@Override
	public Types selByName(String typesName) {
		return typesMapper.selByName(typesName);
	}

	@Override
	public Integer addType(Types types) {
		return typesMapper.addType(types);
	}

	@Override
	public Integer delType(Types types) {
		return typesMapper.delType(types);
	}

	@Override
	public Types selSameName(Types types) {
		return typesMapper.selSameName(types);
	}

	@Override
	public Integer updType(Types types) {
		return typesMapper.updType(types);
	}

}

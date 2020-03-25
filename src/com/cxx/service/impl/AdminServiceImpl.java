package com.cxx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cxx.mapper.AdminMapper;
import com.cxx.pojo.Admin;
import com.cxx.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminMapper adminMapper;
	
	@Override
	public Admin login(Admin admin) {
		return adminMapper.selByAdmin(admin);
	}

	@Override
	public Admin selByAdminName(String adminName) {
		return adminMapper.selByAdminName(adminName);
	}

	@Override
	public Admin selSameName(Admin admin) {
		return adminMapper.selSameName(admin);
	}

	@Override
	public int updateAdmin(Admin admin) {
		return adminMapper.updateAdmin(admin);
	}

	

	
	
}

package com.cxx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cxx.mapper.UsersMapper;
import com.cxx.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService{
	@Resource
	private UsersMapper usersMapper;
	
}

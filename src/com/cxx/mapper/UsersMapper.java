package com.cxx.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.cxx.pojo.Users;

public interface UsersMapper {
	@Select("select * from users where userId=#{usersId}")
	Users selById(int userId);
}

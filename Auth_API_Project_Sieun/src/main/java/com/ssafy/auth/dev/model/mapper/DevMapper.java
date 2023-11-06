package com.ssafy.auth.dev.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.auth.dev.model.DevDto;

@Mapper
public interface DevMapper {

	
	String readKey(DevDto dev) throws SQLException;

	void createDev(DevDto dev) throws SQLException;

	void createKey(DevDto dev) throws SQLException;

	String readPw(DevDto dev) throws SQLException;

	 int readDevId(String key) throws SQLException;
	
}

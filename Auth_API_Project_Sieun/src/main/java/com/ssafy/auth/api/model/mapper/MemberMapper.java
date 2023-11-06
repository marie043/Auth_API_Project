package com.ssafy.auth.api.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.auth.api.model.MemberDto;

@Mapper
public interface MemberMapper {

	void createKey(MemberDto member) throws SQLException;

	void createMember(MemberDto member) throws SQLException;

	String readKey(String id) throws SQLException;

	String readSalt(String id) throws SQLException;

	String readPw(String id) throws SQLException;

	MemberDto readMember(String id) throws SQLException;
	
	Integer checkId(String id) throws SQLException;

	void deleteMember(String id) throws SQLException;

	void deleteKey(String idCrypt) throws SQLException;

}

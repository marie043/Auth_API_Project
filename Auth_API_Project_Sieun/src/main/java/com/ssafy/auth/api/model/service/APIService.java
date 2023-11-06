package com.ssafy.auth.api.model.service;

import com.ssafy.auth.api.model.MemberDto;

public interface APIService {

	void signUpMember(MemberDto member) throws Exception;

	MemberDto loginMember(MemberDto member) throws Exception;

	void deleteMember(MemberDto member) throws Exception;

}

package com.ssafy.auth.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.auth.api.model.MemberDto;
import com.ssafy.auth.api.model.service.APIService;

@RestController
@RequestMapping("/api")
public class APICotroller {
	@Autowired
	APIService apiService;

	@PostMapping("/sign")
	public Map<String, String> sign(@RequestBody MemberDto member) {
		Map<String, String> result = new HashMap<String, String>();
		System.out.println(member);
		if(member.getDev_key()==null) {
			result.put("msg", "NO");
			result.put("detail", "개발자 키가 없습니다.");
		}else if (member.getId() == null || member.getPw() == null || member.getEmail() == null || member.getName() == null
				|| member.getBirthday() == null) {
			result.put("msg", "NO");
			result.put("detail", "회원가입 정보를 모두 입력해 주세요");

		} else {
			try {
				System.out.println("회원가입 요청 : " + member.toString());
				apiService.signUpMember(member);
				result.put("msg", "OK");
			} catch (Exception e) {
				result.put("msg", "NO");
				result.put("detail", e.getMessage());
			}
		}
		return result;
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody MemberDto member) {
		Map<String, String> result = new HashMap<String, String>();
		if (member.getId() == null || member.getPw() == null) {
			result.put("msg", "NO");
			result.put("detail", "로그인 정보를 모두 입력해 주세요");
		} else {
			try {
				member = apiService.loginMember(member);
				result.put("id", member.getId());
				result.put("msg", "OK");
				result.put("email", member.getEmail());
				result.put("name", member.getName());
				result.put("birthday", member.getBirthday());
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", e.getMessage());
			}
		}
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, String> delete(@RequestBody MemberDto member){
		Map<String, String> result = new HashMap<String, String>();
		if (member.getId() == null || member.getPw() == null) {
			result.put("msg", "NO");
			result.put("detail", "탈퇴 정보를 모두 입력해 주세요");
		}else {
			try {
				apiService.deleteMember(member);
				result.put("msg", "OK");
			}catch(Exception e) {
				e.printStackTrace();
				result.put("msg", "NO");
				result.put("detail", e.getMessage());
			}
		}
		return result;
	}
}

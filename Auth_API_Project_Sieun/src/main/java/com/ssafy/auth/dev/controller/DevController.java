package com.ssafy.auth.dev.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.auth.dev.model.DevDto;
import com.ssafy.auth.dev.model.service.DevServiceImpl;

@RestController
@RequestMapping("/dev")
public class DevController {
	@Autowired
	DevServiceImpl devService;

	@PostMapping("/key")
	public Map<String, String> getDevKey(@RequestBody DevDto dev){
		Map<String, String> result = new HashMap<String, String>();
		if(dev.getId()==null||dev.getPw()==null) {
			result.put("msg", "아이디와 비밀번호를 입력하세요");
		}else {
		try {
			System.out.println("Key 요청 : " + dev.toString());
			String key = devService.getKey(dev);
			if(key != null) {
				result.put("key", key);
				result.put("msg", "개발자 키 발급이 완료 되었습니다!");
			}else {
				result.put("msg", "error : Fail to Generate Key");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg","error : " + e.getMessage());
		}
		}
		return result;
	}
}

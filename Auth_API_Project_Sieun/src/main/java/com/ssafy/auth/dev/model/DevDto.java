package com.ssafy.auth.dev.model;

import com.ssafy.util.MyException;

public class DevDto {
	private String id, pw, key;

	public String getId() {
		return id;
	}

	public void setId(String id) throws Exception {
		// 빈칸 or null check
		if (id == null || id == "") {
			throw new MyException("id 값이 잘못된 값 입니다.");
		}
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) throws Exception {
		// 빈칸 or null check
		if (pw == null || pw == "") {
			throw new MyException("비밀번호 값이 잘못된 값 입니다.");
		}
		this.pw = pw;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) throws Exception {
		// 빈칸 or null check
		if (key == null || key == "") {
			throw new MyException("키 값이 잘못된 값 입니다.");
		}
		this.key = key;
	}

	@Override
	public String toString() {
		return "DevDto [id=" + id + ", pw=" + pw + ", key=" + key + "]";
	}

}

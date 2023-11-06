package com.ssafy.auth.api.model;

import com.ssafy.util.MyException;

public class MemberDto {
	private String id, pw, email, birthday, dev_key, key, salt, name;

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		// 빈칸 or null check
		if (name == null || name == "") {
			throw new MyException("이름 값이 잘못된 값 입니다.");
		}
		this.name = name;
	}

	@Override
	public String toString() {
		return "memberDto [id=" + id + ", pw=" + pw + ", email=" + email + ", birthday=" + birthday + ", name=" + name
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws Exception {
		// 빈칸 or null check
		if (id == null || id == "") {
			throw new MyException("아이디 값이 잘못된 값 입니다.");
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		// 빈칸 or null check
		if (email == null || email == "") {
			throw new MyException("이메일 값이 잘못된 값 입니다.");
		}
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) throws Exception {
		// 빈칸 or null check
		if (birthday == null || birthday == "") {
			throw new MyException("생일 값이 잘못된 값 입니다.");
		}
		this.birthday = birthday;
	}

	public String getDev_key() {
		return dev_key;
	}

	public void setDev_key(String dev_key) throws Exception {
		// 빈칸 or null check
		if (dev_key == null || dev_key == "") {
			throw new MyException("개발자 키 값이 잘못된 값 입니다.");
		}
		this.dev_key = dev_key;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) throws Exception {
		// 빈칸 or null check
		if (salt == null || salt == "") {
			throw new MyException("salt 값이 잘못된 값 입니다.");
		}
		this.salt = salt;
	}

}

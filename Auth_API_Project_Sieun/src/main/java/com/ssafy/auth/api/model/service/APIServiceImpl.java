package com.ssafy.auth.api.model.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.auth.api.model.MemberDto;
import com.ssafy.auth.api.model.mapper.MemberMapper;
import com.ssafy.auth.dev.model.mapper.DevMapper;
import com.ssafy.util.OpenCrypt;

@Service
public class APIServiceImpl implements APIService {

	@Autowired
	MemberMapper memberMapper;
	@Autowired
	DevMapper devMapper;
	
	@Override
	public void signUpMember(MemberDto member) throws Exception {
		try {
			int dev_id = devMapper.readDevId(member.getDev_key());
			if(dev_id!=1) {
				throw new Exception("No such Developer");
			}else {
				byte[] dev_keyByte = OpenCrypt.hexToByteArray(member.getDev_key());
				String idCrypt = OpenCrypt.aesEncrypt(member.getId(), dev_keyByte);
				if(memberMapper.checkId(idCrypt)!=0) {
					throw new Exception("중복된 id");
				}
				member.setId(idCrypt);
				byte[] keyByte = OpenCrypt.generateKey("AES", 128);
				String salt = UUID.randomUUID().toString();
				String pwCrypt = OpenCrypt.aesEncrypt(member.getPw(), keyByte);
				byte[] pwSaltByte = OpenCrypt.getSHA256(pwCrypt, salt);
				String pwSalt = OpenCrypt.byteArrayToHex(pwSaltByte);
				String key = OpenCrypt.byteArrayToHex(keyByte);
				member.setKey(key);
				member.setPw(pwSalt);
				member.setSalt(salt);
				memberMapper.createKey(member);
				memberMapper.createMember(member);
			}
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Server error : Algorithm");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Sever error : SQL");
		}
	}

	@Override
	public MemberDto loginMember(MemberDto member) throws Exception {
		try {
			byte[] dev_keyByte = OpenCrypt.hexToByteArray(member.getDev_key());
			String idCrypt = OpenCrypt.aesEncrypt(member.getId(), dev_keyByte);
			String key = memberMapper.readKey(idCrypt);
			String salt = memberMapper.readSalt(idCrypt);
			if(key==null||salt==null) {
				throw new Exception("로그인 실패");
			}
			byte[] keyByte = OpenCrypt.hexToByteArray(key);
			String inputPw = member.getPw();
			String inputPwCrypt = OpenCrypt.aesEncrypt(inputPw, keyByte);
			byte[] inputPwSaltByte = OpenCrypt.getSHA256(inputPwCrypt, salt);
			String inputPwSalt = OpenCrypt.byteArrayToHex(inputPwSaltByte);
			String realPw = memberMapper.readPw(idCrypt);
			if(inputPwSalt.equals(realPw)) {
				member = memberMapper.readMember(idCrypt);
			}else {
				throw new Exception("로그인 실패");
			}
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Server error : Algorithm");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Sever error : SQL");
		}
		return member;
	}

	@Override
	public void deleteMember(MemberDto member) throws Exception {
		try {
			byte[] dev_keyByte = OpenCrypt.hexToByteArray(member.getDev_key());
			String idCrypt = OpenCrypt.aesEncrypt(member.getId(), dev_keyByte);
			String key = memberMapper.readKey(idCrypt);
			String salt = memberMapper.readSalt(idCrypt);
			if(key==null||salt==null) {
				throw new Exception("로그인 실패");
			}
			byte[] keyByte = OpenCrypt.hexToByteArray(key);
			String inputPw = member.getPw();
			String inputPwCrypt = OpenCrypt.aesEncrypt(inputPw, keyByte);
			byte[] inputPwSaltByte = OpenCrypt.getSHA256(inputPwCrypt, salt);
			String inputPwSalt = OpenCrypt.byteArrayToHex(inputPwSaltByte);
			String realPw = memberMapper.readPw(idCrypt);
			if(inputPwSalt.equals(realPw)) {
				memberMapper.deleteMember(idCrypt);
				memberMapper.deleteKey(idCrypt);
			}else {
				throw new Exception("탈퇴 실패");
			}
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Server error : Algorithm");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Sever error : SQL");
		}
	}

}

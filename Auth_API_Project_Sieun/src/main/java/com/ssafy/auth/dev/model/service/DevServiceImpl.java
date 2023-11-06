package com.ssafy.auth.dev.model.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.auth.dev.model.DevDto;
import com.ssafy.auth.dev.model.mapper.DevMapper;
import com.ssafy.util.OpenCrypt;

@Service
public class DevServiceImpl implements DevService{
	@Autowired
	DevMapper devMapper;
	
	@Override
	public String getKey(DevDto dev) throws Exception{
		String key = null;
		byte[] keyByte = null;
		try {
			key = devMapper.readKey(dev);
			if(key==null) {
				keyByte = OpenCrypt.generateKey("AES", 128);
				key = OpenCrypt.byteArrayToHex(keyByte);
				dev.setKey(key);
				String pw = OpenCrypt.aesEncrypt(dev.getPw(), keyByte);
				dev.setPw(pw);
				devMapper.createDev(dev);
				devMapper.createKey(dev);
				return key;
			}else {
				keyByte = OpenCrypt.hexToByteArray(key);
				String pwCrypt = devMapper.readPw(dev);
				String pw = OpenCrypt.aesDecrypt(pwCrypt, keyByte);
				if(pw.equals(dev.getPw())) {
					return key;
				}
				throw new Exception("Wrong Password");
			}
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			throw new Exception("Server Error Sorry");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Unable to Generat Key");
		}
	}

}

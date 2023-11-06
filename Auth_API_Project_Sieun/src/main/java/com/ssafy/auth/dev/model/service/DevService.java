package com.ssafy.auth.dev.model.service;

import com.ssafy.auth.dev.model.DevDto;

public interface DevService {
	String getKey(DevDto dev) throws Exception;
}

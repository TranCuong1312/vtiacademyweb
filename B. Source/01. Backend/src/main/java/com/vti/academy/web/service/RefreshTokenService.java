package com.vti.academy.web.service;


import org.springframework.stereotype.Service;

import com.vti.academy.web.model.RefreshToken;

@Service
public interface RefreshTokenService {

	
	public RefreshToken generateRefreshToken(String username);
	
	void validateRefreshToken(String token,String username);
	
	public void deleteRefreshToken(String token);
	
	public void checkExistToken(String token);
	
}

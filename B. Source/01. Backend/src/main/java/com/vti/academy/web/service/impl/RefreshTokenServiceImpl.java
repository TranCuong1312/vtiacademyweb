package com.vti.academy.web.service.impl;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.configuration.JwtTokenUtil;
import com.vti.academy.web.exception.SpringException;
import com.vti.academy.web.model.RefreshToken;
import com.vti.academy.web.repository.RefreshTokenRepository;
import com.vti.academy.web.service.RefreshTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
	
	private final JwtTokenUtil jwtTokenUtil;
	
	@Override
	public RefreshToken generateRefreshToken(String username) {
		
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(jwtTokenUtil.generateRefreshToken(username));
		
		/// Using sql.date instead of Instant.now()
		refreshToken.setCreatedDate(new Date(Calendar.getInstance().getTime().getTime()));
		
		refreshToken.setUsername(username);
		
		return refreshTokenRepository.save(refreshToken);
	}
	
	

	@Override
	public void validateRefreshToken(String token, String username) {
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringException("Invalid refresh Token"));
		
		if(!refreshToken.getUsername().equals(username)) {
			throw new SpringException("refreshToken not belong to this user");
		}
		
	}

	@Override
	public void deleteRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);

	}

	@Override
	public void checkExistToken(String token) {
		refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringException("RefreshToken not exist"));
		
	}

}

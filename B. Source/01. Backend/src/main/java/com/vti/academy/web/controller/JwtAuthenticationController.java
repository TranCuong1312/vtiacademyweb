package com.vti.academy.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.model.dto.RefreshTokenDTO;
import com.vti.academy.web.request.LoginRequest;
import com.vti.academy.web.request.ResetPasswordRequest;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.AccountService;

@RestController
@CrossOrigin
@RequestMapping("/auth/")
public class JwtAuthenticationController {

	@Autowired
	private AccountService accountService;
	
	
	@PostMapping("refresh-token")
	public CommonResponse genRefreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
		
		return accountService.refreshToken(refreshTokenDTO);
		
	}
	
	
	@PostMapping("authenticate")
	public CommonResponse login(@RequestBody @Valid LoginRequest loginRequest) {
		
		return accountService.login(loginRequest);
		
	}
	
	
	@PostMapping("reset-password")
	public CommonResponse resetPassword(@RequestBody ResetPasswordRequest resetRequest) {
		return accountService.resetPassword(resetRequest.getEmail());
	}
	
}
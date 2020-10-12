package com.vti.academy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.configuration.JwtTokenUtil;
import com.vti.academy.web.model.dto.RefreshTokenDTO;
import com.vti.academy.web.request.ChangePassRequest;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.AccountService;
import com.vti.academy.web.service.RefreshTokenService;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@GetMapping(value = "/resetPassword")
	@ResponseBody
	public CommonResponse resetPassword(@RequestParam String account) {
		return accountService.resetPassword(account);
	}
	
	
	@GetMapping(value ="/user-detail")
	public CommonResponse userDetail(HttpServletRequest request) {
		
		return accountService.getAccountInfo(jwtTokenUtil.getCurrentUsername(request));
	} 
	
	
	@PostMapping("/logout")
	public CommonResponse logout(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
		refreshTokenService.checkExistToken(refreshTokenDTO.getRefreshToken());
		
		refreshTokenService.deleteRefreshToken(refreshTokenDTO.getRefreshToken());
		
		return new CommonResponse(ResponseType.INFO.toString()
				, RestCode.SUCCESS.getCode(), "Refresh Token Deleted Success!");
	}
	
	
	
	@PostMapping("/change-password")
	public CommonResponse changePassword(@RequestBody ChangePassRequest changePassRequest, HttpServletRequest request) {
		
		return accountService.changePassword1(changePassRequest.getCurrentPass()
				, changePassRequest.getNewPass(), request);
		
	}
	
	
}

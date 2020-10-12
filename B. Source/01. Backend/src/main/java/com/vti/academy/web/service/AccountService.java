package com.vti.academy.web.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.vti.academy.web.model.User;
import com.vti.academy.web.model.dto.CommonDTO;
import com.vti.academy.web.model.dto.RefreshTokenDTO;
import com.vti.academy.web.request.LoginRequest;
import com.vti.academy.web.response.CommonResponse;

public interface AccountService {

	Optional<User> findByUserName(String userName);
	
	CommonResponse changePassword(String account, String oldPassword, String newPassword);
	
	CommonResponse getFirstLoginByAccount(String userName);
	
	CommonResponse updateAvatar(CommonDTO commonDTO);
	
	CommonResponse getAccountInfo(String userName);
	
	CommonResponse getListAccountWhenLoginWithAccountAdmin();
	
	Optional<User> findByDetailToken(JSONObject payload);
	
	CommonResponse genToken(String userName);
	
	CommonResponse getAuthenDetail(String accessToken);
	
	CommonResponse refreshToken(RefreshTokenDTO refreshTokenDTO);
	
	CommonResponse login(LoginRequest loginRequest);
	
	CommonResponse resetPassword(String gmail);
	
	CommonResponse changePassword1( String oldPassword, String newPassword, HttpServletRequest request);
	
	String getAutoGeneratedPassword();
}
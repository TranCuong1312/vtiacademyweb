package com.vti.academy.web.service.impl;

import java.time.Instant;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.configuration.JwtTokenUtil;
import com.vti.academy.web.exception.ClaimException;
import com.vti.academy.web.exception.SpringException;
import com.vti.academy.web.model.User;
import com.vti.academy.web.model.UserDetail;
import com.vti.academy.web.model.dto.CommonDTO;
import com.vti.academy.web.model.dto.RefreshTokenDTO;
import com.vti.academy.web.model.dto.UserDetailDTO;
import com.vti.academy.web.repository.UserDetailRepository;
import com.vti.academy.web.repository.UserRepository;
import com.vti.academy.web.request.LoginRequest;
import com.vti.academy.web.response.AuthenticationResponse;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.AccountService;
import com.vti.academy.web.service.EmailService;
import com.vti.academy.web.service.Mapper;
import com.vti.academy.web.service.RefreshTokenService;

@Service
@Transactional(rollbackFor = ClaimException.class)
public class AccountServiceImpl implements AccountService,Mapper<UserDetailDTO, UserDetail> {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private EmailService emailService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public CommonResponse changePassword(String account, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse getFirstLoginByAccount(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse updateAvatar(CommonDTO commonDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse getAccountInfo(String userName) {
//		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new SpringException("User not found"));
		
		UserDetailDTO dto = mapToDTO(userDetailRepository.findByUser(user)
				.orElseThrow(() ->  new SpringException("not found")));
		
		return new CommonResponse(ResponseType.INFO.toString()
				,RestCode.SUCCESS.getCode() , "Account info",dto);
	}

	@Override
	public CommonResponse getListAccountWhenLoginWithAccountAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findByDetailToken(JSONObject payload) {
		return userRepository.findByUsername(payload.getString("sub"));

	}

	@Override
	public CommonResponse genToken(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonResponse getAuthenDetail(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public CommonResponse refreshToken(RefreshTokenDTO refreshTokenDTO) {

		// Check if refreshToken exist
		refreshTokenService.validateRefreshToken(refreshTokenDTO.getRefreshToken(), refreshTokenDTO.getUsername());

		String token = jwtTokenUtil.generateToken(refreshTokenDTO.getUsername());

		String role = userRepository.findByUsername(refreshTokenDTO .getUsername()).get().getRole().toString();
		AuthenticationResponse authenticationResponse = AuthenticationResponse.builder().authenticationToken(token)
															.refreshToken(refreshTokenDTO.getRefreshToken())
															.expiresAt(Instant.now().plusMillis(jwtTokenUtil.getJwtExpirationInMillis()))
															.username(refreshTokenDTO.getUsername())
															.role(role).build();
		
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode()
				, "Get RefreshToken successful", authenticationResponse);
	}


	@Override
	public CommonResponse login(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenUtil.generateToken(loginRequest.getUsername());

		String role = userRepository.findByUsername(loginRequest.getUsername()).get().getRole().toString();
		AuthenticationResponse authenticationResponse =  AuthenticationResponse.builder()
															.authenticationToken(token)
															.refreshToken(refreshTokenService.generateRefreshToken(loginRequest.getUsername()).getToken())
															.expiresAt(Instant.now().plusMillis(jwtTokenUtil.getJwtExpirationInMillis()))
															.username(loginRequest.getUsername())
															.role(role).build();
		
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode()
				, "Authentication successful", authenticationResponse);
	}

	
	
	@Override
	public CommonResponse resetPassword(String email) {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new SpringException("This email has not been registered with any account"));

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String resetPasswordRaw = getAutoGeneratedPassword();
		String resetPassword = encoder.encode(resetPasswordRaw);

		// Send mail to user the reset password
		user.setPassword(resetPassword);
		userRepository.save(user);
		emailService.sendResetedPassword(user,resetPasswordRaw);
		
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(),
				"Successful, please access your email to receive your reset password ");
	}

	
	
	public String getAutoGeneratedPassword() {
		return "rs" + RandomStringUtils.randomAlphanumeric(10);
	}

	
	
	@Override
	public CommonResponse changePassword1(String oldPassword, String newPassword, HttpServletRequest request) {

		String username = jwtTokenUtil.getCurrentUsername(request);

		User currentUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new SpringException("Not found user"));

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(oldPassword, currentUser.getPassword())) {
			currentUser.setPassword(encoder.encode(newPassword));
			userRepository.save(currentUser);
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(),
					"Password change successful");
		}

		return new CommonResponse(ResponseType.ERROR.toString(), RestCode.BAD_REQUEST.getCode(),
				"Current Password is wrong");

	}

	@Override
	public UserDetailDTO mapToDTO(UserDetail entity) {
		// TODO Auto-generated method stub
		return UserDetailDTO.builder()
				.id(entity.getId())
				.lastName(entity.getLastName())
				.firstName(entity.getFirstName())
//				.user(entity.getUser())
				.build();
	}

	@Override
	public UserDetail mapToEntity(UserDetailDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

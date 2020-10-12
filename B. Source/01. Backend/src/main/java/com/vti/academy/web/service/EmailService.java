package com.vti.academy.web.service;

import org.springframework.stereotype.Service;

import com.vti.academy.web.model.User;
import com.vti.academy.web.model.dto.FormRegisterDTO;
@Service
public interface EmailService {
//	void sendRegisteredEmailType1(FormRegisterDTO formRegisterDTO);
	void sendNoticeEmailType1(FormRegisterDTO formRegisterDTO); 
	
//	void sendRegisteredEmailType3(FormRegisterDTO formRegisterDTO);
	void sendNoticeEmailType3(FormRegisterDTO formRegisterDTO); 

	void sendResetedPassword(User user, String passwordUpdated);
}

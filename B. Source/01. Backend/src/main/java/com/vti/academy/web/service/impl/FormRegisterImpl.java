package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.academy.web.configuration.DriveSheets;
import com.vti.academy.web.exception.SpringException;
import com.vti.academy.web.model.Course;
import com.vti.academy.web.model.FormRegister;
import com.vti.academy.web.model.dto.FormRegisterDTO;
import com.vti.academy.web.repository.CourseRepository;
import com.vti.academy.web.repository.FormRegisterRepository;
import com.vti.academy.web.service.EmailService;
import com.vti.academy.web.service.FormRegisterService;
import com.vti.academy.web.service.Mapper;

@Service
public class FormRegisterImpl implements FormRegisterService, Mapper<FormRegisterDTO, FormRegister> {
	
	@Autowired
	private FormRegisterRepository formRegisterRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private DriveSheets toDriveSheets;

	@Override
	public void addFormRegister(FormRegisterDTO formRegisterDTO, int type) throws IOException, GeneralSecurityException {
		pushToSheet(formRegisterDTO);
		Course course = courseRepository.findByName(formRegisterDTO.getCourse());
		FormRegister formRegister = new FormRegister(formRegisterDTO.getEmail(), formRegisterDTO.getName(), formRegisterDTO.getPhone(), type, course);
			switch (type) {
			case 1:
//				emailService.sendRegisteredEmailType1(formRegisterDTO);
				emailService.sendNoticeEmailType1(formRegisterDTO); 
				formRegisterRepository.save(formRegister);
				break;
//			case 2:
//				formRegisterRepository.save(formRegister);
//				break;
			case 3:
//				emailService.sendRegisteredEmailType3(formRegisterDTO);
				emailService.sendNoticeEmailType3(formRegisterDTO);
				formRegisterRepository.save(formRegister);
				break;
			default:
				throw new SpringException("Type register invalid");
		}
	}	

	private void pushToSheet(FormRegisterDTO formRegisterDTO) throws IOException, GeneralSecurityException {
		toDriveSheets.toSheet(formRegisterDTO.getName(), formRegisterDTO.getPhone(), formRegisterDTO.getEmail());
	}

	@Override
	public FormRegisterDTO mapToDTO(FormRegister entity) {
		return null;
	}

	@Override
	public FormRegister mapToEntity(FormRegisterDTO dto) {
		Date date = new Date();
		return FormRegister.builder().date(date).email(dto.getEmail()).name(dto.getName())
				.phone(dto.getPhone()).course(courseRepository.findByName(dto.getCourse())).build();
	}

}


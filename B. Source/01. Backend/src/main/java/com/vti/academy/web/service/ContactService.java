package com.vti.academy.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vti.academy.web.model.dto.ContactDTO;

public interface ContactService {
	
	List<ContactDTO> getContact(Pageable pageable);

	void saveContact(ContactDTO contactDTO);
}

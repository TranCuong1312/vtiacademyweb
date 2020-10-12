package com.vti.academy.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.academy.web.model.Contact;
import com.vti.academy.web.model.dto.ContactDTO;
import com.vti.academy.web.repository.ContactRepository;
import com.vti.academy.web.service.ContactService;


@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	private ContactDTO mapToDTO(Contact contact) {
		return ContactDTO.builder()
				.address(contact.getAddress())
				.address2(contact.getAddress2())
				.domain(contact.getDomain())
				.email(contact.getEmail())
				.facebook(contact.getFacebook())
				.id(contact.getId())
				.phone(contact.getPhone())
				.phone2(contact.getPhone2())
				.youtube(contact.getYoutube())
				.build();
	}
	
	private Contact mapToContact(ContactDTO contactDTO) {
		return Contact.builder()
				.address(contactDTO.getAddress())
				.address2(contactDTO.getAddress2())
				.domain(contactDTO.getDomain())
				.email(contactDTO.getEmail())
				.facebook(contactDTO.getFacebook())
				.id(contactDTO.getId())
				.phone(contactDTO.getPhone())
				.phone2(contactDTO.getPhone2())
				.youtube(contactDTO.getYoutube())
				.build();
	}
	
	@Override
	public List<ContactDTO> getContact(Pageable pageable) {
		return contactRepository.findAll(pageable).getContent()
				.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void saveContact(ContactDTO contactDTO) {
		contactRepository.save(mapToContact(contactDTO));
		
	}

}

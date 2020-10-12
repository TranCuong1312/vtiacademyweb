package com.vti.academy.web.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.dto.ContactDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.ContactService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@PutMapping("/contact/{id}/edit")
	public CommonResponse editContact(@Valid @PathVariable int id,@RequestBody ContactDTO contactDTO){
		contactDTO.setId(id);
		contactService.saveContact(contactDTO);
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Edit Success");
	}
}

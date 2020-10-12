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
import com.vti.academy.web.model.dto.AboutUsDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.AboutUsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin")
public class AboutUsController {

	@Autowired
	private AboutUsService aboutUsService; 
	
	@PutMapping("/aboutus/{id}")
	public CommonResponse editAboutUs(@Valid @RequestBody AboutUsDTO aboutUsDTO,@PathVariable Long id){
		aboutUsDTO.setId(id);
		aboutUsService.saveAboutUs(aboutUsDTO);
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Edit success");
	}
	
}

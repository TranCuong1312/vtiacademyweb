package com.vti.academy.web.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.Business_training;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.Business_trainingService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin/business_training")
public class Business_trainingController {
	
	@Autowired
	private Business_trainingService business_trainingSer;
	
	@GetMapping
	public CommonResponse getAll() {
		List<Business_training> data = business_trainingSer.getAll();
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",data);
	}
	
	@PostMapping("/edit/{id}")
	public ResponseEntity<?> editBusiness_training(@PathVariable String id, @RequestParam("business_training") String business_training) throws JsonParseException, JsonMappingException, IOException{
		String response = business_trainingSer.editBusiness_training(id, business_training);
		return new ResponseEntity<>("Edit success!", HttpStatus.OK);
	}
}

package com.vti.academy.web.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.service.OtherConfigService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin/otherConfigs")
public class OtherConfigController {
	
	@Autowired
	private OtherConfigService otherConfigService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllOtherConfig() {	
		return new ResponseEntity<>(otherConfigService.getAllOtherConfig(), HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> editOtherConfig(@RequestParam("otherConfig") String otherConfig) throws JsonParseException, JsonMappingException, IOException{
		otherConfigService.editOtherConfigNoImage(otherConfig);
		return new ResponseEntity<>("Edit success!", HttpStatus.OK);
	}
	
	@PostMapping("/edit/image")
	public ResponseEntity<?> editOtherConfigHaveImage(@RequestParam("image") MultipartFile image,
			@RequestParam("otherConfig") String otherConfig) throws Exception {
		
		String response = otherConfigService.editOtherConfig(image, otherConfig);
		
		if(response == "file name exists") 
			return new ResponseEntity<>("Unsucces!", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>("Edit success!", HttpStatus.OK);
	}
}

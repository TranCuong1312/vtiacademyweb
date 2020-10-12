package com.vti.academy.web.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.dto.MentorsDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.response.PaginationResponse;
import com.vti.academy.web.service.MentorsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin/mentors")
public class MentorsController {

	@Autowired
	private MentorsService mentorsService;


	
	@GetMapping
	public CommonResponse getAll(Pageable pageable){PaginationResponse<MentorsDTO> data = new PaginationResponse<MentorsDTO>(mentorsService.getAll(pageable), "admin/mentor");
		
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success", data);

	}
	
	@GetMapping("/{id}")
	public CommonResponse getMentorById(@PathVariable Long id) {
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success",
				mentorsService.getMentorByID(id));
	
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> saveMentor(@RequestParam("image") MultipartFile image, @RequestParam("mentor") String mentor) throws Exception{
		String response = mentorsService.saveMentor(image, mentor);
		if(response == "file name exists") 
			return new ResponseEntity<>("Unsuccess!", HttpStatus.BAD_REQUEST);
		else
		return new ResponseEntity<>("Insert success!", HttpStatus.OK);
	}
	
	@PostMapping("/edit/{id}")
	public ResponseEntity<?> editMentor(@RequestParam("mentor") String mentor, @PathVariable String id) throws JsonParseException, JsonMappingException, IOException{
		mentorsService.editMentor(mentor, id);
		return new ResponseEntity<>("Insert success!", HttpStatus.OK);
	}
	
	@PostMapping("/edit/image/{id}")
	public ResponseEntity<?> editMentorHaveImage(@RequestParam("image") MultipartFile image,
			@RequestParam("mentor") String mentor, @PathVariable String id) throws Exception{
		String response = mentorsService.editMentorHaveImage(image, mentor, id);
		if(response == "fail") 
			return new ResponseEntity<>("Unsucces!", HttpStatus.BAD_REQUEST);
		else
		return new ResponseEntity<>("Insert success!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public CommonResponse deleteMentorById(@PathVariable Long id) {
		String response = mentorsService.deleteMentorById(id);
		if(response == "file delete fail") {
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.BAD_REQUEST.getCode(), response);
		}
		else
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), response);
	}

}
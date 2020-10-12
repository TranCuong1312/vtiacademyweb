package com.vti.academy.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.model.dto.IntroDTO;
import com.vti.academy.web.service.IntroService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private IntroService introService;
	@PutMapping("intro/edit/{id}")
	public ResponseEntity<?> editIntroById( @PathVariable Long id, @RequestBody @Valid IntroDTO introDTO) {
		introService.editIntroById(id, introDTO);
		return new ResponseEntity<>("Update success!", HttpStatus.OK);
	}
}

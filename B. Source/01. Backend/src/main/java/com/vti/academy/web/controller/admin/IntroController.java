package com.vti.academy.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.dto.IntroDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.IntroService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin/intro")
public class IntroController {
	@Autowired
	private IntroService introService;
	
	@GetMapping("")
	public CommonResponse getCourseById(Pageable pageable) {
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success", 
				introService.listAll(pageable));
	}

	@PutMapping("/{id}")
	public CommonResponse editIntroById(@PathVariable Long id, @RequestBody IntroDTO introDTO) {
		introService.editIntroById(id, introDTO);
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(),
				"Get All SubCourses By Id Course Successful");
	}

}

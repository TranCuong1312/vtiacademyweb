package com.vti.academy.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.dto.SubCourseDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.response.PaginationResponse;
import com.vti.academy.web.service.SubCourseService;

@RestController
@RequestMapping("/admin/Sub-Course")
public class SubCourseController {

	@Autowired
	private SubCourseService subCourseService;

	@GetMapping
	public CommonResponse getAll(Pageable pageable) {
		PaginationResponse<SubCourseDTO> data = new PaginationResponse<SubCourseDTO>(subCourseService.getAll(pageable), "admin/news");
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",data);
		
	}
	
	@GetMapping("/getAll")
	public CommonResponse getAll() {
		List<SubCourseDTO> data = subCourseService.getAll();
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",data);
		
	}
	
	

	@PostMapping("/add")
	public CommonResponse addSubCourse(@RequestBody SubCourseDTO dto) {
		subCourseService.addSubCourse(dto);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Create success");
	}

	@GetMapping("/{id}")
	public CommonResponse getSubCourseById(@PathVariable Long id) {
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success", 
				subCourseService.getSubCourseById(id));
	}

	@DeleteMapping("/delete/{id}")
	public CommonResponse deleteCourseById(@PathVariable Long id) {
		subCourseService.deleteSubCourseById(id);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Create success");
	}

	@PutMapping("/update/{id}")
	public CommonResponse editSubCourseById(@PathVariable Long id, @RequestBody SubCourseDTO dto) {
		subCourseService.editSubCourseById(id, dto);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Update success");
	}

}

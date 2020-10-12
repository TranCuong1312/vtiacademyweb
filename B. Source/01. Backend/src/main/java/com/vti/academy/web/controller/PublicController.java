package com.vti.academy.web.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
//import com.vti.academy.web.configuration.DriveSheets;
import com.vti.academy.web.model.Type;
import com.vti.academy.web.model.dto.FormRegisterDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.AboutUsService;
import com.vti.academy.web.service.ContactService;
import com.vti.academy.web.service.CourseService;
import com.vti.academy.web.service.EnterpriseService;
import com.vti.academy.web.service.FormRegisterService;
import com.vti.academy.web.service.IntroService;
import com.vti.academy.web.service.MentorsService;
import com.vti.academy.web.service.NewsService;
import com.vti.academy.web.service.OtherConfigService;
import com.vti.academy.web.service.ReviewsService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private AboutUsService aboutUsService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private IntroService introService;
	@Autowired
	private ReviewsService reviewsService;
	@Autowired
	private MentorsService mentorsService;
	@Autowired
	private OtherConfigService otherConfigService;
	
	 @Autowired private FormRegisterService formRegisterService;
	 @Autowired private EnterpriseService enterpriseService;
	 
	
//	@Autowired
//	private EmailService emailService;
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/enterprise")
	public CommonResponse getAllEnterprise() {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				enterpriseService.getAllEnterprise());
	}

	@GetMapping("/mentors")
	public CommonResponse getAll() {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				mentorsService.getAll());
	}

	@GetMapping("/aboutus")
	public CommonResponse getAboutUs(Pageable pageable) {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				aboutUsService.listAll(pageable));
	}

	@GetMapping("/news")
	public CommonResponse getTop3News() {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				newsService.getTop3News());
	}

	@GetMapping("/news/{id}")
	public CommonResponse getNewsById(@PathVariable Long id) {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				newsService.getNewsById(id));
	}

	 @GetMapping("/news-random")
	 public CommonResponse getNewsRandom() {
	 	return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
	 			newsService.getTop3News());
	 }

	@GetMapping("/intro")
	public CommonResponse getIntro(Pageable pageable) {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				introService.listAll(pageable));
	}

	@GetMapping("/courses")
	public CommonResponse getAllCourses() {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				courseService.listAllCourse());
	}

	@GetMapping("/courses/{id}")
	public CommonResponse getCourseById(@PathVariable Long id) {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				courseService.getCourseById(id));
	}

	@GetMapping("/reviews/{type}")
	public CommonResponse listReviewByType(@PathVariable("type") Type type) {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",
				reviewsService.getReviewsByType(type));
	}

	
	  @PostMapping("/register/{type}") 
	  public CommonResponse createScholarshipRegister(@RequestBody @Valid FormRegisterDTO formRegisterDTO, @PathVariable int type) throws IOException, GeneralSecurityException {
		  formRegisterService.addFormRegister(formRegisterDTO, type); 
		  return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(),"Create success"); 
	  }
	 

	@GetMapping("/contact")
	public CommonResponse getContact(Pageable pageable) {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",
				contactService.getContact(pageable));
	}
	
	@GetMapping("/otherConfig")
	public CommonResponse getOtherConfig() {
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",
				otherConfigService.getAllOtherConfig());
	}
	
	
}

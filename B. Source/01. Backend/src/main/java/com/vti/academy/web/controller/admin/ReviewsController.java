package com.vti.academy.web.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.vti.academy.web.model.Type;
import com.vti.academy.web.model.dto.ReviewsDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.response.PaginationResponse;
import com.vti.academy.web.service.ReviewsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/reviews")
public class ReviewsController {

	@Autowired
	private ReviewsService reviewsService;

	@GetMapping("/type/{type}")
	public CommonResponse getAll(@PathVariable("type") Type type, Pageable pageable) {
		PaginationResponse<ReviewsDTO> data = new PaginationResponse<ReviewsDTO>(reviewsService.getReviewsByTypeAndPaging(type, pageable), "admin/news");
		return new CommonResponse(ResponseType.INFO.toString(),RestCode.SUCCESS.getCode(), "Get Success", data);

	}

//	@GetMapping(value = "/reviews/{id}")
//	public ResponseEntity<?> getReviewsById(@PathVariable(name = "id") Long id) {
//		return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
	@GetMapping("/{id}")
	public CommonResponse getReviewsById(@PathVariable Long id) {
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success",
				reviewsService.getReviewsById(id));
	}

//	@PostMapping("/reviews/add")
//	public ResponseEntity<?> createReview(@Valid @RequestBody Reviews entity) {
//		return ResponseEntity.status(HttpStatus.OK).body(service.createReviews(entity));
//
	@PostMapping("/add")
	public CommonResponse addReviews(@RequestParam("image") MultipartFile image, @RequestParam("review") String review) throws JsonParseException, JsonMappingException, IOException {
		String response = reviewsService.addReviews(image, review);
		if(response == "file name exists")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);
	}

	@PostMapping("/edit/image/{id}")
	public CommonResponse editReviewsHaveImageById(@RequestParam("image") MultipartFile image,
			@RequestParam("review") String review,
			@PathVariable Long id) throws JsonParseException, JsonMappingException, IOException{
		String response = reviewsService.editReviewsHaveImageById(image, review, id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);
	}
	
	@PostMapping("/edit/{id}")
	public CommonResponse editReviewsById(
			@RequestParam("review") String review,
			@PathVariable Long id) throws JsonParseException, JsonMappingException, IOException{
		String response = reviewsService.editReviewsById( review, id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);
	}

	@DeleteMapping("/delete/{id}")
	public CommonResponse deleteReviewsById(@PathVariable Long id) {
		String response = reviewsService.deleteReviewsById(id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.BAD_REQUEST.getCode(),
				response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				response);

	}

}

package com.vti.academy.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.model.Reviews;
import com.vti.academy.web.model.Type;
import com.vti.academy.web.model.dto.ReviewsDTO;

@Service
public interface ReviewsService {
//
//	public List<ReviewsDTO> getAll(Pageable pageable);

	public List<ReviewsDTO> getReviewsByType(Type type);
	
	public Page<ReviewsDTO> getReviewsByTypeAndPaging(Type type, Pageable pageable);

	public Reviews getById(Long id);

	String addReviews(MultipartFile image, String review) throws JsonParseException, JsonMappingException, IOException;

	String editReviewsHaveImageById(MultipartFile image, String review, Long id) throws JsonParseException, JsonMappingException, IOException;
	
	String editReviewsById(String review, Long id) throws JsonParseException, JsonMappingException, IOException;

	String deleteReviewsById(Long id);
	
	ReviewsDTO getReviewsById(Long id);
}

package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.Reviews;
import com.vti.academy.web.model.Type;
import com.vti.academy.web.model.dto.ReviewsDTO;
import com.vti.academy.web.repository.ReviewsRepository;
import com.vti.academy.web.service.Mapper;
import com.vti.academy.web.service.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService, Mapper<ReviewsDTO, Reviews> {

	@Autowired
	private ReviewsRepository repository;

	@Override
	public List<ReviewsDTO> getReviewsByType(Type type) {
		if (type.equals(Type.CHUYENGIA)) {
			return repository.getReviewByType("CHUYENGIA").stream().map(this::mapToDTO).collect(Collectors.toList());
		} else if (type.equals(Type.HOCVIEN)) {
			return repository.getReviewByType("HOCVIEN").stream().map(this::mapToDTO).collect(Collectors.toList());
		} else {
			return repository.getReviewByType("DOANHNGHIEP").stream().map(this::mapToDTO).collect(Collectors.toList());
		}

	}
	@Override
	public Page<ReviewsDTO> getReviewsByTypeAndPaging(Type type, Pageable pageable) {
		if (type.equals(Type.CHUYENGIA)) {
			return repository.getReviewByType(Type.CHUYENGIA, pageable).map(this::mapToDTO);
		} else if (type.equals(Type.HOCVIEN)) {
			return repository.getReviewByType(Type.HOCVIEN, pageable).map(this::mapToDTO);
		} else {
			return repository.getReviewByType(Type.DOANHNGHIEP, pageable).map(this::mapToDTO);
		}
	}

	@Override
	public Reviews getById(Long id) {

		return repository.getById(id);

	}

	@Override
	public ReviewsDTO getReviewsById(Long id) {
		return mapToDTO(repository.findOne(id));
	}

	@Override
	public ReviewsDTO mapToDTO(Reviews entity) {
		return ReviewsDTO.builder().id(entity.getId()).image(entity.getImage()).content(entity.getContent())
				.reviewerName(entity.getReviewerName()).office(entity.getOffice()).type(entity.getType()).build();
	}

	@Override
	public Reviews mapToEntity(ReviewsDTO entity) {
		return Reviews.builder().id(entity.getId()).image(entity.getImage()).content(entity.getContent())
				.reviewerName(entity.getReviewerName()).office(entity.getOffice()).type(entity.getType()).build();

	}
	@Override
	public String addReviews(MultipartFile image, String review) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		String response = "done";
		TemplateService ts = new TemplateService();
		
		Reviews newReview = new ObjectMapper().readValue(review, Reviews.class);
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		if(newReview.getImage().indexOf(".") >= 0) {
			newReview.setImage(ts.SplitTakeStartName(newReview.getImage()));
		}
		String modifiedName = newReview.getImage()
								+"."+extension;
		
		String fileSrc = "\\Reviews\\";
		if(newReview.getType().toString() == "CHUYENGIA") {
			fileSrc = fileSrc + "Chuyengia\\";
		}else {
			fileSrc = fileSrc + "Hocvien\\";
		}
		
		
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "file name exists") {
			response = createSuccess;
		}
		else {
			newReview.setImage(modifiedName);
			repository.save(newReview);
		}
		
		return response;
	}
	
	@SuppressWarnings("unused")
	@Override
	public String editReviewsHaveImageById(MultipartFile image, String review, Long id) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		TemplateService ts = new TemplateService();
		Reviews newReview = new ObjectMapper().readValue(review, Reviews.class);
		String fileSrc = "\\Reviews\\";
		
		if(newReview.getType().toString() == "CHUYENGIA") {
			fileSrc = fileSrc + "Chuyengia\\";
		}else {
			fileSrc = fileSrc + "Hocvien\\";
		}
	
		//Delete old file
		Reviews oldReview = repository.findOne(id);
		String deleteOldImage = ts.deleteFile(fileSrc, oldReview.getImage());
		
		//Prepare create new file
		String modifiedName;
		if(newReview.getImage().indexOf(".") >= 0) {
//			String[] newWords = branchNewsDTO.getImage().split("\\.");
			newReview.setImage(ts.SplitTakeStartName(newReview.getImage()));
		}
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		modifiedName =newReview.getImage()
				+"."+extension;
		
		//Create new file
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "done"){
			newReview.setImage(modifiedName);
			editReviewFunction(oldReview, newReview);
		}
		
		return createSuccess; 
	}
	@SuppressWarnings("unused")
	@Override
	public String editReviewsById(String review, Long id) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Reviews newReview = new ObjectMapper().readValue(review, Reviews.class);
		String fileSrc = "\\Reviews\\";
		
		if(newReview.getType().toString() == "CHUYENGIA") {
			fileSrc = fileSrc + "Chuyengia\\";
		}else {
			fileSrc = fileSrc + "Hocvien\\";
		}
	
		TemplateService ts = new TemplateService();
		
		Reviews oldReview = repository.findOne(id);
		
		editReviewFunction(oldReview, newReview);
		return "done";
	}
	
	
	@Override
	public String deleteReviewsById(Long id) {
		// TODO Auto-generated method stub
		TemplateService ts = new TemplateService();
		String fileSrc = "\\Reviews\\";
		Reviews deleteReview = repository.findOne(id);
		if(deleteReview.getType().toString() == "CHUYENGIA") {
			fileSrc = fileSrc + "Chuyengia\\";
		}else {
			fileSrc = fileSrc + "Hocvien\\";
		}
		
		String response = ts.deleteFile(fileSrc, deleteReview.getImage());
		if(response == "file deleted") {
			repository.delete(id);
		}
		return response;
	}
	
	void editReviewFunction(Reviews oldReview, Reviews newReview) {
		oldReview.setImage(newReview.getImage());
		oldReview.setContent(newReview.getContent());
		oldReview.setReviewerName(newReview.getReviewerName());
		oldReview.setOffice(newReview.getOffice());
		oldReview.setType(newReview.getType());

		repository.save(oldReview);
	}
	
}

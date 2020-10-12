package com.vti.academy.web.service;

import java.io.IOException;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.model.Business_training;

public interface Business_trainingService {
	
	List<Business_training> getAll();
	
	String editBusiness_training(String id, String business_training) throws JsonParseException, JsonMappingException, IOException;
}

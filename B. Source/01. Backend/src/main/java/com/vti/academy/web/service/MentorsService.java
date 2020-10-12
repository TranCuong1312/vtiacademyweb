package com.vti.academy.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.model.Mentors;

import com.vti.academy.web.model.dto.MentorsDTO;

@Service
public interface MentorsService {
	public Page<MentorsDTO> getAll(Pageable pageable);
	
	public List<MentorsDTO> getAll();

	public Mentors getById(Long id);	
	
	MentorsDTO getMentorByID(Long id);
	
	String saveMentor(MultipartFile image, String mentor) throws IOException;
	
	String editMentor(String mentor, String id) throws JsonParseException, JsonMappingException, IOException;
	
	String editMentorHaveImage(MultipartFile image, String mentor, String id) throws IOException;

	String deleteMentorById(Long id);
	


}

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
import com.vti.academy.web.model.Mentors;
import com.vti.academy.web.model.dto.MentorsDTO;
import com.vti.academy.web.repository.MentorsRepository;
import com.vti.academy.web.service.Mapper;
import com.vti.academy.web.service.MentorsService;

@Service
public class MentorsServiceImpl implements MentorsService,Mapper<MentorsDTO, Mentors> {
	@Autowired
	private MentorsRepository mentorRepository;

	@Override
	public Page<MentorsDTO> getAll(Pageable pageable) {
		return mentorRepository.findAll(pageable).map(this::mapToDTO);
	}
	
	@Override
	public List<MentorsDTO> getAll() {
		return mentorRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public Mentors getById(Long id) {

		return mentorRepository.getById(id);
	}

	@Override
	public MentorsDTO mapToDTO(Mentors entity) {
		return MentorsDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.content(entity.getContent())
				.position(entity.getPosition())
				.img(entity.getImg())
				.build();
		
	}

	@Override
	public Mentors mapToEntity(MentorsDTO dto) {
		return Mentors.builder()
				.id(dto.getId())
				.name(dto.getName())
				.position(dto.getPosition())
				.img(dto.getImg())
				.content(dto.getContent())
				.build();
	}

	@Override
	public MentorsDTO getMentorByID(Long id) {
		return mapToDTO(mentorRepository.findOne(id));
	}

	@Override
	public String saveMentor(MultipartFile image, String mentor) throws IOException {
		String response ="done";
		TemplateService ts = new TemplateService();
		
		Mentors mentors = new ObjectMapper().readValue(mentor, Mentors.class);
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		if(mentors.getImg().indexOf(".") >= 0) {
			mentors.setImg(ts.SplitTakeStartName(mentors.getImg()));
		}
		String modifiedName = mentors.getImg()
								+"."+extension;
		String fileSrc = "\\mentors\\";
		
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "file name exists") {
			response = "file name exists";
		} else {
			mentors.setImg(modifiedName);
			mentorRepository.save(mentors);
		}
		return response;
	}

	@SuppressWarnings("unused")
	@Override
	public String editMentor(String mentor, String id) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		String fileSrc = "\\mentors\\";
		TemplateService ts = new TemplateService();
		Mentors branchNews = new ObjectMapper().readValue(mentor, Mentors.class);
		long idEdit = Long.parseLong(id);
		Mentors mentorOld = mentorRepository.findOne(idEdit);
		
		editMentorFunction(branchNews, mentorOld);
		
		return "done";
	}

	@SuppressWarnings("unused")
	@Override
	public String editMentorHaveImage(MultipartFile image, String mentor, String id) throws IOException {
		
		TemplateService ts = new TemplateService();
		
		String fileSrc = "\\mentors\\";
		
		long idEdit = Long.parseLong(id);
		
		//Delete old file
		Mentors oldMentor = mentorRepository.findOne(idEdit);
		String deleteOldImage = ts.deleteFile(fileSrc, oldMentor.getImg());
		
		//Prepare create new file
		Mentors branchNews = new ObjectMapper().readValue(mentor, Mentors.class);
		String modifiedName;
		if(branchNews.getImg().indexOf(".") >= 0) {
			branchNews.setImg(ts.SplitTakeStartName(branchNews.getImg()));
		}
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		modifiedName =branchNews.getImg()
				+"."+extension;
		
		//Create new file
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "done"){
			branchNews.setImg(modifiedName);
			editMentorFunction(branchNews, oldMentor);
		}
		
		return createSuccess; 
	}

	void editMentorFunction(Mentors newMentor, Mentors oldMentor) {
		
		oldMentor.setName(newMentor.getName());
		oldMentor.setPosition(newMentor.getPosition());
		oldMentor.setContent(newMentor.getContent());
		oldMentor.setImg(newMentor.getImg());
		
		mentorRepository.save(oldMentor);
	}

	@Override
	public String deleteMentorById(Long id) {
		TemplateService ts = new TemplateService();
		String fileSrc = "\\mentors\\";
		Mentors mentor = mentorRepository.findOne(id);
		String response = ts.deleteFile(fileSrc, mentor.getImg());
		if(response == "file deleted") {
			mentorRepository.delete(id);
		}
		return response;
	}

}

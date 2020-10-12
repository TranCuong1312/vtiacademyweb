package com.vti.academy.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.academy.web.exception.NotFoundException;
import com.vti.academy.web.model.AboutUs;
import com.vti.academy.web.model.dto.AboutUsDTO;
import com.vti.academy.web.repository.AboutUsRepository;
import com.vti.academy.web.service.AboutUsService;
import com.vti.academy.web.service.Mapper;

@Service
public class AboutUsServiceImpl implements AboutUsService,Mapper<AboutUsDTO, AboutUs> {

	@Autowired
	private AboutUsRepository aboutUsRepository;

	@Override
	public List<AboutUsDTO> listAll(Pageable pageable) {
		return aboutUsRepository.findAll(pageable).getContent()
				.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
	
	public Page<AboutUs> getAll(Pageable pageable){
		return  aboutUsRepository.findAll(pageable);
	}

	@Override
	public AboutUsDTO mapToDTO(AboutUs aboutUs) {
		return AboutUsDTO.builder().id(aboutUs.getId()).description(aboutUs.getDescription()).video(aboutUs.getVideo())
				.titleVideo(aboutUs.getTitleVideo()).descriptionVideo(aboutUs.getDescriptionVideo()).build();
	}

	@Override
	public AboutUs mapToEntity(AboutUsDTO dto) {
		return AboutUs.builder().id(dto.getId()).description(dto.getDescription()).video(dto.getVideo())
				.titleVideo(dto.getTitleVideo()).descriptionVideo(dto.getDescriptionVideo()).build();
	}
	

	@Override
	public void saveAboutUs(AboutUsDTO aboutUsDTO) {
		aboutUsRepository.save(mapToEntity(aboutUsDTO));
	}

	@Override
	public void editAboutUs(Long id, AboutUsDTO aboutUsDTO) {
		if(!aboutUsRepository.exists(id)) {
			throw new NotFoundException("Course not found!");
		}
		AboutUs aboutUs = aboutUsRepository.findOne(aboutUsDTO.getId());
		aboutUs = mapToEntity(aboutUsDTO);
		aboutUsRepository.save(aboutUs);	
	}



}

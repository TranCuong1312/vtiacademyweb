package com.vti.academy.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.academy.web.model.Intro;
import com.vti.academy.web.model.dto.IntroDTO;
import com.vti.academy.web.repository.IntroRepository;
import com.vti.academy.web.service.IntroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IntroServiceImpl  implements IntroService{
	@Autowired
	private IntroRepository introRepository;

	@Override
	public List<IntroDTO> listAll(Pageable pageable) {
		return introRepository.findAll(pageable).getContent()
				.stream()
				.map(this::mapToDto)
				.collect(Collectors.toList());
				
	}
	
	private IntroDTO mapToDto(Intro intro) {
		return IntroDTO.builder()
					.id(intro.getId())
					.name(intro.getName())
					.description(intro.getDescription())
					.build();
	}

	@Override
	public void editIntroById(Long id, IntroDTO introDTO) {
		Intro intro= introRepository.findOne(id);
		intro = setValue(intro, introDTO);	
		introRepository.save(intro);
	}

	@Override
	public Intro setValue(Intro entity, IntroDTO dto) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	
}

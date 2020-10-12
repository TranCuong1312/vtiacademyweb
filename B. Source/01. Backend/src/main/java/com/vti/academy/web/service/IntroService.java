package com.vti.academy.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.academy.web.model.Intro;
import com.vti.academy.web.model.dto.IntroDTO;

@Service
public interface IntroService {
	List<IntroDTO> listAll(Pageable pageable);
	
	void editIntroById(Long id, IntroDTO introDTO);

	Intro setValue(Intro entity, IntroDTO dto);
}

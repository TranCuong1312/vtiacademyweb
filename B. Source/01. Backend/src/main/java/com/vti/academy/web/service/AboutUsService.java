package com.vti.academy.web.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.vti.academy.web.model.dto.AboutUsDTO;
@Service
public interface AboutUsService {
	List<AboutUsDTO> listAll(Pageable pageable);
	
	void saveAboutUs(AboutUsDTO aboutUsDTO);
	void editAboutUs(Long id, AboutUsDTO aboutUsDTO);
}

package com.vti.academy.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.model.OtherConfig;

public interface OtherConfigService {
	
	List<OtherConfig> getAllOtherConfig();

	String editOtherConfig(MultipartFile image, String otherConfig) throws IOException;
	
	String editOtherConfigNoImage(String otherConfig) throws JsonParseException, JsonMappingException, IOException;
}

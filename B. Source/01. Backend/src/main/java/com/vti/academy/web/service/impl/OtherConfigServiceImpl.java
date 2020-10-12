package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.OtherConfig;
import com.vti.academy.web.repository.OtherConfigRepository;
import com.vti.academy.web.service.OtherConfigService;

@Service
public class OtherConfigServiceImpl implements OtherConfigService {

	@Autowired
	private OtherConfigRepository otherConfigRepository;

	@Override
	public List<OtherConfig> getAllOtherConfig() {
		return otherConfigRepository.findAll();
	}
	
	@SuppressWarnings("unused")
	@Override
	public String editOtherConfigNoImage(String otherConfig)
			throws JsonParseException, JsonMappingException, IOException {
		
		String fileSrc = "\\other-config\\";
		
		TemplateService ts = new TemplateService();
		
		OtherConfig newOtherConfig = new ObjectMapper().readValue(otherConfig, OtherConfig.class);
		
		List<OtherConfig> listOtherConfig = otherConfigRepository.findAll();
		OtherConfig oldOtherConfig = listOtherConfig.get(0);
		
		
		editOtherConfigFunction(newOtherConfig, oldOtherConfig);
		return "done";
	}

	@SuppressWarnings("unused")
	@Override
	public String editOtherConfig(MultipartFile image, String otherConfig) throws IOException {

		TemplateService ts = new TemplateService();
		
		String fileSrc = "\\other-config\\";
		
		//Delete old file
		List<OtherConfig> listOtherConfig = otherConfigRepository.findAll();
		OtherConfig oldOtherConfig = listOtherConfig.get(0);
		String deleteOldImage = ts.deleteFile(fileSrc, oldOtherConfig.getTraining_program_img());
		
		//Prepare create new file
		OtherConfig newOtherConfig = new ObjectMapper().readValue(otherConfig, OtherConfig.class);
		String modifiedName;
		
		if(newOtherConfig.getTraining_program_img().indexOf(".") >= 0) {
			newOtherConfig.setTraining_program_img(ts.SplitTakeStartName(newOtherConfig.getTraining_program_img()));
		}
		
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		modifiedName = newOtherConfig.getTraining_program_img() + "." + extension;
		
		//Create new file
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "done"){
			newOtherConfig.setTraining_program_img(modifiedName);
			editOtherConfigFunction(newOtherConfig, oldOtherConfig);
		}
		
		return createSuccess;
	}
	
	void editOtherConfigFunction(OtherConfig newOtherConfig, OtherConfig oldOtherConfig) {
		oldOtherConfig.setTraining_program_img(newOtherConfig.getTraining_program_img());
		oldOtherConfig.setBusiness_training_text(newOtherConfig.getBusiness_training_text());
		
		otherConfigRepository.save(oldOtherConfig);
	}

}

//
package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.MainBanner;
import com.vti.academy.web.repository.MainBannerRepository;
import com.vti.academy.web.service.MainBannerService;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NVANH
 * @create_date: Sep 16, 2020
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Sep 16, 2020
 */
@Service
public class MainBannerServiceImpl implements MainBannerService{
	
	@Autowired
	private MainBannerRepository mainBannerRepository;
	
	@Override
	public List<MainBanner> getTop3Banner() {
		// TODO Auto-generated method stub
		return mainBannerRepository.getTop3Banner();
	}
	
	@Override
	public List<MainBanner> getAllBanner(){
		return mainBannerRepository.findAll();
	}
	
	@Override
	public Page<MainBanner> getMainBannerPaging(Pageable pageable) {
		// TODO Auto-generated method stub
		return mainBannerRepository.findAll(pageable);
	}


	@SuppressWarnings("unused")
	@Override
	public String editBannerContent(String banner, int id) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		String fileSrc = "\\main-Banner\\";
		TemplateService ts = new TemplateService();
		MainBanner newMainBanner = new ObjectMapper().readValue(banner, MainBanner.class);
		
		MainBanner oldMainBanner = mainBannerRepository.findOne(id);
	
		editBannerFunction(newMainBanner, oldMainBanner);
		
		return "done";
	}

	@SuppressWarnings("unused")
	@Override
	public String editBanner(MultipartFile image, String banner, int id) throws IOException {
		// TODO Auto-generated method stub
		TemplateService ts = new TemplateService();
		
		String fileSrc = "\\main-Banner\\";
		
		//Delete old file
		MainBanner oldMainBanner = mainBannerRepository.findOne(id);
		String deleteOldImage = ts.deleteFile(fileSrc, oldMainBanner.getImage());
		
		//Prepare create new file
		MainBanner newMainBanner = new ObjectMapper().readValue(banner, MainBanner.class);
		String modifiedName;
		if(newMainBanner.getImage().indexOf(".") >= 0) {
			newMainBanner.setImage(ts.SplitTakeStartName(newMainBanner.getImage()));
		}
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		modifiedName = newMainBanner.getImage()
				+"."+extension;
		
		//Create new file
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "done"){
			newMainBanner.setImage(modifiedName);
			editBannerFunction(newMainBanner, oldMainBanner);
		}
		
		return createSuccess; 
	}

	@Override
	public String addBanner(MultipartFile image, String banner) throws IOException {
		String response = "done";
		TemplateService ts = new TemplateService();
		
		MainBanner newMainBanner = new ObjectMapper().readValue(banner, MainBanner.class);
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		if(newMainBanner.getImage().indexOf(".") >= 0) {
			newMainBanner.setImage(ts.SplitTakeStartName(newMainBanner.getImage()));
		}
		String modifiedName = newMainBanner.getImage()
								+"."+extension;
		String fileSrc = "\\main-Banner\\";
		
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "file name exists") {
			response = createSuccess;
		}
		else {
			newMainBanner.setImage(modifiedName);
			mainBannerRepository.save(newMainBanner);
		}
		
		return response;
	}

	@Override
	public String deleteBanner(int id) {
		// TODO Auto-generated method stub
		TemplateService ts = new TemplateService();
		String fileSrc = "\\main-Banner\\";
		MainBanner deleteMainBanner = mainBannerRepository.findOne(id);
		System.out.println(deleteMainBanner.getImage());
		String response = ts.deleteFile(fileSrc, deleteMainBanner.getImage());
		
		if(response == "file deleted") {
			mainBannerRepository.delete(id);
		}
		return response;
	}
	
	void editBannerFunction(MainBanner newMainBanner, MainBanner oldMainBanner) {
		oldMainBanner.setUrl(newMainBanner.getUrl());
		oldMainBanner.setName(newMainBanner.getName());
		oldMainBanner.setImage(newMainBanner.getImage());
		
		mainBannerRepository.save(oldMainBanner);
	}
	
}

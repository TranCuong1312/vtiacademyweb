//
package com.vti.academy.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.model.MainBanner;

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
public interface MainBannerService {
	
	List<MainBanner> getTop3Banner();
	
	List<MainBanner> getAllBanner();
	
	String editBannerContent(String banner, int id) throws JsonParseException, JsonMappingException, IOException;
	
	String editBanner(MultipartFile image, String banner, int id) throws IOException;
	
	String addBanner(MultipartFile image, String banner) throws IOException;
	
	String deleteBanner(int id);
	
	Page<MainBanner> getMainBannerPaging(Pageable pageable);
	
}

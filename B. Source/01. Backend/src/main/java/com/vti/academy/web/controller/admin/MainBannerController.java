//
package com.vti.academy.web.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.MainBanner;
import com.vti.academy.web.response.CommonResponse;
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
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin/mainbanner")
public class MainBannerController {
	
	@Autowired
	private MainBannerService mainBannerService;
	
	@GetMapping("allbanner")
	public CommonResponse getAllBanner() {
		List<MainBanner> mainBannerList = mainBannerService.getAllBanner();
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",
				mainBannerList);
	}
	
	@GetMapping("paging")
	public CommonResponse getPagingBanner(Pageable pageable) {
		Page<MainBanner> mainBannerList = mainBannerService.getMainBannerPaging(pageable);
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get success", mainBannerList);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addMainBanner(@RequestParam("image") MultipartFile image,
			@RequestParam("banner") String mainBanner) throws IOException{ 
		String response = mainBannerService.addBanner(image, mainBanner);
		if(response == "file name exists")
			return new ResponseEntity<>(response,  HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(response,  HttpStatus.OK);
	}
	
	@PostMapping("/editcontent/{id}")
	public ResponseEntity<?> editContentMainBanner(@RequestParam("banner") String mainBanner, 
			@PathVariable("id") Integer id) throws JsonParseException, JsonMappingException, IOException{
		String response = mainBannerService.editBannerContent(mainBanner, id);
		if(response == "dir not exists")
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/edit/{id}")
	public ResponseEntity<?> editMainBanner(@RequestParam("image") MultipartFile image, 
			@RequestParam("banner") String mainBanner, 
			@PathVariable("id") Integer id) throws IOException{
		String response = mainBannerService.editBanner(image, mainBanner, id);
		if(response == "done")
			return new ResponseEntity<>(response, HttpStatus.OK);
		else
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/delete/{id}")
	public CommonResponse deleteMainBanner(@PathVariable("id") Integer id) {
		String response = mainBannerService.deleteBanner(id);
		if(response == "file delete fail") {
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.BAD_REQUEST.getCode(), response);
		}
		else
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), response);
	}
	
}

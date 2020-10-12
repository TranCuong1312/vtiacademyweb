package com.vti.academy.web.controller.admin;

import java.io.IOException;
import java.text.ParseException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;

import org.springframework.http.ResponseEntity;
import com.vti.academy.web.model.News;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.response.PaginationResponse;
import com.vti.academy.web.service.NewsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@GetMapping("/news")
	public CommonResponse getNews(Pageable pageable){
		PaginationResponse<News> data = new PaginationResponse<News>(newsService.getNews(pageable), "admin/news");
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success",data);
	}
	
//	@GetMapping("/news/author/{author}")
//	public CommonResponse findByAuthor(@PathVariable String author){
//		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",newsService.findByAuthor(author));
//	}
//	
//	@GetMapping("/news/createdate/{date}")
//	public CommonResponse findByCreateDate(@PathVariable String date){
//		Date d = Date.valueOf(date);
//		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",newsService.findByCreateDate(d));
//	}
//	
//	@GetMapping("/news/note/{note}")
//	public CommonResponse findByNote(@PathVariable String note){
//		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",newsService.findByNote(note));
//	}
//	
//	@GetMapping("/news/title/{title}")
//	public CommonResponse findByTitle(@PathVariable String title){
//		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",newsService.findByTitle(title));
//	}
//	
//	@GetMapping("/news/content/{content}")
//	public CommonResponse findByContent(@PathVariable String content){
//		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Sucess",newsService.findByContent(content));
//	}
//	
	@PostMapping("/news")
	public ResponseEntity<?> saveNews(@RequestParam("image") MultipartFile image, @RequestParam("new") String news) throws Exception{
		String response = newsService.saveNews(image, news);
		if(response == "file name exists")
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/news/edit/{id}")
	public ResponseEntity<?> editNews(@RequestParam("image") MultipartFile image,
			@RequestParam("new") String news, @PathVariable String id) throws Exception{
		String response = newsService.editNews(image, news, id);
		if(response == "fail")
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/news/edit/noimage/{id}")
	public ResponseEntity<?> editNewsNoImage(@RequestParam("new") String news, @PathVariable String id) throws IOException, ParseException{
		String response = newsService.editNewsNoImage(news, id);
		if(response == "fail")
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/news/delete/{id}")
	public CommonResponse deleteNews(@PathVariable Long id){
		String response = newsService.deleteNewsById(id);
		if(response == "file delete fail") {
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.BAD_REQUEST.getCode(), response);
		}
		else
			return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), response);
	}
}

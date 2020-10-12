package com.vti.academy.web.service;

import java.io.IOException;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.vti.academy.web.model.News;


public interface NewsService {
	
	List<News> getTop3News();
	
	News getNewsById(Long id);
	
	String deleteNewsById(Long id);

	String saveNews(MultipartFile image, String news) throws Exception;
	
	String editNews(MultipartFile image, String news, String id) throws Exception;

	String editNewsNoImage(String news, String id) throws IOException, ParseException;
	
	Page<News> getNews(Pageable pageable);

//	List<NewsDTO> findByAuthor(String author);
//
//	List<NewsDTO> findByContent(String content);
//
//	List<NewsDTO> findByCreateDate(Date createDate);
//
//	List<NewsDTO> findByNote(String note);
//
//	List<NewsDTO> findByTitle(String title);
}

package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.News;
import com.vti.academy.web.repository.NewsRepository;
import com.vti.academy.web.service.NewsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	
	@Override
	public List<News> getTop3News() {
		return newsRepository.getTop3News();
	}

//	@Override
//	public List<News> getNewsRandom() {
//		return newsRepository.getNewsRandom();
//	}

	@Override
	public News getNewsById(Long id) {
		return newsRepository.findById(id);
	}
	
	@Override
	public Page<News> getNews(Pageable pageable) {
		return newsRepository.findAll(pageable);
	}

	@Override
	public String saveNews(MultipartFile image, String news) throws Exception {
		String response = "done";
		TemplateService ts = new TemplateService();
		
		News newss = new ObjectMapper().readValue(news, News.class);
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		if(newss.getImage().indexOf(".") >= 0) {
			newss.setImage(ts.SplitTakeStartName(newss.getImage()));
		}
		String modifiedName = newss.getImage()
								+"."+extension;
		String fileSrc = "\\News-Event\\";
		
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "file name exists") {
			response = createSuccess;
		}
		else {
			newss.setImage(modifiedName);
			newsRepository.save(newss);
		}
		
		return response;
	}
	
	@SuppressWarnings("unused")
	@Override
	public String editNews(MultipartFile image, String news, String id) throws IOException, ParseException {
		// TODO Auto-generated method stub
		TemplateService ts = new TemplateService();
		
		String fileSrc = "\\News-Event\\";
		
		long idEdit = Long.parseLong(id);
		
		//Delete old file
		News news2 = newsRepository.findOne(idEdit);
		String deleteOldImage = ts.deleteFile(fileSrc, news2.getImage());
		
		//Prepare create new file
		News branchNews = new ObjectMapper().readValue(news, News.class);
		String modifiedName;
		if(branchNews.getImage().indexOf(".") >= 0) {
//			String[] newWords = branchNewsDTO.getImage().split("\\.");
			branchNews.setImage(ts.SplitTakeStartName(branchNews.getImage()));
		}
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		modifiedName =branchNews.getImage()
				+"."+extension;
		
		//Create new file
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		
		if(createSuccess == "done"){
			branchNews.setImage(modifiedName);
			editNewsFunction(branchNews, news2);
		}
		
		return createSuccess; 
	}
	
	@SuppressWarnings("unused")
	@Override
	public String editNewsNoImage(String news, String id) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String fileSrc = "\\News-Event\\";
		TemplateService ts = new TemplateService();
		News branchNews = new ObjectMapper().readValue(news, News.class);
		long idEdit = Long.parseLong(id);
		News news2 = newsRepository.findOne(idEdit);
	
	
			editNewsFunction(branchNews, news2);
		
		return "done";
	}
	
	@Override
	public String deleteNewsById(Long id) {
		TemplateService ts = new TemplateService();
		String fileSrc = "\\News-Event\\";
		News news = newsRepository.findOne(id);
		String response = ts.deleteFile(fileSrc, news.getImage());
		if(response == "file deleted") {
			newsRepository.delete(id);
		}
		return response;
	}

	void editNewsFunction(News branchNews, News news) {
		news.setTitle(branchNews.getTitle());
		news.setContent(branchNews.getContent());
		news.setAuthor(branchNews.getAuthor());
		news.setImage(branchNews.getImage());
		news.setCreateDate(branchNews.getCreateDate());
		news.setShortDes(branchNews.getShortDes());
		
		newsRepository.save(news);
}
	

}

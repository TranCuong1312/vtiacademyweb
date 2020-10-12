	package com.vti.academy.web.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
	
	@Query(value = "SELECT n.* "
				+ "FROM News n" 
				+ "ORDER BY n.id DESC "
				+ " LIMIT 3", nativeQuery = true)
	public List<News> getTop3News();
	
	News findById(Long id);
	
	@Query(value = "SELECT n.* "
			+ "FROM News n \r\n" 
			+ "ORDER BY RAND() "
			+ " LIMIT 0,3 \r\n", nativeQuery = true)
	public List<News> getNewsRandom();
	
	@Query(value = "SELECT n.* FROM News n WHERE n.author LIKE %:author%",nativeQuery = true)
	List<News> findByAuthor(@Param("author") String author);
	
	@Query(value = "SELECT n.* FROM News n WHERE n.content LIKE %:content%",nativeQuery = true)
	List<News> findByContent(@Param("content") String content);
	
	List<News> findByCreateDate(Date createDate);
	
	@Query(value = "SELECT n.* FROM News n WHERE n.note LIKE %:note%",nativeQuery = true)
	List<News> findByNote(@Param("note") String note);
	
	@Query(value = "SELECT n.* FROM News n WHERE n.title LIKE %:title%",nativeQuery = true)
	List<News> findByTitleLike(@Param("title") String title);
	
}

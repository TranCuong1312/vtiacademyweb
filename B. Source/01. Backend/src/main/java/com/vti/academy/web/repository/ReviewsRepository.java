package com.vti.academy.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.Reviews;
import com.vti.academy.web.model.Type;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
	public Reviews getById(Long id);

	@Query(value = "  SELECT *  FROM Reviews r WHERE review_type = :review_type", nativeQuery = true)
	public List<Reviews> getReviewByType(@Param("review_type") String type);
	
//	@Query(value = "  SELECT r.*  FROM Reviews r WHERE review_type = :review_type ORDER BY ?#{#pageable} ", nativeQuery = true)
//	public Page<Reviews> getReviewByTypeAndPaging(@Param("review_type") String type, Pageable pageable);
	
	public Page<Reviews> getReviewByType(Type type, Pageable pageable);

}

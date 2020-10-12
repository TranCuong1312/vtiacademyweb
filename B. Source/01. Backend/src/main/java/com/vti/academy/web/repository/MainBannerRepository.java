//
package com.vti.academy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Repository
public interface MainBannerRepository extends JpaRepository<MainBanner, Integer> {
	
	@Query(value = "SELECT n.* "
			+ "FROM mainbanner n \r\n" 
			+ "ORDER BY create_date DESC "
			+ " LIMIT 0,3 \r\n", nativeQuery = true)
	public List<MainBanner> getTop3Banner();
	
}

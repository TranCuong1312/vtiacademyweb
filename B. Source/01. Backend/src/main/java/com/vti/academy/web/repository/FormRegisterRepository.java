package com.vti.academy.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.FormRegister;
/**
 * This class is . 
 * 
 * @Description: .
 * @author: NVANH
 * @create_date: Aug 18, 2020
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Aug 18, 2020
 */
@Repository
public interface FormRegisterRepository extends JpaRepository<FormRegister, Integer> {
	
}

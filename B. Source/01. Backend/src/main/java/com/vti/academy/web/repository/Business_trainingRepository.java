package com.vti.academy.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.Business_training;

@Repository
public interface Business_trainingRepository extends JpaRepository <Business_training, Long>{

}

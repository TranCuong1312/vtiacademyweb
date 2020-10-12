package com.vti.academy.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>{

	
}

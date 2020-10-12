package com.vti.academy.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.User;
import com.vti.academy.web.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long>{
	Optional<UserDetail> findByUser(User user);
	}

package com.vti.academy.web.service;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.vti.academy.web.model.Enterprise;

@Service
public interface EnterpriseService {
	
	public Page<Enterprise> getAll(Pageable pageable);
	
	public List<Enterprise> getAllEnterprise();
	
	public Enterprise getById(Long id);
	
	public List<Enterprise> getTop3Enterprise();
	
	String createEnterprise(MultipartFile icon, MultipartFile image, String enterprise) throws IOException;
	
	String editEnterprise(MultipartFile icon ,MultipartFile image, String enterprise, String id) throws IOException;
	
	String editIconEnterprise(MultipartFile icon , String enterprise, String id) throws IOException;
	
	String editImageEnterprise(MultipartFile image, String enterprise, String id) throws IOException;
	
	String editContentEnterprise(String enterprise, String id) throws IOException;
	
	String deleteEnterprise(Long id);
	
	Enterprise getEnterpriseByID(Long id);
	
}
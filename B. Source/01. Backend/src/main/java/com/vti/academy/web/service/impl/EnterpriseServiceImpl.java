package com.vti.academy.web.service.impl;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.Enterprise;
import com.vti.academy.web.repository.EnterpriseRepository;
import com.vti.academy.web.service.EnterpriseService;

@Service
public class EnterpriseServiceImpl implements EnterpriseService  {
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Override
	public Page<Enterprise> getAll(Pageable pageable) {
		return enterpriseRepository.findAll(pageable);
	}
	@Override
	public List<Enterprise> getAllEnterprise() {
		return enterpriseRepository.findAll();
	}
	@Override
	public Enterprise getById(Long id) {
		return enterpriseRepository.getById(id);
	}
	
	@Override
	public List<Enterprise> getTop3Enterprise() {
		// TODO Auto-generated method stub
		return enterpriseRepository.getTop3Enterprise();
	}
	
	@Override
	public String createEnterprise(MultipartFile icon, MultipartFile image, String enterprises) throws IOException {
		String response = "done";
		TemplateService ts = new TemplateService();
		
		Enterprise newEnterprise = new ObjectMapper().readValue(enterprises, Enterprise.class);
		String fileImageSrc = "\\Enterprise\\Images\\";
		String fileIconSrc = "\\Enterprise\\Icons\\";
		//Create image
		if(newEnterprise.getImage().indexOf(".") >= 0) {
			newEnterprise.setImage(ts.SplitTakeStartName(newEnterprise.getImage()));
		}
		String imageExtension = FilenameUtils.getExtension(image.getOriginalFilename());
		String modifiedImageName = newEnterprise.getImage()
								+"."+imageExtension;
		String createImageSuccess = ts.CreateFileImage(fileImageSrc, modifiedImageName, image);
		System.out.println(createImageSuccess+ " image\n");
		
		//Create icon
		String iconExtension = FilenameUtils.getExtension(icon.getOriginalFilename());
		String modifiedIconName = newEnterprise.getIcon()
								+"."+iconExtension;
		
		String createIconSuccess = ts.CreateFileImage(fileIconSrc, modifiedIconName, icon);
		System.out.println(createIconSuccess + " icon");
		
		if(createImageSuccess == "file name exists" || createIconSuccess == "file name exists" ) {
			response = "file name exists";
		}
		else {
			newEnterprise.setImage(modifiedImageName);
			newEnterprise.setIcon(modifiedIconName);
			enterpriseRepository.save(newEnterprise);
		}
		
		return response;
	}
	@SuppressWarnings("unused")
	@Override
	public String editEnterprise(MultipartFile icon ,
			MultipartFile image, 
			String enterprises, String id) throws IOException {
		String response = "done";
		TemplateService ts = new TemplateService();
		
		String fileImageSrc = "\\Enterprise\\Images\\";
		String fileIconSrc = "\\Enterprise\\Icons\\";
		
		long idEdit = Long.parseLong(id);
		
		//Delete old file
		Enterprise oldEnterprise = enterpriseRepository.findOne(idEdit);
		String deleteOldImage = ts.deleteFile(fileImageSrc, oldEnterprise.getImage());
		String deleteOldIcon = ts.deleteFile(fileIconSrc, oldEnterprise.getIcon());
		
		//Prepare create new file
		Enterprise newEnterprise = new ObjectMapper().readValue(enterprises, Enterprise.class);
		
		//Prepare image
		String modifiedImageName;
		if(newEnterprise.getImage().indexOf(".") >= 0) {
			newEnterprise.setImage(ts.SplitTakeStartName(newEnterprise.getImage()));
		}
		String imageExtension = FilenameUtils.getExtension(image.getOriginalFilename());
		modifiedImageName =newEnterprise.getImage()
				+"."+imageExtension;
		//Prepare icon
		String modifiedIconName;
		if(newEnterprise.getIcon().indexOf(".") >= 0) {
			newEnterprise.setIcon(ts.SplitTakeStartName(newEnterprise.getIcon()));
		}
		String iconExtension = FilenameUtils.getExtension(icon.getOriginalFilename());
		modifiedIconName =newEnterprise.getIcon()
				+"."+iconExtension;
		
		//Create new image File
		String createImageSuccess = ts.CreateFileImage(fileImageSrc, modifiedImageName, image);
		//Create new icon File
		String createIconSuccess = ts.CreateFileImage(fileIconSrc, modifiedIconName, icon);
		
		if(createImageSuccess == "done" && createIconSuccess == "done"){
			newEnterprise.setImage(modifiedImageName);
			newEnterprise.setIcon(modifiedIconName);
			editEnterpriseFunction(newEnterprise, oldEnterprise);
		}
		else {
			response = "file name exists";
		}
		
		return response; 
	}
	/* 
	* @see com.vti.academy.web.service.EnterpriseService#editIconEnterprise(org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String)
	*/
	@SuppressWarnings("unused")
	@Override
	public String editIconEnterprise(MultipartFile icon, 
			String enterprises, 
			String id) throws IOException {
		
		TemplateService ts = new TemplateService();
		String fileImageSrc = "\\Enterprise\\Images\\";
		String fileIconSrc = "\\Enterprise\\Icons\\";
		Long enterpriseId = Long.parseLong(id);
		Enterprise newEnterprise = new ObjectMapper().readValue(enterprises, Enterprise.class);
		
		//Delete old icon
		Enterprise oldEnterprise = enterpriseRepository.findOne(enterpriseId);
		String deleteOldIcon = ts.deleteFile(fileIconSrc, oldEnterprise.getIcon());
		
		//Change image name
	
		
		//Prepare create new icon
		if(newEnterprise.getIcon().indexOf(".") >= 0) {
			newEnterprise.setIcon(ts.SplitTakeStartName(newEnterprise.getIcon()));
		}
		String iconExtension = FilenameUtils.getExtension(icon.getOriginalFilename());
		String modifiedIconName =newEnterprise.getIcon()
				+"."+iconExtension;
		
		
		String createSuccessIcon = ts.CreateFileImage(fileIconSrc, modifiedIconName, icon);
		
		if(createSuccessIcon == "done" ) {
			
			newEnterprise.setIcon(modifiedIconName);
			editEnterpriseFunction(newEnterprise, oldEnterprise);
		}
		return createSuccessIcon;
	}
	/* 
	* @see com.vti.academy.web.service.EnterpriseService#editImageEnterprise(org.springframework.web.multipart.MultipartFile, java.lang.String, java.lang.String)
	*/
	@SuppressWarnings("unused")
	@Override
	public String editImageEnterprise(MultipartFile image, String enterprises, String id) throws IOException {
		TemplateService ts = new TemplateService();
		Long enterpriseId = Long.parseLong(id);
		String fileImageSrc = "\\Enterprise\\Images\\";
		String fileIconSrc = "\\Enterprise\\Icons\\";
		Enterprise newEnterprise = new ObjectMapper().readValue(enterprises, Enterprise.class);
		
		//Delete old image
		Enterprise oldEnterprise = enterpriseRepository.findOne(enterpriseId);
		String deleteOldImage = ts.deleteFile(fileImageSrc, oldEnterprise.getImage());
		
		//Change icon name
		
		
		//Prepare create new image
		if(newEnterprise.getImage().indexOf(".") >= 0) {
			newEnterprise.setImage(ts.SplitTakeStartName(newEnterprise.getIcon()));
		}
		String extensionImage = FilenameUtils.getExtension(image.getOriginalFilename());
		String modifiedNameImage = newEnterprise.getImage()
								+"."+extensionImage;
		
		String createSuccessImage = ts.CreateFileImage(fileImageSrc, modifiedNameImage, image);
		if(createSuccessImage == "done" ) {
			
			newEnterprise.setImage(modifiedNameImage);
			editEnterpriseFunction(newEnterprise, oldEnterprise);
		}
		
		return createSuccessImage;
	}
	/* 
	* @see com.vti.academy.web.service.EnterpriseService#editContentEnterprise(java.lang.String, java.lang.String)
	*/
	@SuppressWarnings("unused")
	@Override
	public String editContentEnterprise(String enterprises, String id) throws IOException {
		String response = "done";
		String fileImageSrc = "\\Enterprise\\Images\\";
		String fileIconSrc = "\\Enterprise\\Icons\\";
		TemplateService ts = new TemplateService();
		Enterprise newEnterprise = new ObjectMapper().readValue(enterprises, Enterprise.class);
		
		long idEdit = Long.parseLong(id);
		Enterprise oldEnterprise = enterpriseRepository.findOne(idEdit);
		
		
		
		
		editEnterpriseFunction(newEnterprise, oldEnterprise);
	
		return response;
	}
	
	void editEnterpriseFunction(Enterprise newEnterprise, Enterprise oldEnterprise) {
		
		oldEnterprise.setImage(newEnterprise.getImage());
		oldEnterprise.setContent(newEnterprise.getContent());
		oldEnterprise.setIcon(newEnterprise.getIcon());
		oldEnterprise.setName(newEnterprise.getName());
		
		enterpriseRepository.save(oldEnterprise);
	}
	
	@Override
	public String deleteEnterprise(Long id) {
		String response = "done";
		TemplateService ts = new TemplateService();
		String fileImageSrc = "\\Enterprise\\Images\\";
		String fileIconSrc = "\\Enterprise\\Icons\\";
		Enterprise deleteEnterprise = enterpriseRepository.findOne(id);
		String responseImage = ts.deleteFile(fileImageSrc, deleteEnterprise.getImage());
		String responseIcon = ts.deleteFile(fileIconSrc, deleteEnterprise.getIcon());
		if(responseImage == "file deleted" && responseIcon == "file deleted") {
			enterpriseRepository.delete(deleteEnterprise);
		}else {
			response = "fail";
		}
		return response;
	}
	
	@Override
	public Enterprise getEnterpriseByID(Long id) {
		return enterpriseRepository.findOne(id);
	}
}
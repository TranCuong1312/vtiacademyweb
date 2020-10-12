package com.vti.academy.web.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import com.vti.academy.web.model.ConfigSrcFolder;

public class TemplateService {
	
	ConfigSrcFolder csf = new ConfigSrcFolder();
	String fileFolder = csf.getPathfolder();
	
	String SplitTakeStartName(String splitName) {
		String[] splitWords = splitName.split("\\."); 
		return splitWords[0];
	}
	
	String SplitTakeEndName(String splitName) {
		String[] splitWords = splitName.split("\\.");
		return splitWords[1];
	}
	
	Boolean CheckExsit(String pathFolder, String modifiedName) {
		File fileTemp = new File(pathFolder);
		boolean check = false;
		boolean checkPng = new File(fileTemp, modifiedName + ".png").exists();
		boolean checkPNG = new File(fileTemp, modifiedName + ".PNG").exists();
		boolean checkJPG = new File(fileTemp, modifiedName + ".jpg").exists();
		if(checkPng || checkPNG || checkJPG) {
			check = true;
		}
		return check;
	}
	
	String CreateFileImage(String fileSrc, String modifiedName, MultipartFile image) throws IOException{
		String created = "done";
		
		String pathFolder = fileFolder + fileSrc;

		byte[] bytes = image.getBytes();
		
		String startModi = SplitTakeStartName(modifiedName);
		
		if(CheckExsit(pathFolder, startModi)) {
			created = "file name exists";
		}else {
			Path path = Paths.get(pathFolder + modifiedName);
			Files.write(path, bytes);
		}
		return created;		
	}
	
	String deleteFile(String fileSrc, String deleteName) {
		String response ="done";
		String pathFolder = fileFolder + fileSrc;
		File deleteFile = new File(pathFolder + deleteName);
		System.out.println(pathFolder + deleteName);
		System.out.println(pathFolder + deleteName);
		if(deleteFile.delete()) {
			response = "file deleted";
		}
		else {
			response = "file delete fail";
		}
		return response;
	}
	
	
}

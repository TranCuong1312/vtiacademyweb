
  package com.vti.academy.web.service;
  
  import java.io.IOException;
import java.security.GeneralSecurityException;

import com.vti.academy.web.model.dto.FormRegisterDTO;
  
  public interface FormRegisterService { 
	  
	  public void addFormRegister(FormRegisterDTO scholarshipRegisterDTO, int type) throws IOException, GeneralSecurityException; 
	  
  }
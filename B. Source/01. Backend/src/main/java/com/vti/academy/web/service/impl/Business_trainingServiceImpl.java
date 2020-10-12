package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.Business_training;
import com.vti.academy.web.repository.Business_trainingRepository;
import com.vti.academy.web.service.Business_trainingService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Business_trainingServiceImpl implements Business_trainingService {
	
	@Autowired
	private Business_trainingRepository business_trainingRe;
	
	@Override
	public List<Business_training> getAll() {
		// TODO Auto-generated method stub
		return business_trainingRe.findAll();
	}

	@Override
	public String editBusiness_training(String id, String business_training) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Business_training oldBT = business_trainingRe.findOne(Long.parseLong(id));
		Business_training newBT = new ObjectMapper().readValue(business_training, Business_training.class);
		oldBT.setName(newBT.getName());
		business_trainingRe.save(oldBT);
		return "done";
	}

}

package com.vti.academy.web.model.dto;

import java.io.Serializable;
import java.util.List;

public class CommonDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String base64Image;
	private List<Long> taskIds;
	
	/**
	 * @return the taskIds
	 */
	public List<Long> getTaskIds() {
		return taskIds;
	}

	/**
	 * @param taskIds the taskIds to set
	 */
	public void setTaskIds(List<Long> taskIds) {
		this.taskIds = taskIds;
	}

	/**
	 * @return the base64
	 */
	public String getBase64Image() {
		return base64Image;
	}

	/**
	 * @param base64 the base64 to set
	 */
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
}

package com.vti.academy.web.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDTO {
	@JsonProperty("username")
	private String username;
	
	@NotBlank
	@JsonProperty("refreshToken")
	private String refreshToken;

	
}

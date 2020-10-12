package com.vti.academy.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AboutUsDTO {
	private Long id;
	private String description;
	private String video;
	private String titleVideo;
	private String descriptionVideo;
}

package com.vti.academy.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SubCourseInCourseRequest {
	private Long courseId;
	private Long subcourseId;
}

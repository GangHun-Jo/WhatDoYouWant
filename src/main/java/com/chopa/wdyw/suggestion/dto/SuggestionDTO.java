package com.chopa.wdyw.suggestion.dto;

import java.util.List;

import com.chopa.wdyw.category.dto.CategoryDTO;

import lombok.Data;

public class SuggestionDTO {
	@Data
	public static class Response {
		private Long id;
		private List<CategoryDTO> categoryList;
		private String title;
		private String content;
	}

	@Data
	public static class Request {
		private Long id;
		private List<CategoryDTO> categoryList;
		private String title;
		private String content;
	}
}

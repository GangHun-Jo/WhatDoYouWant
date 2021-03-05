package com.chopa.wdyw.suggestion.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
		@NotEmpty
		private List<@Valid CategoryDTO> categoryList;
		@NotEmpty
		private String title;
		@NotEmpty
		private String content;
	}

	@Data
	public static class CategoryDTO {
		@Min(1)
		@NotNull
		private Long id;
		private String name;
	}
}

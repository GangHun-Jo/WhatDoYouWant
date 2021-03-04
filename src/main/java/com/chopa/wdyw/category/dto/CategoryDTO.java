package com.chopa.wdyw.category.dto;


import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CategoryDTO {
	private Long id;
	@NotEmpty
	private String name;
}

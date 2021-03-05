package com.chopa.wdyw.category.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chopa.wdyw.category.dto.CategoryDTO;
import com.chopa.wdyw.category.model.Category;
import com.chopa.wdyw.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;
	private final ModelMapper modelMapper;

	@GetMapping("/categories")
	public List<CategoryDTO> findAll() {
		return categoryService.findAll().stream()
			.map((category -> modelMapper.map(category, CategoryDTO.class)))
			.collect(Collectors.toList());
	}


	@PostMapping("/categories")
	public CategoryDTO create(@RequestBody @Valid CategoryDTO dto) {
		Category newCategory = modelMapper.map(dto, Category.class);
		return modelMapper.map(categoryService.create(newCategory), CategoryDTO.class);
	}

	@PutMapping("/categories/{id}")
	public CategoryDTO update(@PathVariable Long id, @RequestBody @Valid CategoryDTO dto) {
		if (!dto.getId().equals(id)) {
			throw new IllegalArgumentException("id가 맞지 않습니다.");
		}

		Category newCategory = modelMapper.map(dto, Category.class);
		return modelMapper.map(categoryService.update(newCategory), CategoryDTO.class);
	}

	@DeleteMapping("/categories/{id}")
	public void delete(@PathVariable Long id) {
		categoryService.deleteById(id);
	}
}

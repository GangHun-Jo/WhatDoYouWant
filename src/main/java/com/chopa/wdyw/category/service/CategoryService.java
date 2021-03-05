package com.chopa.wdyw.category.service;

import java.util.List;

import com.chopa.wdyw.category.model.Category;

public interface CategoryService {
	List<Category> findAll();

	Category findById(Long id);

	Category create(Category category);

	Category update(Category category);

	void deleteById(Long id);
}

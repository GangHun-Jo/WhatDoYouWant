package com.chopa.wdyw.category.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chopa.wdyw.category.model.Category;
import com.chopa.wdyw.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Category newCategory) {
		Category category = categoryRepository.findById(newCategory.getId()).orElseThrow(EntityNotFoundException::new);
		category.update(newCategory);
		return category;
	}

	@Override
	public void deleteById(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		category.getSuggestionList().forEach(suggestion -> suggestion.getCategoryList().remove(category));
		category.setSuggestionList(new ArrayList<>());

		categoryRepository.deleteById(id);
	}
}

package com.chopa.wdyw.category.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.chopa.wdyw.suggestion.model.Suggestion;

import lombok.Data;

@Entity
@Data
public class Category {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany(mappedBy = "categoryList")
	private List<Suggestion> suggestionList = new ArrayList<>();
	private String name;

	public void update(Category newCategory) {
		this.name = newCategory.getName();
	}
}

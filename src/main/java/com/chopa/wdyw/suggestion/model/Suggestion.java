package com.chopa.wdyw.suggestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.chopa.wdyw.category.model.Category;

import lombok.Data;

@Entity
@Data
public class Suggestion {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany
	@JoinTable(
		name = "SuggestionCategory",
		joinColumns = {@JoinColumn(name = "suggestion_id", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
	)
	private List<Category> categoryList = new ArrayList<>();

	private String title;

	private String content;
}

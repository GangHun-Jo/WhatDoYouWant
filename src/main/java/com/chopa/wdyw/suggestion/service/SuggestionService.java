package com.chopa.wdyw.suggestion.service;

import java.util.List;

import com.chopa.wdyw.suggestion.model.Suggestion;

public interface SuggestionService {
	List<Suggestion> findAll();

	Suggestion findById(Long id);

	Suggestion create(Suggestion suggestion);

	Suggestion update(Suggestion newSuggestion);

	void deleteById(Long id);

	void likeSuggestion(Long id);
}

package com.chopa.wdyw.suggestion.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chopa.wdyw.suggestion.model.Suggestion;
import com.chopa.wdyw.suggestion.repository.SuggestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

	private final SuggestionRepository suggestionRepository;

	@Override
	public List<Suggestion> findAll() {
		return suggestionRepository.findAll();
	}

	@Override
	public Suggestion findById(Long id) {
		return suggestionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Suggestion create(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	@Override
	public Suggestion update(Suggestion newSuggestion) {
		Suggestion suggestion = suggestionRepository.findById(newSuggestion.getId()).orElseThrow(EntityNotFoundException::new);
		suggestion.update(newSuggestion);
		return suggestion;
	}

	@Override
	public void deleteById(Long id) {
		suggestionRepository.deleteById(id);
	}
}

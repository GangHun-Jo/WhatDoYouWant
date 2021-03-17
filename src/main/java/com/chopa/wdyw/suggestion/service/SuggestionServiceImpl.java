package com.chopa.wdyw.suggestion.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chopa.wdyw.suggestion.model.Suggestion;
import com.chopa.wdyw.suggestion.repository.SuggestionRankRepositoryImpl;
import com.chopa.wdyw.suggestion.repository.SuggestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

	private final SuggestionRepository suggestionRepository;
	private final SuggestionRankRepositoryImpl rankRepository;

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
		Suggestion newSuggestion = suggestionRepository.save(suggestion);
		rankRepository.add(suggestion.getId());
		return newSuggestion;
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

	@Override
	public void likeSuggestion(Long id) {
		if (!suggestionRepository.existsById(id)) {
			throw new IllegalArgumentException("id에 해당하는 suggestion이 존재하지 않습니다");
		}
		rankRepository.increment(id);
	}
}

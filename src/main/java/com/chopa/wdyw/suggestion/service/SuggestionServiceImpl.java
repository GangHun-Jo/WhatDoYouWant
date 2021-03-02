package com.chopa.wdyw.suggestion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chopa.wdyw.suggestion.repository.SuggestionRepository;
import com.chopa.wdyw.suggestion.model.Suggestion;

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
}

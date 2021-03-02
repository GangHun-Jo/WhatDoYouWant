package com.chopa.wdyw.suggestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chopa.wdyw.suggestion.service.SuggestionService;
import com.chopa.wdyw.suggestion.model.Suggestion;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SuggestionController {
	private final SuggestionService suggestionService;

	@GetMapping("/suggestions")
	public List<Suggestion> findAll() {
		return suggestionService.findAll();
	}
}

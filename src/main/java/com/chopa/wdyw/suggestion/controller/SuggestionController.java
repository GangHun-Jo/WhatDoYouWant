package com.chopa.wdyw.suggestion.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chopa.wdyw.suggestion.dto.SuggestionDTO;
import com.chopa.wdyw.suggestion.model.Suggestion;
import com.chopa.wdyw.suggestion.service.SuggestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SuggestionController {
	private final SuggestionService suggestionService;
	private final ModelMapper modelMapper;

	@GetMapping("/suggestions")
	public List<SuggestionDTO.Response> findAll() {
		return suggestionService.findAll().stream()
			.map((suggestion -> modelMapper.map(suggestion, SuggestionDTO.Response.class)))
			.collect(Collectors.toList());
	}

	@GetMapping("/suggestions/{id}")
	public SuggestionDTO.Response findById(@PathVariable Long id) {
		return modelMapper.map(suggestionService.findById(id), SuggestionDTO.Response.class);
	}

	@PostMapping("/suggestions")
	public SuggestionDTO.Response create(@RequestBody @Valid SuggestionDTO.Request dto) {
		Suggestion newSuggestion = modelMapper.map(dto, Suggestion.class);
		return modelMapper.map(suggestionService.create(newSuggestion), SuggestionDTO.Response.class);
	}

	@PutMapping("/suggestions/{id}")
	public SuggestionDTO.Response update(@PathVariable Long id, @RequestBody @Valid SuggestionDTO.Request dto) {
		if (!dto.getId().equals(id)) {
			throw new IllegalArgumentException("id가 맞지 않습니다.");
		}

		Suggestion newSuggestion = modelMapper.map(dto, Suggestion.class);
		return modelMapper.map(suggestionService.update(newSuggestion), SuggestionDTO.Response.class);
	}

	@DeleteMapping("/suggestions/{id}")
	public void delete(@PathVariable Long id) {
		suggestionService.deleteById(id);
	}

	@PostMapping("/suggestions/{id}/like")
	public ResponseEntity<?> likeSuggestion(@PathVariable Long id) {
		suggestionService.likeSuggestion(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/suggestions/rank")
	public List<SuggestionDTO.Response> findSuggestionRank(@RequestParam Long count) {
		return suggestionService.findMostLikedList(count).stream()
			.map((suggestion -> modelMapper.map(suggestion, SuggestionDTO.Response.class)))
			.collect(Collectors.toList());
	}
}

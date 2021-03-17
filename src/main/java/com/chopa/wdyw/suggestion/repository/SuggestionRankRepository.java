package com.chopa.wdyw.suggestion.repository;

public interface SuggestionRankRepository {
	void add(Long suggestionId);

	void increment(Long suggestionId);
}

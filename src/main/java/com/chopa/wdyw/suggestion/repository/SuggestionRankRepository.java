package com.chopa.wdyw.suggestion.repository;

import java.util.Set;

public interface SuggestionRankRepository {
	void add(Long suggestionId);

	void increment(Long suggestionId);

	Set<Long> getMostLikedIdSet(long count);
}

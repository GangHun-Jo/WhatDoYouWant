package com.chopa.wdyw.suggestion.repository;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SuggestionRankRepositoryImpl implements SuggestionRankRepository {
	public static final String KEY = "suggestionRank";

	private final RedisTemplate<String, Object> redisTemplate;
	private ZSetOperations<String, Object> zSetOps;

	@PostConstruct
	public void init() {
		zSetOps = redisTemplate.opsForZSet();
	}

	private String getValue(Long suggestionId) {
		return "suggestion" + suggestionId.toString();
	}

	@Override
	public void add(Long suggestionId) {
		zSetOps.add(KEY, getValue(suggestionId), 0);
	}

	@Override
	public void increment(Long suggestionId) {
		zSetOps.incrementScore(KEY, getValue(suggestionId), 1d);
	}
}

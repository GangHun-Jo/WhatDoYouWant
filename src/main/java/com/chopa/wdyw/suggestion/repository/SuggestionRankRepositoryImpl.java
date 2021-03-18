package com.chopa.wdyw.suggestion.repository;

import java.util.Set;
import java.util.stream.Collectors;

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

	private Long parseIdFromValue(String value) {
		return Long.parseLong(value.split("suggestion")[1]);
	}

	@Override
	public void add(Long suggestionId) {
		zSetOps.add(KEY, getValue(suggestionId), 0);
	}

	@Override
	public void increment(Long suggestionId) {
		zSetOps.incrementScore(KEY, getValue(suggestionId), 1d);
	}

	@Override
	public Set<Long> getMostLikedIdSet(long count) {
		// TODO : score 순서 맞지 않는 듯
		return zSetOps.reverseRange(KEY, 0, count - 1).stream()
			.map(value -> parseIdFromValue((String) value))
			.collect(Collectors.toSet());
	}
}

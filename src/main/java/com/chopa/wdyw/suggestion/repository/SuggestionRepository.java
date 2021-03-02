package com.chopa.wdyw.suggestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chopa.wdyw.suggestion.model.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}

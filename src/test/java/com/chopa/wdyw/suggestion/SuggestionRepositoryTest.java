package com.chopa.wdyw.suggestion;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.chopa.wdyw.category.repository.CategoryRepository;
import com.chopa.wdyw.category.model.Category;
import com.chopa.wdyw.suggestion.model.Suggestion;
import com.chopa.wdyw.suggestion.repository.SuggestionRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("local")
@TestPropertySource(properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class SuggestionRepositoryTest {

	@Autowired
	private SuggestionRepository suggestionRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private Suggestion newSuggestion;

	@BeforeEach
	public void init() {
		Category category = new Category();
		category.setName("카테고리");
		categoryRepository.save(category);

		newSuggestion = new Suggestion();
		newSuggestion.setCategoryList(Arrays.asList(category));
		newSuggestion.setContent("내용");
		newSuggestion.setTitle("타이틀");
		suggestionRepository.save(newSuggestion);
	}

	@Test
	void findAllTest() {
		System.out.println(suggestionRepository.findAll());
	}
}

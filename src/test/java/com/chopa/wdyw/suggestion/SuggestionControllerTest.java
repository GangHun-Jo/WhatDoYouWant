package com.chopa.wdyw.suggestion;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.chopa.wdyw.category.dto.CategoryDTO;
import com.chopa.wdyw.suggestion.dto.SuggestionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("local")
public class SuggestionControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private SuggestionDTO.Request newSuggestion;

	@BeforeEach
	public void init() {
		CategoryDTO category = new CategoryDTO();
		category.setName("카테고리");

		newSuggestion = new SuggestionDTO.Request();
		newSuggestion.setCategoryList(Arrays.asList(category));
		newSuggestion.setContent("내용");
		newSuggestion.setTitle("타이틀");
	}

	@Test
	public void testEmptyCategoryList() throws Exception {
		// when
		newSuggestion.setCategoryList(new ArrayList<>());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/suggestions")
			.content(objectMapper.writeValueAsString(newSuggestion))
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		// then
		mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testEmptyCategoryName() throws Exception {
		// when
		if (newSuggestion.getCategoryList().size() > 0) {
			newSuggestion.getCategoryList().get(0).setName("");
		}

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/suggestions")
			.content(objectMapper.writeValueAsString(newSuggestion))
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		// then
		mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest());
	}
}

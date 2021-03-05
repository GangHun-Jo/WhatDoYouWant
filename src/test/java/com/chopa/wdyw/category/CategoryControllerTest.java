package com.chopa.wdyw.category;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("local")
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private CategoryDTO newCategory;

	@BeforeEach
	public void init() {
		newCategory = new CategoryDTO();
		newCategory.setId(1L);
		newCategory.setName("카테고리");
	}

	@Test
	public void testEmptyName() throws Exception {
		newCategory.setName("");
		// when
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/categories")
			.content(objectMapper.writeValueAsString(newCategory))
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		// then
		mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest());
	}
}

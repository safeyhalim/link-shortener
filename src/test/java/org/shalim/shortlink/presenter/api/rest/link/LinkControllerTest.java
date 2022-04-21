package org.shalim.shortlink.presenter.api.rest.link;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.shalim.shortlink.core.domain.entities.Link;
import org.shalim.shortlink.core.domain.exceptions.InvalidUrlException;
import org.shalim.shortlink.core.domain.exceptions.UrlNotFoundException;
import org.shalim.shortlink.core.usecases.link.DecodeLink;
import org.shalim.shortlink.core.usecases.link.EncodeLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = LinkController.class)
public class LinkControllerTest {

	@Configuration
	@ComponentScan(basePackages = { "org.shalim.shortlink.presenter.api.rest.link",
			"org.shalim.shortlink.presenter.api.rest.common" })
	static class Config {
	}

	@MockBean
	private EncodeLink encodeLinkUseCase;

	@MockBean
	private DecodeLink decodeLinkUseCase;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldEncodeAUrl() throws Exception {
		// Given
		EncodeLink.Outputs expectedOutput = new EncodeLink.Outputs("123");
		doReturn(expectedOutput).when(encodeLinkUseCase).execute(any(EncodeLink.Inputs.class));

		// Then
		mockMvc.perform(post("/encode").contentType(MediaType.APPLICATION_JSON).content("Some url"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.shortLink", is("http://localhost/123")));
	}

	@Test
	public void encode_shouldReturnErrorIfNoUrlPassed() throws Exception {
		mockMvc.perform(post("/encode").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	public void encode_shouldReturnErrorIfInvalidUrlIsPassed() throws Exception {
		// Given
		String invalidUrlMsg = "Invalid Url";
		doThrow(new InvalidUrlException(invalidUrlMsg)).when(encodeLinkUseCase).execute(any(EncodeLink.Inputs.class));
		// Then
		mockMvc.perform(post("/encode").contentType(MediaType.APPLICATION_JSON).content("Some invalid url"))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidUrlException))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(false))).andExpect(jsonPath("$.message", is(invalidUrlMsg)));
	}

	@Test
	public void shouldDecodeAUrl() throws Exception {
		// Given
		String shortId = "123";
		String originalUrl = "Some url";
		DecodeLink.Outputs expectedOutput = new DecodeLink.Outputs(new Link(shortId, originalUrl));
		doReturn(expectedOutput).when(decodeLinkUseCase).execute(new DecodeLink.Inputs(shortId));

		// Then
		mockMvc.perform(get("/decode/" + shortId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.originalUrl", is(originalUrl)));
	}

	@Test
	public void decode_shouldReturnErrorIfNoIdPassed() throws Exception {
		mockMvc.perform(get("/decode").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void decode_shouldReturnErrorIfIdIsNotFound() throws Exception {
		// Given
		String urlNotFoundMsg = "Url Not found";
		String wrongId = "123";
		doThrow(new UrlNotFoundException(urlNotFoundMsg)).when(decodeLinkUseCase).execute(any(DecodeLink.Inputs.class));
		// Then
		mockMvc.perform(get("/decode/" + wrongId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof UrlNotFoundException))
				.andExpect(status().isNotFound()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(false))).andExpect(jsonPath("$.message", is(urlNotFoundMsg)));
	}

}

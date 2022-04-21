package org.shalim.shortlink;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.shalim.shortlink.presenter.api.rest.responses.BasicApiResponse;
import org.shalim.shortlink.presenter.api.rest.responses.DecodeLinkResponse;
import org.shalim.shortlink.presenter.api.rest.responses.EncodeLinkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class IntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldEncodeAUrl() throws MalformedURLException {
		// Given
		String originalUrl = "http://www.google.com";

		// When
		ResponseEntity<EncodeLinkResponse> response = testRestTemplate.postForEntity(createEndpoint("encode"),
				originalUrl, EncodeLinkResponse.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldEncodeAndDecodeAUrl() throws MalformedURLException {
		// Given
		String originalUrl = "http://www.google.com";

		// When
		ResponseEntity<EncodeLinkResponse> response = testRestTemplate.postForEntity(createEndpoint("encode"),
				originalUrl, EncodeLinkResponse.class);
		String shortLinkId = getIdFromShortUrl(response.getBody().getShortLink());
		// and
		ResponseEntity<DecodeLinkResponse> decodeResponse = testRestTemplate
				.getForEntity(createEndpoint("decode/" + shortLinkId), DecodeLinkResponse.class);

		// Then
		assertThat(decodeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(decodeResponse.getBody().getOriginalUrl()).isEqualTo(originalUrl);
	}

	@Test
	public void encode_shouldFailWhenUrlIsInvalid() throws MalformedURLException {
		// Given
		String invalidUrl = "Clearly this is not a url!";

		// When
		ResponseEntity<BasicApiResponse> response = testRestTemplate.postForEntity(createEndpoint("encode"), invalidUrl,
				BasicApiResponse.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void decode_shouldFailWhenIdIsInvalid() throws MalformedURLException {
		// Given
		String wrongId = "123";

		// When
		ResponseEntity<BasicApiResponse> response = testRestTemplate.getForEntity(createEndpoint("decode/" + wrongId),
				BasicApiResponse.class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	private String createEndpoint(String path) throws MalformedURLException {
		return new URL(String.format("http://localhost:%s/%s", port, path)).toString();
	}

	private String getIdFromShortUrl(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}
}

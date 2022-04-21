package org.shalim.shortlink.presenter.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.shalim.shortlink.core.domain.exceptions.InvalidUrlException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApacheUrlValidatorUnitTest {

	@ParameterizedTest
	@ValueSource(strings = { "http://www.example.com", "http://example.com", "https://www.example.com",
			"https://example.com", "http://example.com/first?a=5&b=6" })
	public void validation_shouldPassWithValidUrlSchemes(String url) {
		// Given
		ApacheUrlValidator validator = new ApacheUrlValidator();

		// Then
		assertDoesNotThrow(() -> {
			validator.validate(url);
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "Not a url", "ftp://ftp.not.supported", "htt://malformated",
			"https:/oneslash", "mailto://mailto.not.supported" })
	public void validation_shouldFailWithInvalidUrlSchemes(String url) {
		// Given
		ApacheUrlValidator validator = new ApacheUrlValidator();

		// Then
		assertThrows(InvalidUrlException.class, () -> {
			validator.validate(url);
		});
	}
}

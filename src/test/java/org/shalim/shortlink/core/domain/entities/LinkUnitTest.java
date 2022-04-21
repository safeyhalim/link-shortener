package org.shalim.shortlink.core.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shalim.shortlink.core.domain.common.IValidator;
import org.shalim.shortlink.core.domain.exceptions.InvalidUrlException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
public class LinkUnitTest {
	@Mock
	private IValidator urlValidator;
	
	@Test
	public void shouldSuccessfullyInstantiateLink() {
		// Given
		String url = "Some valid url";
		
		// When 
		Link link = Link.newInstance(url, urlValidator);
		
		// Then
		assertThat(link).isNotNull();
		assertThat(link.getId()).isNotNull();
		assertThat(link.getOrignalUrl()).isEqualTo(url);
	}
	
	@Test
	public void shouldFailToInstantiateWhenUrlIsInvalid() {
		// Given
		String url = "Some invalid url";
		
		// When
		doThrow(new InvalidUrlException("Invalid url")).when(urlValidator).validate(anyString());
		
		// Then 
		assertThatExceptionOfType(InvalidUrlException.class).isThrownBy(() -> {
			Link.newInstance(url, urlValidator);
		}).withMessageMatching("Invalid url");
	}
}

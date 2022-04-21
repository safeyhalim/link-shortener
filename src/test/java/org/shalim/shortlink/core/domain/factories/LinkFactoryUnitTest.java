package org.shalim.shortlink.core.domain.factories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shalim.shortlink.core.domain.common.IValidator;
import org.shalim.shortlink.core.domain.entities.Link;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LinkFactoryUnitTest {

	@Mock
	private IValidator validator;
	
	@Test
	public void shouldSuccessfullyCreateLinkObject() {
		// Given
		LinkFactory factory = new LinkFactory(validator);
		String url = "Some url";
		
		// When
		Link link = factory.createLink(url);
		
		// Then
		assertThat(link).isNotNull();
		assertThat(link.getOrignalUrl()).isEqualTo(url);
		assertThat(link.getId()).isNotNull();
	}
}

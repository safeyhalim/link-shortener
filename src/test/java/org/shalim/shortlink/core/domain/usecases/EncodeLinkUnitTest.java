package org.shalim.shortlink.core.domain.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shalim.shortlink.core.domain.entities.Link;
import org.shalim.shortlink.core.domain.factories.LinkFactory;
import org.shalim.shortlink.core.usecases.link.EncodeLink;
import org.shalim.shortlink.core.usecases.repositories.ILinkRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EncodeLinkUnitTest {
	
	@Mock
	ILinkRepository linkRepository;
	@Mock
	LinkFactory linkFactory;
	@InjectMocks
	EncodeLink encodeLinkUseCase;
	
	@Test
	public void shouldEncodeValidUrl() {
		// Given
		String url = "Some valid url";
		Link expectedLink = new Link("123", url);
		doReturn(expectedLink).when(linkFactory).createLink(anyString());
		doReturn(expectedLink).when(linkRepository).persist(any(Link.class));
		
		// When
		EncodeLink.Outputs output = encodeLinkUseCase.execute(new EncodeLink.Inputs(url));
		
		// Then
		assertThat(output.getShortUrlId()).isEqualTo(expectedLink.getId());
	}
}

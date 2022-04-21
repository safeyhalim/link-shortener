package org.shalim.shortlink.core.domain.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shalim.shortlink.core.domain.entities.Link;
import org.shalim.shortlink.core.domain.exceptions.UrlNotFoundException;
import org.shalim.shortlink.core.usecases.link.DecodeLink;
import org.shalim.shortlink.core.usecases.repositories.ILinkRepository;
import static org.mockito.Mockito.doReturn;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class DecodeLinkUnitTest {
	
	@Mock
	private ILinkRepository linkRepository;
	@InjectMocks
	private DecodeLink decodeLinkUseCase;
	
	@Test
	public void shouldReturnDecodeUrl() {
		// Given
		String shortLinkId = "123";
		Link expectedLink = new Link(shortLinkId, "originalUrl");
		doReturn(Optional.of(expectedLink)).when(linkRepository).getLinkById(anyString());
		
		// When
		Link outputLink = (decodeLinkUseCase.execute(new DecodeLink.Inputs(shortLinkId))).getLink();
		
		// Then
		assertThat(outputLink).isEqualTo(expectedLink);
	}
	
	@Test
	public void shouldThrowExceptionIfUrlNotFound() {
		// Given
		doReturn(Optional.ofNullable(null)).when(linkRepository).getLinkById(anyString());
		
		// When
		assertThrows(UrlNotFoundException.class, () -> {
			decodeLinkUseCase.execute(new DecodeLink.Inputs("123"));
		});
	}
}

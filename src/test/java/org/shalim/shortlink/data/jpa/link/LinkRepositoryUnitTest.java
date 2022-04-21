package org.shalim.shortlink.data.jpa.link;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shalim.shortlink.core.domain.entities.Link;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class LinkRepositoryUnitTest {
	
	@Mock
	private IJpaLinkRepository jpaLinkRepository;
	
	@InjectMocks
	private LinkRepository linkRepository;
	
	@Test
	public void shouldPersistLink() {
		// Given
		Link expectedLink = new Link("123", "Some url");
		doReturn(expectedLink).when(jpaLinkRepository).save(any(Link.class));
		
		// When
		Link outputLink = linkRepository.persist(expectedLink);
		
		// Then
		assertThat(outputLink).isEqualTo(expectedLink);
	}
	
	@Test
	public void shouldGetLinkById() {
		// Given
		String shortLinkId = "123";
		Link expectedLink = new Link(shortLinkId, "Some url");
		doReturn(Optional.of(expectedLink)).when(jpaLinkRepository).findById(anyString());
		
		// When
		Link outputLink = linkRepository.getLinkById(shortLinkId).get();
		
		// Then
		assertThat(outputLink).isEqualTo(expectedLink);
	}
	
}
package org.shalim.shortlink.data.jpa.link;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.shalim.shortlink.core.domain.entities.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class JpaLinkRespositoryTest {

	@Configuration
	@AutoConfigurationPackage
	@EntityScan("org.shalim.shortlink.core.domain.entities")
	static class DBConfig {
	}

	@Autowired
	private IJpaLinkRepository jpaLinkRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void shouldGetLinkById() {
		// Given
		String shortLinkId = "123";
		Link expectedLink = new Link(shortLinkId, "url1");
		Arrays.stream(new Link[] { expectedLink, new Link("456", "url2"), new Link("789", "url3") })
				.forEach(l -> testEntityManager.persistAndFlush(l));
		
		// When
		Link output = jpaLinkRepository.findById(shortLinkId).get();
		
		// Then
		assertThat(output).isEqualTo(expectedLink);
	}
}

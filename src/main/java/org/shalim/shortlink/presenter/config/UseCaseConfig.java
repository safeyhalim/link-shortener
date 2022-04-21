package org.shalim.shortlink.presenter.config;

import org.shalim.shortlink.core.domain.factories.LinkFactory;
import org.shalim.shortlink.core.usecases.link.DecodeLink;
import org.shalim.shortlink.core.usecases.link.EncodeLink;
import org.shalim.shortlink.core.usecases.repositories.ILinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
	
	@Bean
	public EncodeLink getEncodeLinkUseCase(LinkFactory linkFactory, ILinkRepository linkRepository) {
		return new EncodeLink(linkFactory, linkRepository);
	}
	
	@Bean
	public DecodeLink getDecodeLinkUseCase(ILinkRepository linkRepository) {
		return new DecodeLink(linkRepository);
	}
}

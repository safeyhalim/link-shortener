package org.shalim.shortlink.presenter.config;

import org.shalim.shortlink.core.domain.factories.LinkFactory;
import org.shalim.shortlink.presenter.validators.ApacheUrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {
	
	@Bean
	public LinkFactory getLinkFactory() {
		return new LinkFactory(new ApacheUrlValidator());
	}
}

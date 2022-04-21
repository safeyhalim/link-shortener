package org.shalim.shortlink.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"org.shalim.shortlink.core.domain.entities"})
@EnableJpaRepositories(basePackages = {"org.shalim.shortlink.data.jpa"})
public class DBConfig {
}

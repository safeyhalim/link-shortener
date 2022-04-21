package org.shalim.shortlink.data.jpa.link;

import org.shalim.shortlink.core.domain.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaLinkRepository extends JpaRepository<Link, String> {
}


package org.shalim.shortlink.core.usecases.repositories;

import java.util.Optional;

import org.shalim.shortlink.core.domain.entities.Link;

public interface ILinkRepository {
	public Link persist(Link link);
	public Optional<Link> getLinkById(String id);
}

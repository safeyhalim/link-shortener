package org.shalim.shortlink.data.jpa.link;

import java.util.Optional;
import org.shalim.shortlink.core.domain.entities.Link;
import org.shalim.shortlink.core.usecases.repositories.ILinkRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LinkRepository implements ILinkRepository {

	private IJpaLinkRepository jpaLinkRepository;
	
	public LinkRepository(IJpaLinkRepository jpaLinkRepository) {
		this.jpaLinkRepository = jpaLinkRepository;
	}
	
	@Override
	public Link persist(Link link) {
		return jpaLinkRepository.save(link);
	}

	@Override
	public Optional<Link> getLinkById(String id) {
		return jpaLinkRepository.findById(id);
	}

}

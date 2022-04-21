package org.shalim.shortlink.core.usecases.link;

import org.shalim.shortlink.core.domain.factories.LinkFactory;
import org.shalim.shortlink.core.usecases.IUseCase;
import org.shalim.shortlink.core.usecases.repositories.ILinkRepository;
import lombok.Value;

public class EncodeLink implements IUseCase<EncodeLink.Inputs, EncodeLink.Outputs>{

	private LinkFactory linkFactory;
	private ILinkRepository linkRepository;
	
	public EncodeLink(LinkFactory linkFactory, ILinkRepository linkRepository) {
		this.linkFactory = linkFactory;
		this.linkRepository = linkRepository;
	}
	
	@Override
	public Outputs execute(Inputs inputs) {
		return new Outputs(linkRepository.persist(linkFactory.createLink(inputs.url)).getId());
	}
	
	@Value
	public static class Inputs implements IUseCase.Inputs {
		String url;
	};
	
	@Value
	public static class Outputs implements IUseCase.Outputs {
		String shortUrlId;
	};
}

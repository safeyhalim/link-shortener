package org.shalim.shortlink.core.usecases.link;

import org.shalim.shortlink.core.domain.entities.Link;
import org.shalim.shortlink.core.domain.exceptions.UrlNotFoundException;
import org.shalim.shortlink.core.usecases.IUseCase;
import org.shalim.shortlink.core.usecases.repositories.ILinkRepository;
import lombok.Value;

public class DecodeLink implements IUseCase<DecodeLink.Inputs, DecodeLink.Outputs> {

	private ILinkRepository linkRepository;

	public DecodeLink(ILinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	@Override
	public Outputs execute(Inputs inputs) {
		return new Outputs(linkRepository.getLinkById(inputs.shortUrlId).orElseThrow(
				() -> new UrlNotFoundException(String.format("Could not find URL for Id: %s", inputs.shortUrlId))));
	}

	@Value
	public static class Inputs implements IUseCase.Inputs {
		String shortUrlId;
	};

	@Value
	public static class Outputs implements IUseCase.Outputs {
		Link link;
	};
}

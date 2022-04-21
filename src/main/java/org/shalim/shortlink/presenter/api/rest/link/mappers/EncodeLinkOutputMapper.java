package org.shalim.shortlink.presenter.api.rest.link.mappers;

import org.shalim.shortlink.core.usecases.link.EncodeLink;
import org.shalim.shortlink.presenter.api.rest.responses.EncodeLinkResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class EncodeLinkOutputMapper {
	
	EncodeLinkOutputMapper mapper;
	
	public static EncodeLinkResponse map(EncodeLink.Outputs outputs) {
		return new EncodeLinkResponse(String.format("%s/%s", getBaseUrl(), outputs.getShortUrlId()));
	}
	
	public static String getBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(null).build().toUriString();
	}
}

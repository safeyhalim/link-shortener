package org.shalim.shortlink.presenter.api.rest.link.mappers;

import org.shalim.shortlink.core.usecases.link.DecodeLink;
import org.shalim.shortlink.presenter.api.rest.responses.DecodeLinkResponse;

public class DecodeLinkOutputMapper {
	public static DecodeLinkResponse map(DecodeLink.Outputs output) {
		return new DecodeLinkResponse(output.getLink().getOrignalUrl());
	}
}

package org.shalim.shortlink.presenter.api.rest.link;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.shalim.shortlink.core.usecases.link.DecodeLink;
import org.shalim.shortlink.core.usecases.link.EncodeLink;
import org.shalim.shortlink.presenter.api.rest.link.mappers.DecodeLinkOutputMapper;
import org.shalim.shortlink.presenter.api.rest.link.mappers.EncodeLinkOutputMapper;
import org.shalim.shortlink.presenter.api.rest.responses.DecodeLinkResponse;
import org.shalim.shortlink.presenter.api.rest.responses.EncodeLinkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class LinkController implements ILinkController {

	@Autowired
	private EncodeLink encodeLinkUseCase;

	@Autowired
	private DecodeLink decodeLinkUseCase;

	@Override
	public ResponseEntity<EncodeLinkResponse> encode(HttpServletRequest httpServletRequest,
			@Valid @NotBlank @RequestBody String url) {
		return ResponseEntity.ok()
				.body(EncodeLinkOutputMapper.map(encodeLinkUseCase.execute(new EncodeLink.Inputs(url.trim()))));
	}

	@Override
	public ResponseEntity<DecodeLinkResponse> decode(
			@PathVariable @Valid @NotBlank(message = "id is mandatory") String id) {
		return ResponseEntity.ok()
				.body(DecodeLinkOutputMapper.map(decodeLinkUseCase.execute(new DecodeLink.Inputs(id.trim()))));
	}
}

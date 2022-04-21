package org.shalim.shortlink.presenter.api.rest.link;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.shalim.shortlink.presenter.api.rest.responses.DecodeLinkResponse;
import org.shalim.shortlink.presenter.api.rest.responses.EncodeLinkResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public interface ILinkController {

	@PostMapping("encode")
	public ResponseEntity<EncodeLinkResponse> encode(HttpServletRequest httpServletRequest,
			@Valid @NotBlank(message = "url is mandatory") @RequestBody String url);

	@GetMapping("decode/{id}")
	public ResponseEntity<DecodeLinkResponse> decode(@PathVariable @Valid @NotBlank(message = "id is mandatory") String id);
}

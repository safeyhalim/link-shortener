package org.shalim.shortlink.core.domain.factories;

import org.shalim.shortlink.core.domain.common.IValidator;
import org.shalim.shortlink.core.domain.entities.Link;

public class LinkFactory {
	private IValidator urlValidator;
	
	public LinkFactory(IValidator urlValidator) {
		this.urlValidator = urlValidator;
	}
	
	public Link createLink(String orignalUrl) {
		return Link.newInstance(orignalUrl, urlValidator);
	}
}

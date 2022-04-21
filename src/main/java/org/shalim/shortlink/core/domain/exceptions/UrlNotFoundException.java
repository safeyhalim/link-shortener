package org.shalim.shortlink.core.domain.exceptions;

public class UrlNotFoundException extends DomainException {
	
	private static final long serialVersionUID = 8038888720142817862L;

	public UrlNotFoundException(String message) {
		super(message);
	}

}

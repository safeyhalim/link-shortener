package org.shalim.shortlink.core.domain.exceptions;

public class InvalidUrlException extends DomainException {
	
	private static final long serialVersionUID = -1722783297069840249L;

	public InvalidUrlException(String message) {
		super(message);
	}

}

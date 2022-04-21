package org.shalim.shortlink.core.domain.exceptions;

public class DomainException extends RuntimeException {
	private static final long serialVersionUID = 2620630262569553110L;

	public DomainException(String message) {
        super(message);
    }
	
	public DomainException(String message, Exception e) {
        super(message);
        e.printStackTrace();
    }
}

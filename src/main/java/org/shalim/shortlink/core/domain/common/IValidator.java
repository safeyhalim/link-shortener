package org.shalim.shortlink.core.domain.common;

import org.shalim.shortlink.core.domain.exceptions.InvalidUrlException;

public interface IValidator {
	public void validate(String url) throws InvalidUrlException;
}

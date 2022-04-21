package org.shalim.shortlink.presenter.validators;

import org.apache.commons.validator.routines.UrlValidator;
import org.shalim.shortlink.core.domain.common.IValidator;
import org.shalim.shortlink.core.domain.exceptions.InvalidUrlException;

public class ApacheUrlValidator implements IValidator {

	private final UrlValidator validator = new UrlValidator(new String[]{"http", "https"});
	
	@Override
	public void validate(String url) throws InvalidUrlException {
		if (!validator.isValid(url)) {
			throw new InvalidUrlException(String.format("Invalid Url: %s", url));
		}
	}

}

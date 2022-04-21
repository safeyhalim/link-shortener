package org.shalim.shortlink.presenter.api.rest.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicApiResponse {
	Boolean success;
	String message;
}

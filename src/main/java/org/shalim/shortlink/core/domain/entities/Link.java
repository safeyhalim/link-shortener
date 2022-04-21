package org.shalim.shortlink.core.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.shalim.shortlink.core.domain.common.IValidator;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "link")
@Table(name = "link")
public class Link {
	@Id String id;
	String orignalUrl;
	
	public static Link newInstance(String originalUrl, IValidator urlValidator) {
		urlValidator.validate(originalUrl);
		return new Link(NanoIdUtils.randomNanoId(), originalUrl);
	}
}

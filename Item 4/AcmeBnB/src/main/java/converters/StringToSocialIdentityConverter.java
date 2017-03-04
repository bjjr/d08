
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;

@Component
@Transactional
public class StringToSocialIdentityConverter implements Converter<String, SocialIdentity> {

	@Autowired
	SocialIdentityRepository	socialIdentityRepository;


	@Override
	public SocialIdentity convert(String text) {
		SocialIdentity res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = socialIdentityRepository.findOne(id);
			}
		} catch (Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}
}

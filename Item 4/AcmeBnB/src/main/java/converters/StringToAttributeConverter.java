
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AttributeRepository;
import domain.Attribute;

@Component
@Transactional
public class StringToAttributeConverter implements Converter<String, Attribute> {

	@Autowired
	AttributeRepository	actorRepository;


	@Override
	public Attribute convert(String text) {
		Attribute res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = actorRepository.findOne(id);
			}
		} catch (Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}
}

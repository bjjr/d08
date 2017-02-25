
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Attribute;

@Component
@Transactional
public class AttributeToStringConverter implements Converter<Attribute, String> {

	@Override
	public String convert(Attribute attribute) {
		String res;

		if (attribute == null) {
			res = null;
		} else {
			res = String.valueOf(attribute.getId());
		}
		return res;
	}
}

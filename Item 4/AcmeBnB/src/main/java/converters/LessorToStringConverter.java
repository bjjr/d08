
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Lessor;

@Component
@Transactional
public class LessorToStringConverter implements Converter<Lessor, String> {

	@Override
	public String convert(Lessor lessor) {
		String res;

		if (lessor == null) {
			res = null;
		} else {
			res = String.valueOf(lessor.getId());
		}
		return res;
	}
}

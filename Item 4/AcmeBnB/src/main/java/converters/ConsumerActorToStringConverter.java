
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ConsumerActor;

@Component
@Transactional
public class ConsumerActorToStringConverter implements Converter<ConsumerActor, String> {

	@Override
	public String convert(ConsumerActor ca) {
		String res;

		if (ca == null) {
			res = null;
		} else {
			res = String.valueOf(ca.getId());
		}
		return res;
	}
}

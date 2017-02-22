
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConsumerActorRepository;
import domain.ConsumerActor;

@Component
@Transactional
public class StringToConsumerActorConverter implements Converter<String, ConsumerActor> {

	@Autowired
	ConsumerActorRepository	consumerActorRepository;


	@Override
	public ConsumerActor convert(String text) {
		ConsumerActor res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = consumerActorRepository.findOne(id);
			}
		} catch (Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}
}

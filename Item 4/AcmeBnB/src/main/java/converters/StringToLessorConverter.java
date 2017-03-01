
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.LessorRepository;
import domain.Lessor;

@Component
@Transactional
public class StringToLessorConverter implements Converter<String, Lessor> {

	@Autowired
	LessorRepository	actorRepository;


	@Override
	public Lessor convert(String text) {
		Lessor res;
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

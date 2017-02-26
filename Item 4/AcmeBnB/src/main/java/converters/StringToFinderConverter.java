
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.FinderRepository;
import domain.Finder;

@Component
@Transactional
public class StringToFinderConverter implements Converter<String, Finder> {

	@Autowired
	FinderRepository	finderRepository;


	@Override
	public Finder convert(String text) {
		Finder res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = finderRepository.findOne(id);
			}
		} catch (Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}
}

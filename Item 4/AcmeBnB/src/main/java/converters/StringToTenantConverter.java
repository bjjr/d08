
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TenantRepository;
import domain.Tenant;

@Component
@Transactional
public class StringToTenantConverter implements Converter<String, Tenant> {

	@Autowired
	TenantRepository	tenantRepository;


	@Override
	public Tenant convert(String text) {
		Tenant res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = tenantRepository.findOne(id);
			}
		} catch (Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}
}

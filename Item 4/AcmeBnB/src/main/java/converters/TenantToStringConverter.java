
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Tenant;

@Component
@Transactional
public class TenantToStringConverter implements Converter<Tenant, String> {

	@Override
	public String convert(Tenant tenant) {
		String res;

		if (tenant == null) {
			res = null;
		} else {
			res = String.valueOf(tenant.getId());
		}
		return res;
	}
}

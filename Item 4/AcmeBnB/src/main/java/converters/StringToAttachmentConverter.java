
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AttachmentRepository;
import domain.Attachment;

@Component
@Transactional
public class StringToAttachmentConverter implements Converter<String, Attachment> {

	@Autowired
	AttachmentRepository	attachmentRepository;


	@Override
	public Attachment convert(String text) {
		Attachment result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = attachmentRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}

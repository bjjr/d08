
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Book;

@Component
@Transactional
public class BookToStringConverter implements Converter<Book, String> {

	@Override
	public String convert(Book book) {
		String res;

		if (book == null) {
			res = null;
		} else {
			res = String.valueOf(book.getId());
		}
		return res;
	}
}

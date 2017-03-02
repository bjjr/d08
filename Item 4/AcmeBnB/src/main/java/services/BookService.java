
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BookRepository;
import utilities.DateUtil;
import domain.Book;
import domain.Property;

@Transactional
@Service
public class BookService {

	@Autowired
	private BookRepository	bookRepository;

	@Autowired
	private TenantService	tenantService;

	@Autowired
	private StatusService	statusService;


	public Book create(Property property) {
		Book book = new Book();

		Date currentDate = new Date(System.currentTimeMillis());

		book.setSmoker(false);
		book.setCheckInDate(currentDate);
		book.setCheckOutDate(DateUtil.addDays(currentDate, 1));

		book.setTenant(tenantService.findByPrincipal());
		book.setProperty(property);
		book.setStatus(statusService.create());

		return book;
	}

	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}

	public Collection<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book save(Book book) {
		Assert.notNull(book, "BookService.save: The 'book' can not be null");

		Assert.isTrue(DateUtil.isOneDayAfter(book.getCheckInDate(), book.getCheckOutDate()), "BookService.save: The checkoutDate has to be one day after than checkinDate");

		Date currentMoment = new Date(System.currentTimeMillis());
		Assert.isTrue(book.getCheckInDate().after(currentMoment) && book.getCheckOutDate().after(currentMoment), "BookService.save: Checkin and checkout need to be planned in the future");

		Assert.isTrue(checkJustABookPendingForTenant(book), "BookService.save: Just a single book PENDING over a same property");

		Book result;

		result = bookRepository.save(book);

		return result;
	}

	public void delete(Book book) {
		Assert.notNull(book, "BookService.delete: The 'book' can not be null");
		Assert.isTrue(book.getId() != 0, "BookService.delete: The id can not be zero");

		bookRepository.delete(book);
	}

	// Other business methods -------------------------------

	public Collection<Book> findBooksByPrincipal() {
		return bookRepository.findBooksByPrincipal(tenantService.findByPrincipal().getId());
	}
	
	public Boolean checkJustABookPendingForTenant(Book book){
		Collection<Book> tenantBooks = tenantService.findOne(book.getTenant().getId()).getBooks();
		
		List<Book> tenantBooksOverTheBookProperty = new ArrayList<>();
		for(Book tenantBook: tenantBooks){
			if(tenantBook.getProperty().equals(book.getProperty())){
				tenantBooksOverTheBookProperty.add(tenantBook);
			}
		}
		
		for (Book tenantBookOverTheBookProperty: tenantBooksOverTheBookProperty) {
			if(tenantBookOverTheBookProperty.getStatus().getName().equals("PENDING")){
				return false;
			}
		}
		
		return true;
	}

}

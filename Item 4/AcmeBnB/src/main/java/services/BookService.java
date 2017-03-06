
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BookRepository;
import utilities.DateUtil;
import domain.Book;
import domain.CreditCard;
import domain.Lessor;
import domain.Property;
import domain.Tenant;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository	bookRepository;

	@Autowired
	private TenantService	tenantService;

	@Autowired
	private LessorService	lessorService;

	@Autowired
	private StatusService	statusService;

	@Autowired
	private Validator		validator;


	public Book create(Property property) {
		Book book = new Book();

		Date currentDate = new Date(System.currentTimeMillis());

		book.setSmoker(false);
		book.setCheckInDate(currentDate);
		book.setCheckOutDate(DateUtil.addDays(currentDate, 1));

		book.setTenant(tenantService.findByPrincipal());
		book.setProperty(property);
		book.setStatus(statusService.findStatus("PENDING"));

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

		System.out.println(book);

		result = bookRepository.save(book);

		return result;
	}

	public void delete(Book book) {
		Assert.notNull(book, "BookService.delete: The 'book' can not be null");
		Assert.isTrue(book.getId() != 0, "BookService.delete: The id can not be zero");

		bookRepository.delete(book);
	}

	// Other business methods -------------------------------

	public Collection<Book> findTenantBooks() {	
		return bookRepository.findTenantBooks(tenantService.findByPrincipal().getId());
	}

	public Collection<Book> findLessorBooks() {
		Lessor lessor = lessorService.findByPrincipal();

		Collection<Book> books = new ArrayList<>();

		for (Property p : lessor.getProperties()) {
			books.addAll(p.getBooks());
		}

		return books;
	}
	
	private Boolean checkJustABookPendingForTenant(Book book){
		Tenant myself = tenantService.findOne(book.getTenant().getId());

		Collection<Book> tenantBooks = myself.getBooks();

		List<Book> tenantBooksOverTheBookProperty = new ArrayList<>();
		for (Book tenantBook : tenantBooks) {
			if (tenantBook.getProperty().equals(book.getProperty())) {
				tenantBooksOverTheBookProperty.add(tenantBook);
			}
		}

		for (Book tenantBookOverTheBookProperty : tenantBooksOverTheBookProperty) {
			if (tenantBookOverTheBookProperty.getStatus().getName().equals("PENDING") && tenantBookOverTheBookProperty.getId() != book.getId()) {
				return false;
			}
		}

		return true;
	}
	
	private Boolean isAValidCreditCard(CreditCard creditCard){
		Boolean res = false;
		
		Date currentMoment = new Date(System.currentTimeMillis());
		
		if(creditCard != null && creditCard.getExpiryDate().after(currentMoment)){
			res = true;
		}
		
		return res;
	}
	
	public void acceptBook(int bookId){
		Book bookToAccept = this.findOne(bookId);
		
		CreditCard lessorCreditCard = bookToAccept.getProperty().getLessor().getCreditCard();
		Assert.isTrue(isAValidCreditCard(lessorCreditCard), "You need a valid credit card in order to accept the book");
		
		bookToAccept.setStatus(statusService.findStatus("ACCEPTED"));
		
		this.save(bookToAccept);
	}

	public void denyBook(int bookId) {
		Book bookToAccept = this.findOne(bookId);
		
		bookToAccept.setStatus(statusService.findStatus("DENIED"));

		this.save(bookToAccept);
	}

	@Transactional(readOnly = true)
	public Book reconstruct(Book book, BindingResult bindingResult) {
		Book result;

		if (book.getId() == 0) {
			result = book;
		}else{
			Book aux = bookRepository.findOne(book.getId());
			result = book;
			
			if(result.getSmoker()== null){
				result.setSmoker(false); //Si el checkbox no está marcado
			}
			
			result.setProperty(aux.getProperty());
			result.setStatus(aux.getStatus());
			result.setTenant(aux.getTenant());
			
			validator.validate(result, bindingResult);
		}

		return result;
	}

	public Double findAvgBooksProperty1Audit() {
		Double result;

		result = bookRepository.findAvgBooksProperty1Audit();
		Assert.notNull(result);

		return result;
	}

	public Double findAvgBooksPropertyNoAudit() {
		Double result;

		result = bookRepository.findAvgBooksPropertyNoAudit();
		Assert.notNull(result);

		return result;
	}

}

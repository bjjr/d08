package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BookRepository;
import domain.Book;
import domain.Property;

@Transactional
@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TenantService tenantService;
	
	public Book create(Property property){
		Book book = new Book();
		
		
		book.setSmoker(false);
		book.setCheckInDate(new Date(System.currentTimeMillis()));
		book.setCheckOutDate(new Date(System.currentTimeMillis()+100));
		
		book.setTenant(tenantService.findByPrincipal());
		book.setProperty(property);
		//ToDo: book.setStatus()
		
		return book;
	}
	
	public Book findOne(Integer id){
		return bookRepository.findOne(id);
	}
	
	public Collection<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book save(Book book){
		Assert.notNull(book, "BookService.save: The 'book' can not be null");
		//Todo: Add constraints
		
		Book result;
		
		result = bookRepository.save(book);
		
		return result;
	}
	
	public void delete(Book book){
		Assert.notNull(book, "BookService.delete: The 'book' can not be null");
		Assert.isTrue(book.getId() != 0 , "BookService.delete: The id can not be zero");
		
		bookRepository.delete(book);
	}

}

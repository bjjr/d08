
package controllers.tenant;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookService;
import services.PropertyService;
import services.TenantService;
import controllers.AbstractController;
import domain.Book;
import domain.Property;
import domain.Tenant;

@Controller
@RequestMapping("/book/tenant")
public class BookTenantController extends AbstractController {

	@Autowired
	private TenantService	tenantService;

	@Autowired
	private BookService		bookService;
	
	@Autowired
	private PropertyService propertyService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int propertyId) {
		ModelAndView result;
		Book book;

		
		Property property = propertyService.findOne(propertyId);
		book = bookService.create(property);
		
		result = createEditModelAndView(book);
		
		return result;
	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Tenant tenant;
		Collection<Book> books = new ArrayList<>();

		tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);

		books = bookService.findTenantBooks();

		result = new ModelAndView("book/list");
		result.addObject("requestUri", "/book/tenant/list.do");
		result.addObject("books", books);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int bookId){
		ModelAndView view = new ModelAndView("book/edit");
		
		Book book = bookService.findOne(bookId);
		Assert.notNull(book);
		
		view.addObject("book", book);
		
		return view;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Book book, BindingResult bindingResult){
		ModelAndView result;

		Book book_reconstructed = bookService.reconstruct(book, bindingResult);
		
		if (bindingResult.hasErrors()){
			result = createEditModelAndView(book_reconstructed);
		} else {
			try {
				bookService.save(book_reconstructed);
				result = new ModelAndView("redirect:list.do");
			} catch (IllegalArgumentException oops) {
				result = createEditModelAndView(book_reconstructed, "misc.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Book book, BindingResult bindingResult){
		ModelAndView res;

		try {
			bookService.delete(book);
			res = new ModelAndView("redirect:list.do");
		} catch (Throwable th) {
			res = createEditModelAndView(book, "misc.commit.error");
		}

		return res;
	}
	
	
	
	protected ModelAndView createEditModelAndView(Book book) {
		ModelAndView result;

		result = createEditModelAndView(book, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Book book, String message) {
		ModelAndView result;
		
		result = new ModelAndView("book/edit");
		result.addObject("book", book);
		result.addObject("msg", message);

		return result;
	}

}

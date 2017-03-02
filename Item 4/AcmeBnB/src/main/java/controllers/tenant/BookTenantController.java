
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
import services.TenantService;
import controllers.AbstractController;
import domain.Book;
import domain.Tenant;

@Controller
@RequestMapping("/book/tenant")
public class BookTenantController extends AbstractController {

	@Autowired
	private TenantService	tenantService;

	@Autowired
	private BookService		bookService;


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
		
		System.out.println("Hay errores? "+bindingResult.hasErrors());
		if (bindingResult.hasErrors()){
			result = createEditModelAndView(book_reconstructed);
		} else {
			try {
				bookService.save(book_reconstructed);
				System.out.println("Llega redirreción");
				result = new ModelAndView("redirect:list.do");
			} catch (IllegalArgumentException oops) {
				System.out.println("Llega error");
				result = createEditModelAndView(book_reconstructed, "book.commit.error");				
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Book book, BindingResult bindingResult){
		//Todo
		return null;
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

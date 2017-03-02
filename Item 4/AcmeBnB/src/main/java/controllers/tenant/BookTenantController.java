
package controllers.tenant;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

		books = bookService.findBooksByPrincipal();

		result = new ModelAndView("book/list");
		result.addObject("books", books);

		return result;
	}

}


package controllers.lessor;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookService;
import services.LessorService;
import controllers.AbstractController;
import domain.Book;
import domain.Lessor;

@Controller
@RequestMapping("/book/lessor")
public class BookLessorController extends AbstractController {

	@Autowired
	private LessorService	lessorService;

	@Autowired
	private BookService		bookService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Lessor lessor;
		Collection<Book> books = new ArrayList<>();

		lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		books = bookService.findLessorBooks();

		result = new ModelAndView("book/list");
		result.addObject("requestUri", "/book/lessor/list.do");
		result.addObject("books", books);
		result.addObject("feeToPay", lessor.getAccumulatedCharges());

		return result;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam int bookId) {
		ModelAndView view = new ModelAndView("redirect:list.do");

		bookService.acceptBook(bookId);

		return view;
	}

	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam int bookId) {
		ModelAndView view = new ModelAndView("redirect:list.do");

		bookService.denyBook(bookId);

		return view;
	}

}

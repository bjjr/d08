
package controllers.tenant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookService;
import services.InvoiceService;
import utilities.DateUtil;
import controllers.AbstractController;
import domain.Book;
import domain.Invoice;

@Controller
@RequestMapping("/invoice/tenant")
public class InvoiceTenantController extends AbstractController {

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping(value = "/createInvoice", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int bookId) {
		ModelAndView result;
		
		Book book = bookService.findOne(bookId);
		
		Invoice invoice = invoiceService.create();
		invoice.setDetails(book.getProperty().getName());
		
		Double totalDue = DateUtil.getQuantityOfDays(book.getCheckInDate(), book.getCheckOutDate()) * book.getProperty().getRate();
		invoice.setTotalDue(totalDue);
		
		invoiceService.save(invoice);
		
		result = new ModelAndView("redirect:list.do");
		
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
	
		Collection<Invoice> tenantInvoices = invoiceService.findTenantInvoices();
		
		result = new ModelAndView("invoice/list");
		result.addObject("requestUri", "/invoice/tenant/list.do");
		result.addObject("invoices", tenantInvoices);

		return result;
	}
	
	


}

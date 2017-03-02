
package controllers;

import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardService;
import domain.CreditCard;

@Controller
@RequestMapping("/creditCard")
public class CreditCardController extends AbstractController {

	// Services ----------------------------------------------

	@Autowired
	private CreditCardService	creditCardService;


	// Constructor -------------------------------------------

	public CreditCardController() {
		super();
	}

	// Display -----------------------------------------------

	/*
	 * Credit card number is masked and only shows the first
	 * and the last 4 numbers.
	 */

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView res;
		CreditCard creditCard;
		String maskedNumber;
		String expiryDate;
		DateTimeFormatter dtf;

		dtf = DateTimeFormat.forPattern("dd/MM/yyyy");

		try {
			creditCard = creditCardService.findConsumerCreditCard();
			maskedNumber = creditCardService.getMaskedNumber(creditCard);
			expiryDate = dtf.print(creditCard.getExpiryDate().getTime());
		} catch (IllegalArgumentException e) {
			creditCard = null;
			maskedNumber = null;
			expiryDate = null;
		}

		res = new ModelAndView("creditcard/display");
		res.addObject("card", creditCard);
		res.addObject("maskedNumber", maskedNumber);
		res.addObject("expiryDate", expiryDate);

		return res;
	}
	// Creating ----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		CreditCard creditCard;

		creditCard = creditCardService.create();

		res = createEditModelAndView(creditCard);

		return res;
	}

	// Editing -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int creditCardId) {
		ModelAndView res;
		CreditCard creditCard;

		creditCard = creditCardService.findOne(creditCardId);

		res = createEditModelAndView(creditCard);

		return res;
	}

	// Saving -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding) {
		ModelAndView res;

		try {
			Assert.isTrue(creditCardService.checkDatesDifference(creditCard));
		} catch (Throwable th) {
			binding.rejectValue("expiryDate", "creditCard.error.dates");
		}

		if (binding.hasErrors()) {
			res = createEditModelAndView(creditCard);
		} else {
			try {
				creditCardService.save(creditCard);
				res = new ModelAndView("redirect:display.do");
			} catch (Throwable th) {
				res = createEditModelAndView(creditCard, "misc.commit.error");
			}
		}

		return res;
	}
	// Ancillary methods -------------------------------------
	protected ModelAndView createEditModelAndView(CreditCard creditCard) {
		ModelAndView res;

		res = createEditModelAndView(creditCard, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(CreditCard creditCard, String message) {
		ModelAndView res;

		res = new ModelAndView("creditcard/edit");

		res.addObject("creditCard", creditCard);
		res.addObject("message", message);

		return res;
	}
}

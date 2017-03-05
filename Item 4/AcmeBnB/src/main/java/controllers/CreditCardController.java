
package controllers;

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
import forms.CreditCardForm;

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
		CreditCardForm creditCardForm;

		creditCard = creditCardService.create();
		creditCardForm = new CreditCardForm(creditCard);

		res = createEditModelAndView(creditCardForm);

		return res;
	}

	// Editing -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int creditCardId) {
		ModelAndView res;
		CreditCard creditCard;
		CreditCardForm creditCardForm;
		DateTimeFormatter dtf;

		dtf = DateTimeFormat.forPattern("dd/MM/yyyy");

		creditCard = creditCardService.findOne(creditCardId);
		creditCardForm = new CreditCardForm(creditCard);
		creditCardForm.setDate(dtf.print(creditCard.getExpiryDate().getTime()));

		res = createEditModelAndView(creditCardForm);

		return res;
	}

	// Saving -----------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(CreditCardForm creditCardForm, BindingResult binding) {
		ModelAndView res;
		CreditCard creditCard;

		creditCard = creditCardService.reconstruct(creditCardForm, binding);

		if (binding.hasErrors()) {
			res = createEditModelAndView(creditCardForm);
		} else {
			try {
				Assert.isTrue(creditCardService.checkDatesDifference(creditCardForm));
				creditCardService.save(creditCard);
				res = new ModelAndView("redirect:display.do");
			} catch (Throwable th) {
				res = createEditModelAndView(creditCardForm, "misc.commit.error");
			}
		}

		return res;
	}

	// Ancillary methods -------------------------------------
	protected ModelAndView createEditModelAndView(CreditCardForm creditCardForm) {
		ModelAndView res;

		res = createEditModelAndView(creditCardForm, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(CreditCardForm creditCardForm, String message) {
		ModelAndView res;

		res = new ModelAndView("creditcard/edit");

		res.addObject("creditCardForm", creditCardForm);
		res.addObject("message", message);

		return res;
	}
}


package services;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CreditCardRepository;
import domain.ConsumerActor;
import domain.CreditCard;
import forms.CreditCardForm;

@Service
@Transactional
public class CreditCardService {

	// Managed repository -----------------------------------

	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Supporting services ----------------------------------

	@Autowired
	private ConsumerActorService	consumerActorService;

	@Autowired
	private Validator				validator;


	// Constructors -----------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public CreditCard create() {
		CreditCard result;
		Date today;

		result = new CreditCard();
		today = new DateTime().withTimeAtStartOfDay().toDate();

		result.setHolderName("");
		result.setBrandName("");
		result.setNumber("");
		result.setExpiryDate(DateUtils.addDays(today, 7));
		result.setCvv(0);

		return result;
	}
	/*
	 * The save method must check that the expiry date
	 * is at least 7 days in the future.
	 */

	public CreditCard save(CreditCard creditCard) {
		Assert.notNull(creditCard);

		CreditCard result;

		result = creditCardRepository.save(creditCard);

		return result;
	}

	public void flush() {
		creditCardRepository.flush();
	}

	public CreditCard findOne(int creditCardID) {
		CreditCard result;

		result = creditCardRepository.findOne(creditCardID);
		Assert.notNull(result);

		return result;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> result;

		result = creditCardRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other business methods -------------------------------

	/**
	 * Given a credit card this method checks if its expiry date
	 * is at least seven days in the future.
	 * 
	 * @param creditCardForm
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */

	public Boolean checkDatesDifference(CreditCardForm creditCardForm) {
		long diff;
		Boolean res;
		Date today;
		DateTimeFormatter dtf;

		dtf = DateTimeFormat.forPattern("dd/MM/yyyy");

		today = new DateTime().withTimeAtStartOfDay().toDate();
		diff = dtf.parseMillis(creditCardForm.getDate()) - today.getTime();
		res = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= 7;

		return res;
	}

	/**
	 * Get the provided credit card's number masked to allow
	 * display of it in views.
	 * 
	 * @param c
	 *            The credit card whose number must be masked
	 * @return A string with the masked credit card's number
	 */

	public String getMaskedNumber(CreditCard c) {
		Assert.notNull(c);

		String res;

		res = c.getNumber().subSequence(0, 4) + StringUtils.repeat("*", 8) + c.getNumber().substring(12);

		return res;
	}

	public CreditCard findConsumerCreditCard() {
		ConsumerActor consumer;
		CreditCard creditCard;

		consumer = consumerActorService.findByPrincipal();
		creditCard = consumer.getCreditCard();
		Assert.notNull(creditCard);

		return creditCard;
	}

	public CreditCard reconstruct(CreditCardForm creditCardForm, BindingResult binding) {
		CreditCard res;

		if (creditCardForm.getId() == 0) {
			res = creditCardRepository.findOne(creditCardForm.getId());
		} else {
			DateTimeFormatter dateFormat;
			DateTime expiryDate;

			res = creditCardRepository.findOne(creditCardForm.getId());
			dateFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
			expiryDate = dateFormat.parseDateTime(creditCardForm.getDate()).withTimeAtStartOfDay();

			res.setHolderName(creditCardForm.getHolderName());
			res.setBrandName(creditCardForm.getBrandName());
			res.setNumber(creditCardForm.getNumber());
			res.setExpiryDate(expiryDate.toDate());
			res.setCvv(creditCardForm.getCvv());
			validator.validate(res, binding);
		}

		return res;

	}
}

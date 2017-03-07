
package services;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.ConsumerActor;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Managed repository -----------------------------------

	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Supporting services ----------------------------------

	@Autowired
	private ConsumerActorService	consumerActorService;


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
		ConsumerActor consumerActor;

		CreditCard result;

		result = creditCardRepository.save(creditCard);
		consumerActor = consumerActorService.findByPrincipal();

		if (creditCard.getId() == 0) {
			consumerActor.setCreditCard(result);
			consumerActorService.save(consumerActor);
		}

		return result;
	}

	public void flush() {
		creditCardRepository.flush();
	}

	public CreditCard findOne(int creditCardId) {
		CreditCard result;

		result = creditCardRepository.findOne(creditCardId);
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
	 * @param creditCard
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */

	public Boolean checkDatesDifference(CreditCard creditCard) {
		long diff;
		Boolean res;
		Date today;
		Date expiryDate;

		Assert.notNull(creditCard);

		today = new DateTime().withTimeAtStartOfDay().toDate();
		expiryDate = new DateTime(creditCard.getExpiryDate()).withTimeAtStartOfDay().toDate();

		diff = expiryDate.getTime() - today.getTime();
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

	public CreditCard findOneToEdit(int creditCardId) {
		CreditCard result;
		ConsumerActor consumerActor;

		consumerActor = consumerActorService.findByPrincipal();
		result = creditCardRepository.findOne(creditCardId);
		Assert.notNull(result, "The credit card does not exist");
		Assert.isTrue(consumerActor.getCreditCard().equals(result), "This is not your credit card");

		return result;
	}

}

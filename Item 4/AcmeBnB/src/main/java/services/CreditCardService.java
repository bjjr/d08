
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;

public class CreditCardService {

	// Managed repository -----------------------------------

	@Autowired
	private CreditCardRepository	creditCardRepository;


	// Supporting services ----------------------------------

	// Constructors -----------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public CreditCard create() {
		CreditCard result;

		result = new CreditCard();

		result.setBrandName("");
		result.setCvv(0);
		result.setExpirationMonth(0);
		result.setExpirationYear(0);
		result.setHolderName("");
		result.setNumber("");

		return result;
	}

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

}


package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.FeeRepository;
import domain.Fee;

public class FeeService {

	// Managed repository -----------------------------------

	@Autowired
	private FeeRepository	feeRepository;


	// Supporting services ----------------------------------

	// Constructors -----------------------------------------

	public FeeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Fee save(Fee fee) {
		Assert.notNull(fee);

		Fee result;

		result = feeRepository.save(fee);

		return result;
	}

	public Fee findOne(int feeID) {
		Fee result;

		result = feeRepository.findOne(feeID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Fee> findAll() {
		Collection<Fee> result;

		result = feeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		feeRepository.flush();
	}

	// Other business methods -------------------------------

}


package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FeeRepository;
import domain.Fee;

@Service
@Transactional
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

	public void flush() {
		feeRepository.flush();
	}

	// Other business methods -------------------------------

	public Fee findFee() {
		Fee res;

		res = feeRepository.findFee();
		Assert.notNull(res, "No fee exists in the database.");

		return res;
	}

}

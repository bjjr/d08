
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import domain.Lessor;

public class LessorService {

	// Managed repository -----------------------------------

	@Autowired
	private LessorRepository	lessorRepository;


	// Supporting services ----------------------------------

	// Constructors -----------------------------------------

	public LessorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Lessor findOne(int lessorID) {
		Lessor result;

		result = lessorRepository.findOne(lessorID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Lessor> findAll() {
		Collection<Lessor> result;

		result = lessorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Lessor create() {
		Lessor result;

		result = new Lessor();

		return result;
	}

	public Lessor save(Lessor lessor) {
		Assert.notNull(lessor);

		Lessor result;

		result = lessorRepository.save(lessor);

		return result;
	}

	public void flush() {
		lessorRepository.flush();
	}

	// Other business methods -------------------------------

}

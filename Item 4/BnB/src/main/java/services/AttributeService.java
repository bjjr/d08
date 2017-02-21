
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.AttributeRepository;
import domain.Attribute;

public class AttributeService {

	// Managed repository -----------------------------------

	@Autowired
	private AttributeRepository	attributeRepository;


	// Supporting services ----------------------------------

	// Constructors -----------------------------------------

	public AttributeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Attribute findOne(int attributeID) {
		Attribute result;

		result = attributeRepository.findOne(attributeID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Attribute> findAll() {
		Collection<Attribute> result;

		result = attributeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Attribute create() {
		Attribute result;

		result = new Attribute();

		return result;
	}

	public Attribute save(Attribute attribute) {
		Assert.notNull(attribute);

		Attribute result;

		result = attributeRepository.save(attribute);

		return result;
	}

	public void flush() {
		attributeRepository.flush();
	}

	// Other business methods -------------------------------

}

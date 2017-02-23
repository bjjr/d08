
package services;

import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AttributeRepository;
import domain.Attribute;
import domain.AttributeValue;

@Service
@Transactional
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

	public Attribute create() {
		Attribute result;
		Collection<AttributeValue> attributes;

		result = new Attribute();
		attributes = new LinkedList<>();

		result.setAttributeValues(attributes);
		result.setName("");

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

	// Other business methods -------------------------------

}
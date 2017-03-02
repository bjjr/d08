
package services;

import java.util.Collection;

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
	private AttributeRepository		attributeRepository;

	// Supporting services ----------------------------------

	@Autowired
	private AttributeValueService	attributeValueService;


	// Constructors -----------------------------------------

	public AttributeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Attribute create() {
		Attribute result;

		result = new Attribute();

		result.setName("");

		return result;
	}
	public Attribute save(Attribute attribute) {
		Assert.notNull(attribute);

		Attribute result;

		result = attributeRepository.save(attribute);

		return result;
	}

	public void delete(Attribute attribute) {
		Assert.notNull(attribute);
		Collection<AttributeValue> attributeValues;

		attributeValues = attributeValueService.findAttributeValuesByAttribute(attribute.getId());

		for (AttributeValue a : attributeValues) {
			attributeValueService.delete(a);
		}

		attributeRepository.delete(attribute);
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

	public Collection<Attribute> findListAttributesSortedByTimesUsed() {
		Collection<Attribute> result;

		result = attributeRepository.findListAttributesSortedByTimesUsed();
		Assert.notNull(result);

		return result;
	}

}

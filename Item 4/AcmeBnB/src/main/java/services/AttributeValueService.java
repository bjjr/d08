
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AttributeValueRepository;
import domain.AttributeValue;

@Service
@Transactional
public class AttributeValueService {

	// Managed repository -----------------------------------

	@Autowired
	private AttributeValueRepository	attributeValueRepository;


	// Supporting services ----------------------------------

	// Constructors -----------------------------------------

	public AttributeValueService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public AttributeValue create() {
		AttributeValue result;

		result = new AttributeValue();

		return result;
	}

	public AttributeValue save(AttributeValue attributeValue) {
		Assert.notNull(attributeValue);

		AttributeValue result;

		result = attributeValueRepository.save(attributeValue);

		return result;
	}

	public void flush() {
		attributeValueRepository.flush();
	}

	public AttributeValue findOne(int attributeValueID) {
		AttributeValue result;

		result = attributeValueRepository.findOne(attributeValueID);
		Assert.notNull(result);

		return result;
	}

	public Collection<AttributeValue> findAll() {
		Collection<AttributeValue> result;

		result = attributeValueRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other business methods -------------------------------

	public Collection<AttributeValue> findAttributesValuesByProperty(int propertyId) {
		Collection<AttributeValue> res;

		res = attributeValueRepository.findAttributesValuesByProperty(propertyId);

		return res;
	}

}

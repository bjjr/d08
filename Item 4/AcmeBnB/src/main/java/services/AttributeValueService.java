
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AttributeValueRepository;
import domain.AttributeValue;
import domain.Lessor;
import domain.Property;

@Service
@Transactional
public class AttributeValueService {

	// Managed repository -----------------------------------

	@Autowired
	private AttributeValueRepository	attributeValueRepository;

	// Supporting services ----------------------------------

	@Autowired
	private LessorService				lessorService;

	@Autowired
	private PropertyService				propertyService;

	@Autowired
	private Validator					validator;


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

	public void delete(AttributeValue attributeValue) {
		Assert.notNull(attributeValue);

		attributeValueRepository.delete(attributeValue);
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

	public Collection<AttributeValue> findAttributeValuesByAttribute(int attributeId) {
		Collection<AttributeValue> res;

		res = attributeValueRepository.findAttributeValuesByAttribute(attributeId);

		return res;
	}

	public AttributeValue findOneToEdit(int attributeValueId, int propertyId) {
		Lessor lessor;
		AttributeValue res;
		Property property;

		lessor = lessorService.findByPrincipal();
		res = findOne(attributeValueId);
		property = propertyService.findOne(propertyId);

		Assert.notNull(lessor);
		Assert.notNull(res);
		Assert.notNull(property);

		Assert.isTrue(res.getProperty().equals(property));
		Assert.isTrue(property.getLessor().equals(lessor));

		return res;
	}

	public AttributeValue reconstruct(AttributeValue attributeValue, int propertyId, BindingResult binding) {
		AttributeValue res;

		if (attributeValue.getId() == 0) {
			Property property;

			res = attributeValue;
			property = propertyService.findOne(propertyId);
			res.setProperty(property);
		} else {
			res = attributeValueRepository.findOne(attributeValue.getId());

			res.setAttribute(attributeValue.getAttribute());
			res.setValue(attributeValue.getValue());

			validator.validate(res, binding);
		}

		return res;
	}
}

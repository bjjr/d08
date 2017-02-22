
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PropertyRepository;
import domain.Property;

@Service
@Transactional
public class PropertyService {

	// Managed repository -----------------------------------

	@Autowired
	private PropertyRepository	propertyRepository;


	// Supporting services ----------------------------------

	// Constructors -----------------------------------------

	public PropertyService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Property create() {
		Property result;

		result = new Property();

		result.setAddress("");
		result.setAttributeValues(null);
		result.setAudits(null);
		result.setBooks(null);
		result.setDescription("");
		result.setLessor(null);//TODO
		result.setName("");
		result.setRate(0);

		return result;
	}
	public Property save(Property property) {
		Assert.notNull(property);

		Property result;

		result = propertyRepository.save(property);

		return result;
	}

	public Property findOne(int propertyID) {
		Property result;

		result = propertyRepository.findOne(propertyID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Property> findAll() {
		Collection<Property> result;

		result = propertyRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		propertyRepository.flush();
	}

	// Other business methods -------------------------------

}

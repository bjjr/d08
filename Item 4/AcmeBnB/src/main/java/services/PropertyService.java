
package services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PropertyRepository;
import domain.Audit;
import domain.Book;
import domain.Lessor;
import domain.Property;

@Service
@Transactional
public class PropertyService {

	// Managed repository -----------------------------------

	@Autowired
	private PropertyRepository	propertyRepository;

	// Supporting services ----------------------------------

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------

	public PropertyService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Property create() {
		Property result;
		Lessor lessor;
		Collection<Audit> audits;
		Collection<Book> books;

		result = new Property();
		lessor = lessorService.findByPrincipal();
		audits = new LinkedList<>();
		books = new LinkedList<>();

		result.setName("");
		result.setRate(0);
		result.setAddress("");
		result.setDescription("");
		result.setLessor(lessor);
		result.setAudits(audits);
		result.setBooks(books);

		return result;
	}
	public Property save(Property property) {
		Assert.notNull(property);

		Property result;

		result = propertyRepository.save(property);

		return result;
	}

	public void delete(Property property) {
		Assert.notNull(property);

		Assert.isTrue(pendingAcceptedBooks(property.getId()).isEmpty(), "You have pending books");

		propertyRepository.delete(property);
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

	private List<Book> pendingAcceptedBooks(int propertyId) {
		List<Book> result;

		result = propertyRepository.pendingAcceptedBooks(propertyId);
		Assert.notNull(result);

		return result;
	}
	public Property reconstruct(Property property, BindingResult bindingResult) {
		Property result;

		if (property.getId() == 0) {
			result = property;
		} else {
			result = propertyRepository.findOne(property.getId());

			result.setName(property.getName());
			result.setDescription(property.getDescription());
			result.setAddress(property.getAddress());

			validator.validate(result, bindingResult);
		}

		return result;

	}

	private void addBook(Property p, Book b) {
		b.setProperty(p);
		p.getBooks().add(b);
	}

	private void removeBook(Property p, Book b) {
		b.setProperty(null);
		p.getBooks().remove(b);
	}

	private void addAudit(Property p, Audit a) {
		a.setProperty(p);
		p.getAudits().add(a);
	}

	private void removeAudit(Property p, Audit a) {
		a.setProperty(null);
		p.getAudits().remove(a);
	}

}

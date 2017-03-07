
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.LessorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Comment;
import domain.Lessor;
import domain.Property;
import domain.SocialIdentity;
import forms.LessorForm;

@Service
@Transactional
public class LessorService {

	// Managed repository -----------------------------------

	@Autowired
	private LessorRepository		lessorRepository;

	// Supporting services ----------------------------------

	@Autowired
	private ConsumerActorService	consumerActorService;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private Validator				validator;


	// Constructors -----------------------------------------

	public LessorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Lessor findOne(int lessorId) {
		Lessor result;

		result = lessorRepository.findOne(lessorId);
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

		consumerActorService.setConsumerActorProperties(result);
		result.setAccumulatedCharges(0.0);
		result.setUserAccount(userAccountService.create("LESSOR"));
		result.setProperties(new ArrayList<Property>());

		return result;
	}
	public Lessor save(Lessor lessor) {
		Lessor result, authenticatedLessor;
		Assert.notNull(lessor);

		if (lessor.getId() != 0) {
			authenticatedLessor = findByPrincipal();
			Assert.isTrue(lessor.equals(authenticatedLessor));
		} else {
			Assert.isTrue(lessor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("LESSOR"));
			lessor.getUserAccount().setPassword(hashCodePassword(lessor.getUserAccount().getPassword()));
		}
		result = lessorRepository.save(lessor);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		lessorRepository.flush();
	}

	public Lessor findByPrincipal() {
		Lessor lessor;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		lessor = findByUserAccount(userAccount);

		return lessor;
	}

	public Lessor findByUserAccount(UserAccount userAccount) {
		Lessor res;

		res = lessorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(res, "The user is not a lessor");

		return res;
	}

	// Other business methods -------------------------------

	public Lessor reconstruct(LessorForm lessor, BindingResult binding) {
		Lessor result;
		Authority auth;
		auth = new Authority();
		auth.setAuthority("LESSOR");

		result = lessor.getLessor();

		result.getUserAccount().addAuthority(auth);
		result.setAccumulatedCharges(0.0);
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		result.setComments(new ArrayList<Comment>());
		result.setProperties(new ArrayList<Property>());

		validator.validate(result, binding);

		return result;
	}

	public Lessor reconstruct(Lessor lessor, BindingResult binding) {
		Lessor result;

		Lessor aux = findByPrincipal();
		result = lessor;

		result.setProperties(aux.getProperties());
		result.setAccumulatedCharges(aux.getAccumulatedCharges());
		result.setCreditCard(aux.getCreditCard());
		result.setSocialIdentities(aux.getSocialIdentities());
		result.setUserAccount(aux.getUserAccount());
		result.setComments(aux.getComments());

		validator.validate(result, binding);

		return result;
	}
	public Double avgAcceptedPerLessor() {
		Double avg;

		avg = lessorRepository.avgAcceptedPerLessor();

		Assert.notNull(avg);

		return avg;
	}

	public Double avgDeniedPerLessor() {
		Double avg;

		avg = lessorRepository.avgDeniedPerLessor();

		Assert.notNull(avg);

		return avg;
	}

	public Collection<Lessor> lessorNumApproved() {
		Collection<Lessor> lessors;

		lessors = lessorRepository.lessorsOrderByNumApproved();
		Assert.notNull(lessors);

		return lessors;
	}

	public Collection<Lessor> lessorNumPending() {
		Collection<Lessor> lessors;

		lessors = lessorRepository.lessorsOrderByNumPending();
		Assert.notNull(lessors);

		return lessors;
	}

	public Collection<Lessor> lessorNumDenied() {
		Collection<Lessor> lessors;

		lessors = lessorRepository.lessorsOrderByNumDenied();
		Assert.notNull(lessors);

		return lessors;
	}

	public Lessor lessorMaxRatio() {
		List<Lessor> lessors;
		Lessor lessor;

		lessors = (List<Lessor>) lessorRepository.lessorMaxRatio();
		Assert.notNull(lessors);

		lessor = lessors.get(lessors.size() - 1);
		Assert.notNull(lessor);

		return lessor;
	}

	public Lessor lessorMinRatio() {
		List<Lessor> lessors;
		Lessor lessor;

		lessors = (List<Lessor>) lessorRepository.lessorMinRatio();
		Assert.notNull(lessors);

		lessor = lessors.get(lessors.size() - 1);
		Assert.notNull(lessor);

		return lessor;
	}

	public String hashCodePassword(String password) {
		String result;
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		result = encoder.encodePassword(password, null);

		return result;
	}

	//	@Query("select p from Property p where p.lessor.id=?1 order by p.audits.size desc")
	public List<Property> propertiesSortedByAudits(int lessorId) {
		List<Property> properties;

		properties = lessorRepository.propertiesSortedByAudits(lessorId);
		Assert.notNull(properties);

		return properties;
	}
	//
	//	@Query("select p from Property p where p.lessor.id=?1 order by p.books.size desc")
	public List<Property> propertiesSortedByBooks(int lessorId) {
		List<Property> properties;

		properties = lessorRepository.propertiesSortedByBooks(lessorId);
		Assert.notNull(properties);

		return properties;
	}
	//
	//	@Query("select p from Property p join p.books b where b.status.name = 'ACCEPTED' AND p.lessor.id = 15 order by b.size")
	public List<Property> propertiesSortedByAcceptedBooks(int lessorId) {
		List<Property> properties;

		properties = lessorRepository.propertiesSortedByAcceptedBooks(lessorId);
		Assert.notNull(properties);

		return properties;
	}
	//
	//	@Query("select p from Property p join p.books b where b.status.name = 'DENIED' AND p.lessor.id = 15 order by b.size")
	public List<Property> propertiesSortedByDeniedBooks(int lessorId) {
		List<Property> properties;

		properties = lessorRepository.propertiesSortedByDeniedBooks(lessorId);
		Assert.notNull(properties);

		return properties;
	}
	//
	//	@Query("select p from Property p join p.books b where b.status.name = 'PENDING' AND p.lessor.id = 15 order by b.size")
	public List<Property> propertiesSortedByPendingBooks(int lessorId) {
		List<Property> properties;

		properties = lessorRepository.propertiesSortedByPendingBooks(lessorId);
		Assert.notNull(properties);

		return properties;
	}

}


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
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Lessor;
import domain.Property;
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
			Assert.isTrue(lessor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("TENANT"));
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

		if (lessor.getLessor().getId() == 0) {
			result = lessor.getLessor();
		} else {
			result = lessorRepository.findOne(lessor.getLessor().getId());

			result.setName(lessor.getLessor().getName());
			result.setSurname(lessor.getLessor().getSurname());
			result.setEmail(lessor.getLessor().getEmail());
			result.setPhone(lessor.getLessor().getPhone());
			result.setPicture(lessor.getLessor().getPicture());

			result.getUserAccount().setUsername(lessor.getLessor().getUserAccount().getUsername());
			result.getUserAccount().setPassword(lessor.getLessor().getUserAccount().getPassword());

		}

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

}

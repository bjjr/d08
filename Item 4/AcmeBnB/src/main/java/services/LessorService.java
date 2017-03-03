
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.LessorRepository;
import security.LoginService;
import security.UserAccount;
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
		result.setProperties(new ArrayList<Property>());

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

			validator.validate(result, binding);

		}

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

	public String lessorMaxNumApproved() {
		List<Object[]> lessors;
		Lessor lessor;
		String lessorName;

		lessors = lessorRepository.lessorsOrderByNumApproved();

		Assert.notNull(lessors);

		lessor = (Lessor) lessors.get(0)[0];

		Assert.notNull(lessor);

		lessorName = lessor.getName();

		Assert.notNull(lessorName);

		return lessorName;
	}

	public String lessorMaxNumPending() {
		List<Object[]> lessors;
		Lessor lessor;
		String lessorName;

		lessors = lessorRepository.lessorsOrderByNumPending();

		Assert.notNull(lessors);

		lessor = (Lessor) lessors.get(0)[0];

		Assert.notNull(lessor);

		lessorName = lessor.getName();

		Assert.notNull(lessorName);

		return lessorName;
	}

	public String lessorMaxNumDenied() {
		List<Object[]> lessors;
		Lessor lessor;
		String lessorName;

		lessors = lessorRepository.lessorsOrderByNumDenied();

		Assert.notNull(lessors);

		lessor = (Lessor) lessors.get(0)[0];

		Assert.notNull(lessor);

		lessorName = lessor.getName();

		Assert.notNull(lessorName);

		return lessorName;
	}

	public Lessor lessorMaxRatio() {
		Lessor lessor;

		lessor = lessorRepository.lessorMaxRatio();

		return lessor;
	}

}

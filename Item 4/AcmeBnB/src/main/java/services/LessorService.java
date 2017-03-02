
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LessorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Lessor;
import domain.Property;

@Service
@Transactional
public class LessorService {

	// Managed repository -----------------------------------

	@Autowired
	private LessorRepository	lessorRepository;


	// Supporting services ----------------------------------

	//	@Autowired
	//TODO	private UserAccountService	UserAccountService;

	// Constructors -----------------------------------------

	public LessorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Lessor findOne(int lessorID) {
		Lessor result;

		result = lessorRepository.findOne(lessorID);
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

		//TODO

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

}

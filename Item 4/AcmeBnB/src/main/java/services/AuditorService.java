
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Auditor;

@Service
@Transactional
public class AuditorService {

	// Managed repository -----------------------------------

	@Autowired
	private AuditorRepository	auditorRepository;

	// Supporting services ----------------------------------

	@Autowired
	private UserAccountService	userAccountService;


	// Constructors -----------------------------------------

	public AuditorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Auditor create() {
		Auditor result;
		UserAccount userAccount;

		userAccount = userAccountService.create("AUDITOR");

		result = new Auditor();
		result.setUserAccount(userAccount);

		return result;
	}

	public Auditor findOne(int auditorId) {
		Auditor result;

		result = auditorRepository.findOne(auditorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Auditor> findAll() {
		Collection<Auditor> result;

		result = auditorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Auditor save(Auditor auditor) {
		Assert.notNull(auditor);

		Auditor result;

		result = auditorRepository.save(auditor);

		return result;
	}

	// Other business methods -------------------------------

	public Auditor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Auditor result;

		result = auditorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Auditor findByPrincipal() {
		Auditor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		auditorRepository.flush();
	}

}

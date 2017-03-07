
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.Comment;
import domain.SocialIdentity;
import forms.AuditorForm;

@Service
@Transactional
public class AuditorService {

	// Managed repository -----------------------------------

	@Autowired
	private AuditorRepository	auditorRepository;

	// Supporting services ----------------------------------

	// Validator --------------------------------------------

	@Autowired
	private Validator			validator;


	// Constructors -----------------------------------------

	public AuditorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Auditor create() {
		Auditor result;
		Authority authority;
		UserAccount userAccount;
		Collection<Audit> audits;

		userAccount = new UserAccount();
		userAccount.setUsername("");
		userAccount.setPassword("");

		result = new Auditor();

		result.setUserAccount(userAccount);
		audits = new ArrayList<Audit>();
		result.setAudits(audits);

		authority = new Authority();
		authority.setAuthority("AUDITOR");
		result.getUserAccount().addAuthority(authority);

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
		Auditor authenticatedAuditor;

		if (auditor.getId() == 0) {
			Assert.isTrue(auditor.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("AUDITOR"));
			auditor.getUserAccount().setPassword(hashCodePassword(auditor.getUserAccount().getPassword()));
		} else {
			authenticatedAuditor = findByPrincipal();
			Assert.isTrue(auditor.equals(authenticatedAuditor));
		}

		result = auditorRepository.save(auditor);
		Assert.notNull(result);

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

	public Auditor reconstruct(Auditor auditor, BindingResult binding) {
		Auditor result;

		Auditor aux = findByPrincipal();
		result = auditor;

		result.setAudits(aux.getAudits());
		result.setSocialIdentities(aux.getSocialIdentities());
		result.setUserAccount(aux.getUserAccount());
		result.setComments(aux.getComments());

		validator.validate(result, binding);

		return result;
	}

	public Auditor reconstruct(AuditorForm auditorForm, BindingResult binding) {
		Auditor result;
		Authority auth;
		auth = new Authority();
		auth.setAuthority("AUDITOR");

		result = auditorForm.getAuditor();

		result.getUserAccount().addAuthority(auth);
		result.setAudits(new ArrayList<Audit>());
		result.setSocialIdentities(new ArrayList<SocialIdentity>());
		result.setComments(new ArrayList<Comment>());

		validator.validate(result, binding);

		return result;
	}

	public void flush() {
		auditorRepository.flush();
	}

	public String hashCodePassword(String password) {
		String result;
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		result = encoder.encodePassword(password, null);

		return result;
	}
}

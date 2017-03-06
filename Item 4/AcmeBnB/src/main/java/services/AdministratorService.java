
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;

	//Validator

	@Autowired
	private Validator				validator;


	// Constructors -----------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Administrator save(Administrator admin) {
		Assert.notNull(admin);

		Administrator result;

		result = administratorRepository.save(admin);

		return result;
	}

	// Other business methods -------------------------------

	public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = administratorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Administrator reconstruct(Administrator administrator, BindingResult binding) {
		Administrator result;

		if (administrator.getId() == 0) {
			result = administrator;
		} else {
			result = findByPrincipal();

			result.setName(administrator.getName());
			result.setSurname(administrator.getSurname());
			result.setEmail(administrator.getEmail());
			result.setPhone(administrator.getPhone());
			result.setPicture(administrator.getPicture());

			validator.validate(result, binding);
		}

		return result;
	}

}


package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConsumerActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.ConsumerActor;

@Service
@Transactional
public class ConsumerActorService {

	//Managed repository --------------------------
	@Autowired
	private ConsumerActorRepository	consumerActorRepository;


	// Supporting services --------------------------

	// Constructors ---------------------------------

	public ConsumerActorService() {
		super();
	}

	// Simple CRUD methods --------------------------

	public ConsumerActor findOne(Integer caId) {
		Assert.isTrue(caId != 0);

		ConsumerActor res;
		res = consumerActorRepository.findOne(caId);

		Assert.notNull(res);

		return res;
	}

	public ConsumerActor save(ConsumerActor ca) {
		Assert.notNull(ca);

		ConsumerActor res;
		res = consumerActorRepository.save(ca);

		return res;
	}

	public void flush() {
		consumerActorRepository.flush();
	}

	// Other business methods -----------------------

	public ConsumerActor findByPrincipal() {
		ConsumerActor result = null;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);

		return result;
	}

	public ConsumerActor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		ConsumerActor res;
		res = consumerActorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(res);

		return res;
	}

}

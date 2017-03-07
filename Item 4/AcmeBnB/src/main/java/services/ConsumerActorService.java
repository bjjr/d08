
package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConsumerActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.ConsumerActor;
import domain.SocialIdentity;

@Service
@Transactional
public class ConsumerActorService {

	// Managed repository -----------------------------------

	@Autowired
	private ConsumerActorRepository	consumerActorRepository;


	// Supporting services ----------------------------------

	//@Autowired
	//private ActorService			actorService;

	// Constructors -----------------------------------------

	public ConsumerActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public ConsumerActor save(ConsumerActor consumerActor) {
		Assert.notNull(consumerActor);

		ConsumerActor result;

		result = consumerActorRepository.save(consumerActor);

		return result;
	}

	public void flush() {
		consumerActorRepository.flush();
	}

	public ConsumerActor findOne(int consumerActorID) {
		ConsumerActor result;

		result = consumerActorRepository.findOne(consumerActorID);
		Assert.notNull(result);

		return result;
	}

	public Collection<ConsumerActor> findAll() {
		Collection<ConsumerActor> result;

		result = consumerActorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other business methods -------------------------------

	public void setConsumerActorProperties(ConsumerActor ca) {
		Collection<Comment> comments;
		Collection<SocialIdentity> socialIdentities;

		comments = new LinkedList<>();

		socialIdentities = new LinkedList<>();

		ca.setComments(comments);
		ca.setSocialIdentities(socialIdentities);
		ca.setName("");
		ca.setSurname("");
		ca.setEmail("");
		ca.setPhone("");
		ca.setPicture("");
	}

	public ConsumerActor findByPrincipal() {
		ConsumerActor consumerActor;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		consumerActor = findByUserAccount(userAccount);

		return consumerActor;
	}

	public ConsumerActor findByUserAccount(UserAccount userAccount) {
		ConsumerActor res;

		res = consumerActorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(res, "The user is not a consumer");

		return res;
	}
}

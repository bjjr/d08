
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConsumerActorRepository;
import domain.ConsumerActor;

@Service
@Transactional
public class ConsumerActorService {

	// Managed repository -----------------------------------

	@Autowired
	private ConsumerActorRepository	ConsumerActorRepository;


	// Supporting services ----------------------------------

	//@Autowired
	//private ActorService			actorService;

	// Constructors -----------------------------------------

	public ConsumerActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public ConsumerActor findOne(int consumerActorID) {
		ConsumerActor result;

		result = ConsumerActorRepository.findOne(consumerActorID);
		Assert.notNull(result);

		return result;
	}

	public Collection<ConsumerActor> findAll() {
		Collection<ConsumerActor> result;

		result = ConsumerActorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public ConsumerActor save(ConsumerActor consumerActor) {
		Assert.notNull(consumerActor);

		ConsumerActor result;

		result = ConsumerActorRepository.save(consumerActor);

		return result;
	}

	public void flush() {
		ConsumerActorRepository.flush();
	}

	// Other business methods -------------------------------

}

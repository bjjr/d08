
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	// Managed repository -----------------------------------

	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;

	// Supporting services ----------------------------------

	@Autowired
	private ActorService				actorService;

	// Validator --------------------------------------------

	@Autowired
	private Validator					validator;


	// Constructors -----------------------------------------

	public SocialIdentityService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public SocialIdentity create() {
		SocialIdentity result;
		Actor actor;

		result = new SocialIdentity();
		actor = actorService.findByPrincipal();
		result.setActor(actor);

		return result;
	}

	public SocialIdentity findOne(int socialIdentityId) {
		SocialIdentity result;

		result = socialIdentityRepository.findOne(socialIdentityId);
		Assert.notNull(result);

		return result;
	}

	public Collection<SocialIdentity> findAll() {
		Collection<SocialIdentity> result;

		result = socialIdentityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity save(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);

		SocialIdentity result;
		Actor actor;

		if (socialIdentity.getId() == 0) {
			actor = actorService.findByPrincipal();
			actor.getSocialIdentities().add(socialIdentity);
			actorService.save(actor);
		}

		result = socialIdentityRepository.save(socialIdentity);

		return result;
	}

	public void delete(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);
		Assert.isTrue(socialIdentity.getId() != 0);

		Assert.isTrue(socialIdentityRepository.exists(socialIdentity.getId()));

		Actor actor;

		actor = actorService.findByPrincipal();
		actor.getSocialIdentities().remove(socialIdentity);
		actorService.save(actor);

		socialIdentityRepository.delete(socialIdentity);
	}

	// Other business methods -------------------------------

	public Double findMinSocialIdentities() {
		Double result;

		result = socialIdentityRepository.findMinSocialIdentities();
		Assert.notNull(result);

		return result;
	}

	public Double findMaxSocialIdentities() {
		Double result;

		result = socialIdentityRepository.findMaxSocialIdentities();
		Assert.notNull(result);

		return result;
	}

	public Double findAvgSocialIdentities() {
		Double result;

		result = socialIdentityRepository.findAvgSocialIdentities();
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity reconstruct(SocialIdentity socialIdentity, BindingResult binding) {
		SocialIdentity result;
		Actor actor;

		if (socialIdentity.getId() == 0) {
			actor = actorService.findByPrincipal();
			result = socialIdentity;
			result.setActor(actor);
		} else {
			SocialIdentity aux;
			aux = findOne(socialIdentity.getId());
			result = socialIdentity;
			result.setActor(aux.getActor());
		}

		validator.validate(result, binding);

		return result;
	}

}

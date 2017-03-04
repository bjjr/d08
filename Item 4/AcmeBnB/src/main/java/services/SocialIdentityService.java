
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	//Managed repository --------------------------
	@Autowired
	private SocialIdentityRepository	socialIdentityRepository;


	// Supporting services --------------------------

	// Constructors ---------------------------------

	public SocialIdentityService() {
		super();
	}

	// Simple CRUD methods --------------------------

	public SocialIdentity findOne(int id) {
		SocialIdentity res;
		res = socialIdentityRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<SocialIdentity> findAll() {
		Collection<SocialIdentity> result;

		result = socialIdentityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SocialIdentity save(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);

		SocialIdentity res;
		res = socialIdentityRepository.save(socialIdentity);

		return res;
	}

	public void flush() {
		socialIdentityRepository.flush();
	}

	// Other business methods -----------------------

}

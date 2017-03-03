
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentableEntityRepository;
import domain.CommentableEntity;

@Service
@Transactional
public class CommentableEntityService {

	//Managed repository --------------------------
	@Autowired
	private CommentableEntityRepository	commentableEntityRepository;


	// Supporting services --------------------------

	// Constructors ---------------------------------

	public CommentableEntityService() {
		super();
	}

	// Simple CRUD methods --------------------------

	public CommentableEntity findOne(Integer commentableEntityId) {
		Assert.isTrue(commentableEntityId != 0);

		CommentableEntity res;
		res = commentableEntityRepository.findOne(commentableEntityId);

		Assert.notNull(res);

		return res;
	}

	public CommentableEntity save(CommentableEntity ce) {
		Assert.notNull(ce);

		CommentableEntity res;
		res = commentableEntityRepository.save(ce);

		return res;
	}

	public void flush() {
		commentableEntityRepository.flush();
	}

}

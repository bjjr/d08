
package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.CommentableEntity;
import domain.ConsumerActor;

@Service
@Transactional
public class CommentService {

	//Managed repository

	@Autowired
	private CommentRepository			commentRepository;

	// Supporting services
	@Autowired
	private ActorService				actorService;

	@Autowired
	private CommentableEntityService	commentableEntityService;


	//Constructors
	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create(ConsumerActor consumerActor) {
		Assert.notNull(consumerActor);
		Assert.isTrue(consumerActor.getId() != 0);

		Comment result;
		Date moment;

		sc = socialActorService.findByPrincipal();
		identity = sc.getName() + sc.getSurname();
		result = new Comment();
		result.setRecipe(recipe);
		recipe.addComment(result);
		recipeService.save(recipe);
		result.setSocialActor(sc);
		result.setIdentity(identity);
		sc.addComment(result);
		socialActorService.save(sc);
		moment = new Date(System.currentTimeMillis() - 1000);
		result.setMoment(moment);

		return result;
	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);

		Comment result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1000);
		comment.setMomentPosted(moment);

		result = commentRepository.save(comment);

		return result;
	}

	public void flush() {
		commentRepository.flush();
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(commentRepository.exists(comment.getId()));

		CommentableEntity ce;

		ce = comment.getCommentableEntity();
		ce.getComments().remove(comment);
		comment.setCommentableEntity(null);
		commentableEntityService.save(ce);

		commentRepository.delete(comment);
	}

	public Comment findOne(int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);

		Comment result;

		result = commentRepository.findOne(id);
		Assert.notNull(result);

		return result;

	}

}

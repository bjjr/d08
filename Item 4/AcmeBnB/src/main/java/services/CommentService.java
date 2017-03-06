
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	//Managed repository

	@Autowired
	private CommentRepository	commentRepository;

	// Supporting services

	@Autowired
	private ActorService		actorService;


	//Constructors
	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {

		Comment result;
		Date moment;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = new Comment();
		moment = new Date(System.currentTimeMillis() - 1000);

		result.setActor(principal);
		result.setMomentPosted(moment);

		return result;
	}
	public Comment save(Comment comment) {
		Assert.notNull(comment);

		Comment result;
		Date moment;
		Actor principal;

		principal = actorService.findByPrincipal();
		moment = new Date(System.currentTimeMillis() - 1000);
		comment.setMomentPosted(moment);

		result = commentRepository.save(comment);
		principal.getComments().add(result);
		actorService.save(principal);

		return result;
	}

	public void flush() {
		commentRepository.flush();
	}

	public Comment findOne(int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);

		Comment result;

		result = commentRepository.findOne(id);
		Assert.notNull(result);

		return result;

	}

	// Other business methods

	public Collection<Comment> findCommentsByCommentableEntity(int commentableEntityId) {
		Assert.notNull(commentableEntityId);
		Assert.isTrue(commentableEntityId != 0);

		Collection<Comment> result;

		result = commentRepository.findCommentsByCommentableEntity(commentableEntityId);
		Assert.notNull(result);

		return result;
	}

}

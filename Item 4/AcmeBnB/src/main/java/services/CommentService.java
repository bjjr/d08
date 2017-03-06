
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import security.Authority;
import domain.Actor;
import domain.Book;
import domain.Comment;
import domain.CommentableEntity;
import domain.Lessor;
import domain.Property;
import domain.Tenant;

@Service
@Transactional
public class CommentService {

	//Managed repository

	@Autowired
	private CommentRepository	commentRepository;

	// Supporting services

	@Autowired
	private ActorService		actorService;

	@Autowired
	private LessorService		lessorService;

	@Autowired
	private TenantService		tenantService;

	// Validator

	@Autowired
	private Validator			validator;


	//Constructors
	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {

		Comment result;
		Date moment;

		result = new Comment();
		moment = new Date(System.currentTimeMillis() - 1000);

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

	public Comment reconstruct(Comment comment, BindingResult binding) {
		Comment result;
		Actor principal;

		result = null;

		if (comment.getId() == 0) {
			result = comment;
			principal = actorService.findByPrincipal();
			result.setActor(principal);
		}

		validator.validate(result, binding);

		return result;
	}

	public Collection<CommentableEntity> commentableEntities(Actor actor) {
		Collection<CommentableEntity> result;
		Authority authL;
		Authority authT;
		Lessor lessor;
		Tenant tenant;

		result = new ArrayList<CommentableEntity>();
		authL = new Authority();
		authT = new Authority();
		authL.setAuthority(Authority.LESSOR);
		authT.setAuthority(Authority.TENANT);

		if (actor.getUserAccount().getAuthorities().contains(authL)) {
			lessor = lessorService.findOne(actor.getId());
			result.add(lessor);
			for (Property p : lessor.getProperties()) {
				for (Book b : p.getBooks()) {
					if (!result.contains(b.getTenant())) {
						result.add(b.getTenant());
					}
				}
			}
		} else {
			tenant = tenantService.findOne(actor.getId());
			result.add(tenant);
			for (Book b : tenant.getBooks()) {
				if (!result.contains(b.getProperty().getLessor())) {
					result.add(b.getProperty().getLessor());
				}
			}
		}

		return result;

	}
}

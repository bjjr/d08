
package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import domain.Actor;
import domain.Book;
import domain.Comment;
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
	private TenantService		tenantService;


	//Constructors
	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create(Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);

		Comment result;
		Date moment;
		Actor principal;
		Authority auth1;
		Authority auth2;
		Lessor lessor;
		Tenant tenant;
		Boolean finished;

		principal = actorService.findByPrincipal();
		result = new Comment();
		auth1 = new Authority();
		auth2 = new Authority();
		auth1.setAuthority(Authority.TENANT);
		auth2.setAuthority(Authority.LESSOR);
		finished = false;

		if (principal.equals(actor)) {
			result.setCommentableEntity(principal);
			result.setActor(principal);
			principal.getComments().add(result);
			actorService.save(principal);

		} else if (!principal.equals(actor) && principal.getUserAccount().getAuthorities().contains(auth1)) {
			Assert.isTrue(actor.getUserAccount().getAuthorities().contains(auth2));
			tenant = tenantService.findOne(principal.getId());
			for (Book b : tenant.getBooks()) {
				Assert.isTrue(b.getProperty().getLessor().equals(actor));
				break;
			}
			result.setCommentableEntity(actor);
			result.setActor(principal);
			principal.getComments().add(result);
			actorService.save(principal);
		} else {
			Assert.isTrue(actor.getUserAccount().getAuthorities().contains(auth1));
			Assert.isTrue(principal.getUserAccount().getAuthorities().contains(auth2));
			lessor = (Lessor) principal;
			for (Property p : lessor.getProperties()) {
				for (Book b : p.getBooks()) {
					if (b.getTenant().equals(actor)) {
						result.setCommentableEntity(actor);
						result.setActor(principal);
						principal.getComments().add(result);
						actorService.save(principal);
						finished = true;
						break;
					}

				}
				if (finished) {
					break;
				}
			}

		}
		moment = new Date(System.currentTimeMillis() - 1000);
		result.setMomentPosted(moment);

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

		Actor a;

		a = comment.getActor();
		a.getComments().remove(comment);
		comment.setCommentableEntity(null);
		comment.setActor(null);
		actorService.save(a);

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

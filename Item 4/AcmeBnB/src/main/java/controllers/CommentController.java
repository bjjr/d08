
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.CommentService;
import services.LessorService;
import services.TenantService;
import domain.Actor;
import domain.Book;
import domain.Comment;
import domain.CommentableEntity;
import domain.Lessor;
import domain.Property;
import domain.Tenant;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Services -----------------------------------------------------------
	@Autowired
	private CommentService	commentService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private LessorService	lessorService;

	@Autowired
	private TenantService	tenantService;


	// Constructors -----------------------------------------------------------

	public CommentController() {
		super();
	}

	// Listing -----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Comment> comments;
		Actor principal;

		principal = actorService.findByPrincipal();
		comments = commentService.findCommentsByCommentableEntity(principal.getId());

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comments/list.do");

		return result;
	}

	// Creating -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Comment comment;
		Actor principal;
		Lessor lessor;
		Tenant tenant;
		Authority authL;
		Authority authT;
		Collection<CommentableEntity> commentableEntities;

		principal = actorService.findByPrincipal();
		commentableEntities = new ArrayList<CommentableEntity>();
		authL = new Authority();
		authT = new Authority();
		authL.setAuthority(Authority.LESSOR);
		authT.setAuthority(Authority.TENANT);

		comment = commentService.create();
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);

		if (principal.getUserAccount().getAuthorities().contains(authL)) {
			lessor = lessorService.findOne(principal.getId());
			commentableEntities.add(lessor);
			for (Property p : lessor.getProperties()) {
				for (Book b : p.getBooks()) {
					if (!commentableEntities.contains(b.getTenant())) {
						commentableEntities.add(b.getTenant());
					}
				}
			}
		} else if (principal.getUserAccount().getAuthorities().contains(authT)) {
			tenant = tenantService.findOne(principal.getId());
			commentableEntities.add(tenant);
			for (Book b : tenant.getBooks()) {
				if (!commentableEntities.contains(b.getProperty().getLessor())) {
					commentableEntities.add(b.getProperty().getLessor());
				}
			}
		}

		result.addObject("commentableEntities", commentableEntities);

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView();
			result.addObject("comment", comment);
		} else {
			try {
				commentService.save(comment);
				result = new ModelAndView("redirect:../comment/list.do");
				result.addObject("messageStatus", "comment.commit.ok");
			} catch (Throwable oops) {
				result = new ModelAndView();
				result.addObject("comment", comment);
				result.addObject("messageStatus", "comment.commit.error");
			}
		}

		return result;
	}

}

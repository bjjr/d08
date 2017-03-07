
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CommentService;
import domain.Actor;
import domain.Comment;
import domain.CommentableEntity;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Services -----------------------------------------------------------
	@Autowired
	private CommentService	commentService;

	@Autowired
	private ActorService	actorService;


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
		result.addObject("requestURI", "comment/list.do");

		return result;
	}

	// Creating -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Comment comment;
		Actor principal;
		Collection<CommentableEntity> commentableEntities;

		principal = actorService.findByPrincipal();
		comment = commentService.create();
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		commentableEntities = commentService.commentableEntities(principal);

		result.addObject("commentableEntities", commentableEntities);

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Comment comment, BindingResult binding) {
		ModelAndView result;
		Collection<CommentableEntity> commentableEntities;

		comment = commentService.reconstruct(comment, binding);

		commentableEntities = commentService.commentableEntities(comment.getActor());

		if (binding.hasErrors()) {
			result = new ModelAndView();
			result.addObject("comment", comment);
			result.addObject("commentableEntities", commentableEntities);
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


package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import domain.Lessor;

@Controller
@RequestMapping("/lessor")
public class LessorController {

	@Autowired
	private LessorService	lessorService;


	public LessorController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Lessor lessor;

		lessor = lessorService.create();
		result = createEditModelAndView(lessor);

		return result;
	}

	//Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int lessorId) {
		ModelAndView result;
		Lessor lessor;

		lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);
		result = createEditModelAndView(lessor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Lessor lessor, BindingResult binding) {
		ModelAndView result;
		Lessor save;

		save = lessorService.reconstruct(lessor, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(lessor);
		} else {
			try {
				lessorService.save(save);
				result = new ModelAndView("redirect:/lessor/ownList.do");
				result.addObject("message", "lessor.commit.ok");
			} catch (Throwable oops) {
				result = createEditModelAndView(lessor, "lessor.commit.error");
			}
		}

		return result;
	}

	// Deleting ------------------------------------------------
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(Lessor lessor, BindingResult binding) {
	//		ModelAndView res;
	//
	//		try {
	//			lessorService.delete(lessor);
	//			res = new ModelAndView("redirect:ownList.do");
	//		} catch (Throwable th) {
	//			if (th.getMessage().equals("You have pending books")) {
	//				res = createEditModelAndView(lessor, "lessor.commit.error.books");
	//			} else {
	//				res = createEditModelAndView(lessor, "lessor.commit.error");
	//			}
	//		}
	//
	//		return res;
	//	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int lessorId) {
		ModelAndView result;
		Lessor lessor;

		lessor = lessorService.findOne(lessorId);
		result = new ModelAndView("lessor/display");
		result.addObject("lessor", lessor);

		return result;

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Lessor> lessors;

		lessors = lessorService.findAll();
		result = new ModelAndView("lessor/list");
		result.addObject("requestURI", "lessor/list.do");
		result.addObject("lessors", lessors);

		return result;

	}

	protected ModelAndView createEditModelAndView(Lessor lessor) {
		ModelAndView result;

		result = createEditModelAndView(lessor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Lessor lessor, String message) {
		ModelAndView result;

		result = new ModelAndView("lessor/edit");
		result.addObject("lessor", lessor);
		result.addObject("message", message);

		return result;
	}

}


package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LessorService;
import domain.Lessor;
import forms.LessorForm;

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
		Lessor lessor = lessorService.create();

		result = createEditModelAndView(new LessorForm(lessor));

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LessorForm lessorForm, BindingResult binding) {
		ModelAndView result;
		Lessor lessor;

		lessor = lessorService.reconstruct(lessorForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(lessorForm);
		} else {
			if (!lessor.getUserAccount().getPassword().equals(lessorForm.getConfirmPassword())) {
				result = createEditModelAndView(lessorForm, "lessor.commit.password");
			} else if (!lessorForm.isEula()) {
				result = createEditModelAndView(lessorForm, "lessor.commit.eula");
			} else {
				try {
					lessorService.save(lessor);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (IllegalArgumentException oops) {
					result = createEditModelAndView(lessorForm, "lessor.commit.error");
				}
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

	protected ModelAndView createEditModelAndView(LessorForm lessor) {
		ModelAndView result;

		result = createEditModelAndView(lessor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(LessorForm lessor, String message) {
		ModelAndView result;

		result = new ModelAndView("lessor/create");
		result.addObject("lessorForm", lessor);
		result.addObject("message", message);

		return result;
	}

}

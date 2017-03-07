
package controllers;

import java.util.Collection;
import java.util.List;

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
import domain.Property;
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
	public ModelAndView save(LessorForm lessorForm, BindingResult binding) {
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

	// Editing ------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Lessor lessor = lessorService.findByPrincipal();
		Assert.notNull(lessor);

		result = new ModelAndView("lessor/edit");
		result.addObject("lessor", lessor);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Lessor lessor, BindingResult binding) {
		ModelAndView result;
		Lessor lessorReconstructed = lessorService.reconstruct(lessor, binding);

		if (binding.hasErrors()) {
			result = new ModelAndView("lessor/edit");
			result.addObject("lessor", lessorReconstructed);
		} else {
			try {
				lessorService.save(lessorReconstructed);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (IllegalArgumentException oops) {
				result = new ModelAndView("lessor/edit");
				result.addObject("lessor", lessorReconstructed);
				result.addObject("message", "lessor.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int lessorId) {
		ModelAndView result;
		Lessor lessor;
		List<Property> propertiesSortedByAudits, propertiesSortedByBooks, propertiesSortedByAcceptedBooks, propertiesSortedByDeniedBooks, propertiesSortedByPendingBooks;

		lessor = lessorService.findOne(lessorId);
		propertiesSortedByAudits = lessorService.propertiesSortedByAudits(lessorId);
		propertiesSortedByBooks = lessorService.propertiesSortedByBooks(lessorId);
		propertiesSortedByAcceptedBooks = lessorService.propertiesSortedByAcceptedBooks(lessorId);
		propertiesSortedByDeniedBooks = lessorService.propertiesSortedByDeniedBooks(lessorId);
		propertiesSortedByPendingBooks = lessorService.propertiesSortedByPendingBooks(lessorId);

		result = new ModelAndView("lessor/display");
		result.addObject("lessor", lessor);
		result.addObject("propertiesSortedByAudits", propertiesSortedByAudits);
		result.addObject("propertiesSortedByBooks", propertiesSortedByBooks);
		result.addObject("propertiesSortedByAcceptedBooks", propertiesSortedByAcceptedBooks);
		result.addObject("propertiesSortedByDeniedBooks", propertiesSortedByDeniedBooks);
		result.addObject("propertiesSortedByPendingBooks", propertiesSortedByPendingBooks);

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


package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

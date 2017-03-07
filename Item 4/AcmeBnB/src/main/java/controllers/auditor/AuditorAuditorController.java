
package controllers.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditorService;
import controllers.AbstractController;
import domain.Auditor;

@Controller
@RequestMapping("/auditor/auditor")
public class AuditorAuditorController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditorService	auditorService;


	// Constructors -------------------------------------------

	public AuditorAuditorController() {
		super();
	}

	// Edit ---------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Auditor auditor = auditorService.findByPrincipal();
		Assert.notNull(auditor);

		result = createEditModelAndView(auditor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Auditor auditor, BindingResult binding) {
		ModelAndView result;
		Auditor auditorReconstructed = auditorService.reconstruct(auditor, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(auditorReconstructed);
		} else {
			try {
				auditorService.save(auditorReconstructed);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (IllegalArgumentException oops) {
				result = createEditModelAndView(auditorReconstructed, "auditor.commit.error");
			}
		}

		return result;
	}

	// Ancillary methods -------------------------------------

	protected ModelAndView createEditModelAndView(Auditor auditor) {
		ModelAndView result;

		result = createEditModelAndView(auditor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Auditor auditor, String message) {
		ModelAndView result;

		result = new ModelAndView("auditor/edit");
		result.addObject("auditor", auditor);
		result.addObject("message", message);

		return result;
	}
}


package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuditorService;
import controllers.AbstractController;
import domain.Auditor;
import forms.AuditorForm;

@Controller
@RequestMapping("/auditor/administrator")
public class AuditorAdministratorController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditorService	auditorService;


	// Constructors -------------------------------------------

	public AuditorAdministratorController() {
		super();
	}

	// Creating -----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Auditor auditor;
		AuditorForm auditorForm;

		auditor = auditorService.create();
		auditorForm = new AuditorForm(auditor);
		result = createEditModelAndView(auditorForm);

		return result;
	}

	// Saving ------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuditorForm auditorForm, BindingResult binding) {
		ModelAndView result;
		Auditor auditor;

		auditor = auditorService.reconstruct(auditorForm, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(auditorForm);
		} else {
			if (!auditor.getUserAccount().getPassword().equals(auditorForm.getConfirmPassword())) {
				result = createEditModelAndView(auditorForm, "auditor.commit.password");
			} else {
				try {
					auditorService.save(auditor);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (IllegalArgumentException oops) {
					result = createEditModelAndView(auditorForm, "auditor.commit.error");
				}
			}
		}
		return result;
	}

	// Ancillary methods -------------------------------------

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm) {
		ModelAndView result;

		result = createEditModelAndView(auditorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(AuditorForm auditorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("auditor/create");
		result.addObject("auditorForm", auditorForm);
		result.addObject("message", message);

		return result;
	}
}

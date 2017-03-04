
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	//Services

	@Autowired
	private AdministratorService	administratorService;


	//Constructors

	public AdministratorAdministratorController() {
		super();
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrator administrator = administratorService.findByPrincipal();

		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Administrator administrator, BindingResult binding) {
		ModelAndView result;
		administrator = administratorService.reconstruct(administrator, binding);

		if (binding.hasErrors()) {
			result = new ModelAndView("administrator/edit");
			result.addObject("administrator", administrator);
		} else {
			try {
				administratorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
				result.addObject("messageStatus", "administrator.commit.ok");
			} catch (IllegalArgumentException oops) {
				result = new ModelAndView();
				result.addObject("administrator", administrator);
				result.addObject("messageStatus", "administrator.commit.error");
			}
		}

		return result;
	}

}

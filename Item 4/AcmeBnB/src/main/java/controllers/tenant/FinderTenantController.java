
package controllers.tenant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.TenantService;
import controllers.AbstractController;
import domain.Finder;

@Controller
@RequestMapping("/finder/tenant")
public class FinderTenantController extends AbstractController {

	@Autowired
	private TenantService	tenantService;

	@Autowired
	private FinderService	finderService;


	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Finder finder;

		finder = finderService.findByPrincipal();
		if (finder == null) {
			finder = finderService.create();
		}

		result = new ModelAndView("finder/display");
		result.addObject("finder", finder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Finder finder;

		finder = finderService.findByPrincipal();
		if (finder == null) {
			finder = finderService.create();
		}

		result = createEditModelAndView(finder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Finder finder, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(finder);
		} else {
			try {
				finderService.save(finder);
				result = new ModelAndView("redirect:/finder/tenant/display.do");
			} catch (IllegalArgumentException oops) {
				result = createEditModelAndView(finder, "misc.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Finder finder) {
		ModelAndView result;

		result = createEditModelAndView(finder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Finder finder, String message) {
		ModelAndView result;

		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);
		result.addObject("message", message);

		return result;
	}

}

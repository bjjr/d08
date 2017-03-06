
package controllers.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TenantService;
import controllers.AbstractController;
import domain.Tenant;

@Controller
@RequestMapping("/tenant/tenant")
public class TenantTenantController extends AbstractController {

	@Autowired
	private TenantService	tenantService;


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Tenant tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);

		result = createEditModelAndView(tenant);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Tenant tenant, BindingResult binding) {
		ModelAndView result;
		Tenant tenantReconstructed = tenantService.reconstruct(tenant, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(tenantReconstructed);
		} else {
			try {
				tenantService.save(tenantReconstructed);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (IllegalArgumentException oops) {
				result = createEditModelAndView(tenantReconstructed, "tenant.commit.error");
			}
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Tenant tenant) {
		ModelAndView result;

		result = createEditModelAndView(tenant, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Tenant tenant, String message) {
		ModelAndView result;

		result = new ModelAndView("tenant/edit");
		result.addObject("tenant", tenant);
		result.addObject("message", message);

		return result;
	}

}

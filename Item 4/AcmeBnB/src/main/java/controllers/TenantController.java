
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TenantService;
import domain.Tenant;
import forms.TenantForm;

@Controller
@RequestMapping("/tenant")
public class TenantController extends AbstractController {

	@Autowired
	private TenantService	tenantService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Tenant tenant = tenantService.create();

		result = createEditModelAndView(new TenantForm(tenant));

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TenantForm tenantForm, BindingResult binding) {
		ModelAndView result;
		Tenant tenant;

		tenant = tenantService.reconstruct(tenantForm, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(tenantForm);
		} else {
			if (!tenant.getUserAccount().getPassword().equals(tenantForm.getConfirmPassword())) {
				result = createEditModelAndView(tenantForm, "tenant.commit.password");
			} else if (!tenantForm.isEula()) {
				result = createEditModelAndView(tenantForm, "tenant.commit.eula");
			} else {
				try {
					tenantService.save(tenant);
					result = new ModelAndView("redirect:/welcome/index.do");
				} catch (IllegalArgumentException oops) {
					result = createEditModelAndView(tenantForm, "tenant.commit.error");
				}
			}
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(TenantForm tenant) {
		ModelAndView result;

		result = createEditModelAndView(tenant, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(TenantForm tenant, String message) {
		ModelAndView result;

		result = new ModelAndView("tenant/create");
		result.addObject("tenantForm", tenant);
		result.addObject("message", message);

		return result;
	}

}

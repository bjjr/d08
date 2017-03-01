
package controllers.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.TenantService;
import controllers.AbstractController;
import domain.Finder;
import domain.Tenant;

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
		Tenant tenant;

		tenant = tenantService.findByPrincipal();
		Assert.notNull(tenant);

		result = new ModelAndView("finder/display");

		result.addObject("requestURI", "finder/tenant/display.do");
		result.addObject("finder", tenant.getFinder());

		return result;
	}

}

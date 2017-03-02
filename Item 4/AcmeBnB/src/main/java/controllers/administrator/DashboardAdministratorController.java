
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeValueService;
import services.AuditService;
import services.FinderService;
import services.InvoiceService;
import services.LessorService;
import services.PropertyService;
import services.TenantService;
import controllers.AbstractController;
import domain.Lessor;
import domain.Tenant;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Supporting services -----------------------------------------------------------

	@Autowired
	private LessorService			lessorService;

	@Autowired
	private TenantService			tenantService;

	@Autowired
	private FinderService			finderService;

	@Autowired
	private AttributeValueService	attributeValueService;

	@Autowired
	private AuditService			auditService;

	@Autowired
	private InvoiceService			invoiceService;

	@Autowired
	private PropertyService			propertyService;


	// Constructors -----------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	// Dashboard -----------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;
		Double bookAPL;
		Double bookDPL;
		Double bookAPT;
		Double bookDPT;
		Collection<Lessor> lessorMBA;
		Collection<Lessor> lessorMBD;
		Collection<Lessor> lessorMBP;
		Collection<Tenant> tenantMBA;
		Collection<Tenant> tenantMBD;
		Collection<Tenant> tenantMBP;
		Collection<Lessor> lessorMaxABB;
		Collection<Lessor> lessorMinABB;

		result = new ModelAndView();

		return result;

	}

}

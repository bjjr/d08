
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeService;
import services.AuditService;
import services.BookService;
import services.FinderService;
import services.InvoiceService;
import services.LessorService;
import services.SocialIdentityService;
import services.TenantService;
import controllers.AbstractController;
import domain.Attribute;
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
	private AuditService			auditService;

	@Autowired
	private InvoiceService			invoiceService;

	@Autowired
	private BookService				bookService;

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private AttributeService		attributeService;


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
		Collection<Tenant> tenantMaxABB;
		Collection<Tenant> tenantMinABB;
		Lessor maxL;
		Lessor minL;
		Tenant maxT;
		Tenant minT;
		Double maxRPF;
		Double minRPF;
		Double avgRPF;
		Double maxAPP;
		Double minAPP;
		Double avgAPP;
		Collection<Attribute> attributeSMTUP;
		Double maxSIPA;
		Double minSIPA;
		Double avgSIPA;
		Double maxIPT;
		Double minIPT;
		Double avgIPT;
		Double totalMoney;
		Double avgBPP1A;
		Double avgBPPNA;

		bookAPL = numbers(lessorService.avgAcceptedPerLessor());
		bookDPL = numbers(lessorService.avgDeniedPerLessor());
		bookAPT = numbers(tenantService.avgAcceptedPerTenant());
		bookDPT = numbers(tenantService.avgDeniedPerTenant());
		lessorMBA = lessors(lessorService.lessorNumApproved());
		lessorMBD = lessors(lessorService.lessorNumDenied());
		lessorMBP = lessors(lessorService.lessorNumPending());
		tenantMBA = tenants(tenantService.tenantsMoreRequestsApproved());
		tenantMBD = tenants(tenantService.tenantsMoreRequestsDenied());
		tenantMBP = tenants(tenantService.tenantsMoreRequestsPending());
		lessorMaxABB = new ArrayList<Lessor>();
		lessorMinABB = new ArrayList<Lessor>();
		maxL = lessorService.lessorMaxRatio();
		minL = lessorService.lessorMinRatio();
		if (maxL != null) {
			lessorMaxABB.add(maxL);
		}
		if (minL != null) {
			lessorMinABB.add(minL);
		}
		tenantMaxABB = new ArrayList<Tenant>();
		tenantMinABB = new ArrayList<Tenant>();
		maxT = tenantService.tenantMaxRatio();
		minT = tenantService.tenantMinRatio();
		if (maxT != null) {
			tenantMaxABB.add(maxT);
		}
		if (minT != null) {
			tenantMinABB.add(minT);
		}
		maxRPF = numbers(finderService.maxResultsPerFinder());
		minRPF = numbers(finderService.minResultsPerFinder());
		avgRPF = numbers(finderService.avgResultsPerFinder());
		maxAPP = numbers(auditService.findMaxAuditsOfProperties());
		minAPP = numbers(auditService.findMinAuditsOfProperties());
		avgAPP = numbers(auditService.findAvgAuditsOfProperties());
		attributeSMTUP = attributes(attributeService.findListAttributesSortedByTimesUsed());
		maxSIPA = numbers(socialIdentityService.findMaxSocialIdentities());
		minSIPA = numbers(socialIdentityService.findMinSocialIdentities());
		avgSIPA = numbers(socialIdentityService.findAvgSocialIdentities());
		maxIPT = numbers(invoiceService.findMaxInvoicesOfTenants());
		minIPT = numbers(invoiceService.findMinInvoicesOfTenants());
		avgIPT = numbers(invoiceService.findAvgInvoicesOfTenants());
		totalMoney = numbers(invoiceService.totalMoney());
		avgBPP1A = numbers(bookService.findAvgBooksProperty1Audit());
		avgBPPNA = numbers(bookService.findAvgBooksPropertyNoAudit());

		result = new ModelAndView("administrator/dashboard");
		result.addObject("bookAPL", bookAPL);
		result.addObject("bookDPL", bookDPL);
		result.addObject("bookAPT", bookAPT);
		result.addObject("bookDPT", bookDPT);
		result.addObject("lessorMBA", lessorMBA);
		result.addObject("lessorMBD", lessorMBD);
		result.addObject("lessorMBP", lessorMBP);
		result.addObject("tenantMBA", tenantMBA);
		result.addObject("tenantMBD", tenantMBD);
		result.addObject("tenantMBP", tenantMBP);
		result.addObject("lessorMaxABB", lessorMaxABB);
		result.addObject("lessorMinABB", lessorMinABB);
		result.addObject("tenantMaxABB", tenantMaxABB);
		result.addObject("tenantMinABB", tenantMinABB);
		result.addObject("maxRPF", maxRPF);
		result.addObject("minRPF", minRPF);
		result.addObject("avgRPF", avgRPF);
		result.addObject("maxAPP", maxAPP);
		result.addObject("minAPP", minAPP);
		result.addObject("avgAPP", avgAPP);
		result.addObject("attributeSMTUP", attributeSMTUP);
		result.addObject("maxSIPA", maxSIPA);
		result.addObject("minSIPA", minSIPA);
		result.addObject("avgSIPA", avgSIPA);
		result.addObject("maxIPT", maxIPT);
		result.addObject("minIPT", minIPT);
		result.addObject("avgIPT", avgIPT);
		result.addObject("totalMoney", totalMoney);
		result.addObject("avgBPP1A", avgBPP1A);
		result.addObject("avgBPPNA", avgBPPNA);
		result.addObject("requestURI", "dashboard/administrator/dashboard.do");

		return result;

	}
	//Ancillary methods -----------------------------------

	public Collection<Lessor> lessors(Collection<Lessor> lessors) {
		Collection<Lessor> result;

		result = new ArrayList<Lessor>();

		if (lessors != null) {
			result.addAll(lessors);
		}

		return result;
	}

	public Collection<Tenant> tenants(Collection<Tenant> tenants) {
		Collection<Tenant> result;

		result = new ArrayList<Tenant>();

		if (tenants != null) {
			result.addAll(tenants);
		}

		return result;
	}

	public Collection<Attribute> attributes(Collection<Attribute> attributes) {
		Collection<Attribute> result;

		result = new ArrayList<Attribute>();

		if (attributes != null) {
			result.addAll(attributes);
		}

		return result;
	}

	public Double numbers(Double number) {
		Double result;

		result = 0.0;

		if (number != null) {
			result = number;
		}

		return result;
	}

}

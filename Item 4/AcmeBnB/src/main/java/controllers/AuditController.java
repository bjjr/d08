
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.PropertyService;
import domain.Audit;
import domain.Property;

@Controller
@RequestMapping("/audit")
public class AuditController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditService	auditService;

	@Autowired
	private PropertyService	propertyService;


	// Constructors -------------------------------------------

	public AuditController() {
		super();
	}

	// Listing ------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int auditId) {
		ModelAndView result;
		Collection<Audit> audits;
		Property property;
		Boolean isAuditor;

		property = propertyService.findOne(auditId);
		audits = property.getAudits();
		isAuditor = false;

		result = new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("isAuditor", isAuditor);
		result.addObject("requestURI", "audit/list.do");

		return result;
	}

	// Display -----------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int auditId) {
		ModelAndView result;
		Audit audit;

		audit = auditService.findOne(auditId);
		Assert.notNull(audit);

		result = new ModelAndView("audit/display");
		result.addObject("audit", audit);
		result.addObject("requestURI", "audit/auditor/display.do");

		return result;
	}

}

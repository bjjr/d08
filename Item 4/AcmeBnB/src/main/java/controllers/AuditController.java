
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
import domain.Attachment;
import domain.Audit;

@Controller
@RequestMapping("/audit")
public class AuditController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditService	auditService;


	// Constructors -------------------------------------------

	public AuditController() {
		super();
	}

	// Listing ------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId) {
		ModelAndView result;
		Collection<Audit> audits;
		Boolean isAuditor;

		audits = auditService.findAuditsPublishedByProperty(propertyId);
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
		Collection<Attachment> attachments;

		audit = auditService.findOne(auditId);
		Assert.notNull(audit);
		attachments = auditService.findAttachmentsByAudit(auditId);

		result = new ModelAndView("audit/display");
		result.addObject("audit", audit);
		result.addObject("attachments", attachments);
		result.addObject("requestURI", "audit/display.do");

		return result;
	}

}


package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import domain.Attachment;
import domain.Audit;
import domain.Property;

@Controller
@RequestMapping("/attachment")
public class AttachmentController extends AbstractController {

	// Services -----------------------------------------------

	@Autowired
	private AuditService	auditService;


	// Constructors -------------------------------------------

	public AttachmentController() {
		super();
	}

	// Listing ------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int auditId) {
		ModelAndView result;
		Collection<Attachment> attachments;
		Audit audit;
		Property property;
		int propertyId;

		attachments = auditService.findAttachmentsByAudit(auditId);
		audit = auditService.findOne(auditId);
		property = audit.getProperty();
		propertyId = property.getId();

		result = new ModelAndView("attachment/list");
		result.addObject("attachments", attachments);
		result.addObject("propertyId", propertyId);
		result.addObject("auditId", auditId);
		result.addObject("audit", audit);
		result.addObject("requestURI", "attachment/list.do");

		return result;
	}

}
